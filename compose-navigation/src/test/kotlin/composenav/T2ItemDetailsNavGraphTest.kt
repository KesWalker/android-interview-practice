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
class T2ItemDetailsNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { ItemDetailsNavGraph(navController = navController) }
        }
    }

    @Test fun `list screen shows both item buttons`() {
        setUpGraph()
        compose.onNodeWithText("Apple").assertIsDisplayed()
        compose.onNodeWithText("Banana").assertIsDisplayed()
    }

    @Test fun `picking Banana reads the argument back on the details screen`() {
        setUpGraph()
        compose.onNodeWithText("Banana").performClick()
        compose.onNodeWithText("Selected: Banana").assertIsDisplayed()
    }

    @Test fun `picking Apple reads a different argument value`() {
        setUpGraph()
        compose.onNodeWithText("Apple").performClick()
        compose.onNodeWithText("Selected: Apple").assertIsDisplayed()
    }
}
