package composelayout

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File
import kotlin.math.abs

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T2ModifierOrderTest {
    @get:Rule val compose = createComposeRule()

    @Before
    fun forceRoborazziCapture() {
        // captureRoboImage is normally a no-op outside a `recordRoborazziDebug` style
        // Gradle task; this makes it actually render pixels for our own assertion too.
        System.setProperty("roborazzi.test.record", "true")
    }

    @After
    fun clearRoborazziCapture() {
        System.clearProperty("roborazzi.test.record")
    }

    // A point 5% in from the corner of an 80dp box is 4dp from the edge:
    // inside the painted region when the background covers the full box,
    // but inside the transparent 16dp margin when padding was applied first.
    private fun cornerRgb(paddingFirst: Boolean): Triple<Int, Int, Int> {
        compose.setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    ModifierOrderBox(
                        paddingFirst = paddingFirst,
                        modifier = Modifier.testTag("box").size(80.dp),
                    )
                }
            }
        }
        val file = File.createTempFile("modifier-order-box", ".png")
        try {
            compose.onNodeWithTag("box").captureRoboImage(file.absolutePath)
            val bytes = file.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val x = (bitmap.width * 0.05f).toInt()
            val y = (bitmap.height * 0.05f).toInt()
            val argb = bitmap.getPixel(x, y)
            return Triple((argb shr 16) and 0xFF, (argb shr 8) and 0xFF, argb and 0xFF)
        } finally {
            file.delete()
        }
    }

    private fun assertCloseTo(expected: Triple<Int, Int, Int>, actual: Triple<Int, Int, Int>) {
        val tolerance = 10
        val closeEnough = abs(expected.first - actual.first) <= tolerance &&
            abs(expected.second - actual.second) <= tolerance &&
            abs(expected.third - actual.third) <= tolerance
        assertTrue("expected ~$expected but was $actual", closeEnough)
    }

    @Test
    fun `background-then-padding paints edge to edge`() {
        // 0xB3261E
        assertCloseTo(Triple(0xB3, 0x26, 0x1E), cornerRgb(paddingFirst = false))
    }

    @Test
    fun `padding-then-background leaves a transparent margin`() {
        // white, from the surrounding Surface(color = Color.White)
        assertCloseTo(Triple(0xFF, 0xFF, 0xFF), cornerRgb(paddingFirst = true))
    }
}
