package composenav

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T4SingleTopNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { SingleTopNavGraph(navController = navController) }
        }
    }

    private fun notificationsEntryCount(): Int =
        navController.currentBackStack.value.count { it.destination.route == "notifications" }

    @Test fun `opening notifications once shows the notifications screen`() {
        setUpGraph()
        compose.onNodeWithText("Open Notifications").performClick()
        compose.onNodeWithText("Notifications").assertIsDisplayed()
        compose.runOnIdle { assertEquals(1, notificationsEntryCount()) }
    }

    @Test fun `refreshing repeatedly does not stack duplicate entries`() {
        setUpGraph()
        compose.onNodeWithText("Open Notifications").performClick()
        compose.onNodeWithText("Refresh").performClick()
        compose.onNodeWithText("Refresh").performClick()
        compose.onNodeWithText("Refresh").performClick()

        compose.runOnIdle { assertEquals(1, notificationsEntryCount()) }
        compose.onNodeWithText("Notifications").assertIsDisplayed()
    }
}
