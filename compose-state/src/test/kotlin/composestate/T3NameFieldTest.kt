package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextReplacement
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
class T3NameFieldTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `is fully controlled by its value parameter, not internal state`() {
        compose.setContent {
            MaterialTheme {
                NameField(value = "Ada", onValueChange = {}, modifier = Modifier.testTag("name-field"))
            }
        }
        compose.onNodeWithText("Ada").assertIsDisplayed()

        compose.onNodeWithTag("name-field").performTextReplacement("Grace")

        // onValueChange is a no-op above, so a stateless field has nothing of
        // its own to fall back on: it must still show "Ada".
        compose.onNodeWithText("Ada").assertIsDisplayed()
    }

    @Test fun `reports edits upward through onValueChange`() {
        var lastValue = ""
        compose.setContent {
            MaterialTheme {
                NameField(
                    value = "Ada",
                    onValueChange = { lastValue = it },
                    modifier = Modifier.testTag("name-field"),
                )
            }
        }

        compose.onNodeWithTag("name-field").performTextReplacement("Grace")

        assertEquals("Grace", lastValue)
    }
}
