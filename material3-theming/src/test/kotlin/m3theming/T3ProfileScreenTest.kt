package m3theming

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.junit.runner.RunWith

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T3ProfileScreenTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `screen shows the top app bar title and the profile card`() {
        compose.setContent {
            MaterialTheme { ProfileScreen(onSaveClick = {}) }
        }
        compose.onNodeWithText("Profile").assertIsDisplayed()
        compose.onNodeWithText("Kes Walker").assertIsDisplayed()
    }

    @Test fun `clicking Save invokes the callback`() {
        var saveCount = 0
        compose.setContent {
            MaterialTheme { ProfileScreen(onSaveClick = { saveCount++ }) }
        }
        compose.onNodeWithText("Save").assertIsDisplayed()
        compose.onNodeWithText("Save").performClick()
        assertEquals(1, saveCount)
    }
}
