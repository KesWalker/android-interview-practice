package composenav

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T3OnboardingNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { OnboardingNavGraph(navController = navController) }
        }
    }

    private fun ourRoutes(): List<String> =
        navController.currentBackStack.value.mapNotNull { it.destination.route }
            .filter { it in setOf("A", "B", "C") }

    @Test fun `walking A to B to C ends up showing screen C`() {
        setUpGraph()
        compose.onNodeWithText("Next").performClick()
        compose.onNodeWithText("Finish").performClick()
        compose.onNodeWithText("Screen C").assertIsDisplayed()
    }

    @Test fun `reaching C with popUpTo inclusive removes A and B from the back stack`() {
        setUpGraph()
        compose.onNodeWithText("Next").performClick()
        compose.onNodeWithText("Finish").performClick()

        compose.runOnIdle {
            val routes = ourRoutes()
            assertEquals(listOf("C"), routes)
            assertFalse("A should have been popped", routes.contains("A"))
            assertFalse("B should have been popped", routes.contains("B"))
            assertTrue(routes.contains("C"))
        }
    }
}
