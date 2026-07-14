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
class T1HomeDetailsNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { HomeDetailsNavGraph(navController = navController) }
        }
    }

    @Test fun `home screen shows its text and the details screen is not there yet`() {
        setUpGraph()
        compose.onNodeWithText("Home Screen").assertIsDisplayed()
        compose.onNodeWithText("Details Screen").assertDoesNotExist()
    }

    @Test fun `clicking Go to Details navigates to the details destination`() {
        setUpGraph()
        compose.onNodeWithText("Go to Details").performClick()
        compose.onNodeWithText("Details Screen").assertIsDisplayed()
        compose.onNodeWithText("Home Screen").assertDoesNotExist()
    }

    @Test fun `navigating directly through the controller also works`() {
        setUpGraph()
        compose.runOnIdle { navController.navigate("details") }
        compose.onNodeWithText("Details Screen").assertIsDisplayed()
    }
}
