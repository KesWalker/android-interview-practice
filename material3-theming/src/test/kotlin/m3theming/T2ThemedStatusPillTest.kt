package m3theming

import android.graphics.BitmapFactory
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
class T2ThemedStatusPillTest {
    init {
        System.setProperty("roborazzi.test.record", "true")
    }

    @get:Rule val compose = createComposeRule()

    @Test fun `light theme shows the light label and the LightPalette surface color`() {
        compose.setContent { ThemedStatusPill(isDarkTheme = false) }
        compose.onNodeWithText("Light mode").assertIsDisplayed()

        val outFile = File("build/test-captures/statusPillLight.png")
        compose.onNodeWithTag("statusPill").captureRoboImage(outFile)
        val pixel = BitmapFactory.decodeFile(outFile.absolutePath).getPixel(2, 2)

        assertTrue(
            "expected the light pill to use LightPalette.surface",
            colorsRoughlyMatch(LightPalette.surface.toArgb(), pixel),
        )
    }

    @Test fun `dark theme shows the dark label and the DarkPalette surface color`() {
        compose.setContent { ThemedStatusPill(isDarkTheme = true) }
        compose.onNodeWithText("Dark mode").assertIsDisplayed()

        val outFile = File("build/test-captures/statusPillDark.png")
        compose.onNodeWithTag("statusPill").captureRoboImage(outFile)
        val pixel = BitmapFactory.decodeFile(outFile.absolutePath).getPixel(2, 2)

        assertTrue(
            "expected the dark pill to use DarkPalette.surface",
            colorsRoughlyMatch(DarkPalette.surface.toArgb(), pixel),
        )
    }
}
