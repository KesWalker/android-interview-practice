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
class T5ColorPickerNavGraphTest {
    @get:Rule val compose = createComposeRule()

    private lateinit var navController: NavHostController

    private fun setUpGraph() {
        compose.setContent {
            navController = rememberNavController()
            MaterialTheme { ColorPickerNavGraph(navController = navController) }
        }
    }

    @Test fun `screen A starts with no color selected`() {
        setUpGraph()
        compose.onNodeWithText("Selected color: None").assertIsDisplayed()
    }

    @Test fun `picking Red on screen B returns the result to screen A`() {
        setUpGraph()
        compose.onNodeWithText("Pick Color").performClick()
        compose.onNodeWithText("Pick a color").assertIsDisplayed()
        compose.onNodeWithText("Red").performClick()
        compose.onNodeWithText("Selected color: Red").assertIsDisplayed()
    }

    @Test fun `picking Blue then going again and picking Red overwrites the result`() {
        setUpGraph()
        compose.onNodeWithText("Pick Color").performClick()
        compose.onNodeWithText("Blue").performClick()
        compose.onNodeWithText("Selected color: Blue").assertIsDisplayed()

        compose.onNodeWithText("Pick Color").performClick()
        compose.onNodeWithText("Red").performClick()
        compose.onNodeWithText("Selected color: Red").assertIsDisplayed()
    }
}
