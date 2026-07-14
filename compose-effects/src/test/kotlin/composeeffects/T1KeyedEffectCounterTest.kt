package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T1KeyedEffectCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `changing the key cancels the in-flight effect instead of letting it complete`() {
        compose.mainClock.autoAdvance = false
        val keyState = mutableStateOf(0)
        compose.setContent {
            MaterialTheme { KeyedEffectCounter(key = keyState.value, delayMs = 1000L) }
        }

        // The effect launches immediately: started bumps right away. A newly
        // launched coroutine needs a few frame ticks to actually dispatch and
        // run, even before it hits its first suspension point, so poll for it
        // with the clock still paused rather than relying on a fixed count.
        waitForText("Started: 1")
        compose.onNodeWithText("Started: 1").assertIsDisplayed()
        compose.onNodeWithText("Completed: 0").assertIsDisplayed()

        // Halfway through the delay, nothing has completed yet.
        compose.mainClock.advanceTimeBy(400L)
        compose.onNodeWithText("Completed: 0").assertIsDisplayed()

        // Changing the key must cancel the first effect's delay outright and
        // launch a brand new one.
        compose.runOnIdle { keyState.value = 1 }
        waitForText("Started: 2")
        compose.onNodeWithText("Started: 2").assertIsDisplayed()

        // Advance past a full delay window. If the old effect had NOT been
        // cancelled it would also complete here, pushing completed to 2.
        compose.mainClock.advanceTimeBy(1000L)
        compose.onNodeWithText("Completed: 1").assertIsDisplayed()
    }

    // Advances the paused clock one frame at a time, up to a bound, until a
    // node with this exact text shows up. No real delay is ever used: this is
    // still fully driven by the deterministic test clock, just resilient to
    // however many frame ticks a coroutine launch happens to need to dispatch.
    private fun waitForText(text: String, maxFrames: Int = 50) {
        repeat(maxFrames) {
            if (compose.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty()) return
            compose.mainClock.advanceTimeByFrame()
        }
    }
}
