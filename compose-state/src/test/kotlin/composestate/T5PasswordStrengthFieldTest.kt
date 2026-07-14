package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
class T5PasswordStrengthFieldTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `badge only recomposes when strength crosses the threshold`() {
        val password = mutableStateOf("")
        var badgeRecomposeCount = 0

        compose.setContent {
            MaterialTheme {
                PasswordStrengthField(password = password, onBadgeRecompose = { badgeRecomposeCount++ })
            }
        }
        compose.onNodeWithText("Weak").assertIsDisplayed()
        assertEquals(1, badgeRecomposeCount)

        password.value = "a"
        compose.waitForIdle()
        password.value = "ab"
        compose.waitForIdle()
        password.value = "abc"
        compose.waitForIdle()
        compose.onNodeWithText("Weak").assertIsDisplayed()
        assertEquals(1, badgeRecomposeCount)

        password.value = "abcdefgh"
        compose.waitForIdle()
        compose.onNodeWithText("Strong").assertIsDisplayed()
        assertEquals(2, badgeRecomposeCount)

        password.value = "abcdefghi"
        compose.waitForIdle()
        compose.onNodeWithText("Strong").assertIsDisplayed()
        assertEquals(2, badgeRecomposeCount)
    }
}
