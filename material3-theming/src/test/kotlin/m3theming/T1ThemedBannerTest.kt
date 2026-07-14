package m3theming

import android.graphics.BitmapFactory
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T1ThemedBannerTest {
    init {
        System.setProperty("roborazzi.test.record", "true")
    }

    @get:Rule val compose = createComposeRule()

    @Test fun `banner text is shown`() {
        val customScheme = lightColorScheme(
            primary = Color(0xFF2D6A4F),
            onPrimary = Color.White,
        )
        compose.setContent {
            MaterialTheme(colorScheme = customScheme) {
                ThemedBanner()
            }
        }
        compose.onNodeWithText("Themed").assertIsDisplayed()
    }

    @Test fun `banner surface fills with the theme's primary color, not a hardcoded one`() {
        val customScheme = lightColorScheme(
            primary = Color(0xFF2D6A4F),
            onPrimary = Color.White,
        )
        compose.setContent {
            MaterialTheme(colorScheme = customScheme) {
                ThemedBanner()
            }
        }

        val outFile = File("build/test-captures/themedBanner.png")
        compose.onNodeWithTag("themedBanner").captureRoboImage(outFile)

        val pixel = BitmapFactory.decodeFile(outFile.absolutePath).getPixel(2, 2)
        assertTrue(
            "expected the banner to be filled with the theme's primary color",
            colorsRoughlyMatch(customScheme.primary.toArgb(), pixel),
        )
    }
}

/**
 * Rendering introduces a little colour drift (anti-aliasing, colour-space
 * conversion), so compare channels with a small tolerance instead of an
 * exact match.
 */
internal fun colorsRoughlyMatch(expectedArgb: Int, actualArgb: Int, tolerance: Int = 16): Boolean {
    fun channel(argb: Int, shift: Int) = (argb shr shift) and 0xFF
    val channels = intArrayOf(16, 8, 0)
    return channels.all { shift ->
        kotlin.math.abs(channel(expectedArgb, shift) - channel(actualArgb, shift)) <= tolerance
    }
}
