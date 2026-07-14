package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
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
class T3DisposableListenerEffectTest {
    @get:Rule val compose = createComposeRule()

    private val listener = EffectListener()

    @Test fun `onDispose runs on key change and when the composable leaves composition`() {
        val keyState = mutableStateOf(0)
        val visible = mutableStateOf(true)

        compose.setContent {
            MaterialTheme {
                if (visible.value) {
                    DisposableListenerEffect(key = keyState.value, listener = listener)
                }
            }
        }
        compose.waitForIdle()
        assertEquals(1, listener.registered)
        assertEquals(0, listener.disposed)

        // Changing the key tears down the old registration before the new one runs.
        compose.runOnIdle { keyState.value = 1 }
        compose.waitForIdle()
        assertEquals(2, listener.registered)
        assertEquals(1, listener.disposed)

        // Leaving composition entirely must also dispose, without registering again.
        compose.runOnIdle { visible.value = false }
        compose.waitForIdle()
        assertEquals(2, listener.registered)
        assertEquals(2, listener.disposed)
    }
}
