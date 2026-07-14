package m3theming

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.window.core.layout.WindowWidthSizeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T5AdaptiveHomeScreenTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `compact width shows a bottom bar, not a rail`() {
        compose.setContent {
            MaterialTheme { AdaptiveHomeScreen(windowWidthSizeClass = WindowWidthSizeClass.COMPACT) }
        }
        compose.onNodeWithTag("bottomBar").assertIsDisplayed()
        compose.onNodeWithTag("navRail").assertDoesNotExist()
    }

    @Test fun `expanded width shows a navigation rail, not a bottom bar`() {
        compose.setContent {
            MaterialTheme { AdaptiveHomeScreen(windowWidthSizeClass = WindowWidthSizeClass.EXPANDED) }
        }
        compose.onNodeWithTag("navRail").assertIsDisplayed()
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
    }

    @Test fun `medium width also shows a navigation rail`() {
        compose.setContent {
            MaterialTheme { AdaptiveHomeScreen(windowWidthSizeClass = WindowWidthSizeClass.MEDIUM) }
        }
        compose.onNodeWithTag("navRail").assertIsDisplayed()
    }
}
