package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertNotNull
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
class T4LoginFormStateTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `same state instance survives an unrelated recomposition`() {
        var recomposeTrigger by mutableStateOf(0)
        var stateBefore: LoginFormState? = null
        var stateAfter: LoginFormState? = null

        compose.setContent {
            MaterialTheme {
                val trigger = recomposeTrigger
                val state = rememberLoginFormState()
                if (trigger == 0) stateBefore = state else stateAfter = state
                LoginForm(state = state)
            }
        }

        recomposeTrigger = 1
        compose.waitForIdle()

        assertNotNull(stateBefore)
        assertNotNull(stateAfter)
        assertTrue(stateBefore === stateAfter)
    }
}
