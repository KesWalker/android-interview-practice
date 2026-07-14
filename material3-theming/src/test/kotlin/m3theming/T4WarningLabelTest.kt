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
import org.junit.Assert.assertFalse
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
class T4WarningLabelTest {
    init {
        System.setProperty("roborazzi.test.record", "true")
    }

    @get:Rule val compose = createComposeRule()

    // Deliberately NOT a typical hardcoded "warning red": if the
    // implementation hardcodes Color.Red instead of reading
    // MaterialTheme.colorScheme.error, this pixel check catches it.
    private val unusualErrorColor = Color(0xFF00695C)

    @Test fun `label text is shown`() {
        val scheme = lightColorScheme(error = unusualErrorColor)
        compose.setContent {
            MaterialTheme(colorScheme = scheme) { WarningLabel("Low battery") }
        }
        compose.onNodeWithText("Low battery").assertIsDisplayed()
    }

    @Test fun `label reads the theme's error color, not a hardcoded one`() {
        val scheme = lightColorScheme(error = unusualErrorColor)
        compose.setContent {
            MaterialTheme(colorScheme = scheme) { WarningLabel("Low battery") }
        }

        val outFile = File("build/test-captures/warningLabel.png")
        compose.onNodeWithTag("warningLabel").captureRoboImage(outFile)
        val pixel = BitmapFactory.decodeFile(outFile.absolutePath).getPixel(2, 2)

        assertTrue(
            "expected the label to be filled with the theme's (unusual) error color",
            colorsRoughlyMatch(unusualErrorColor.toArgb(), pixel),
        )
        assertFalse(
            "found a hardcoded red instead of the theme's error color",
            colorsRoughlyMatch(Color.Red.toArgb(), pixel),
        )
    }
}
