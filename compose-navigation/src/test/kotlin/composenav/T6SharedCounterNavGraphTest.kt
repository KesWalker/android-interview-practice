package composenav

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T6SharedCounterNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { SharedCounterNavGraph(navController = navController) }
        }
    }

    @Test fun `entering the flow shows counter A starting at zero`() {
        setUpGraph()
        compose.onNodeWithText("Enter Counter Flow").performClick()
        compose.onNodeWithText("Counter A: 0").assertIsDisplayed()
    }

    @Test fun `incrementing on counter A is visible immediately on counter A`() {
        setUpGraph()
        compose.onNodeWithText("Enter Counter Flow").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Counter A: 2").assertIsDisplayed()
    }

    @Test fun `counter B sees the same shared count, not a fresh zero`() {
        setUpGraph()
        compose.onNodeWithText("Enter Counter Flow").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()

        compose.onNodeWithText("Go to Counter B").performClick()
        compose.onNodeWithText("Counter B sees: 3").assertIsDisplayed()
    }
}
