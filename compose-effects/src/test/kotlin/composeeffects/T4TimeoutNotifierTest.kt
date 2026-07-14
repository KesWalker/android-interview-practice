package composeeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Reads `external.value` into a plain val each recomposition, then hands
 * TimeoutNotifier a fresh onTimeout closure over that val. Because `current` is
 * snapshotted fresh on every recomposition, this produces a genuinely different
 * lambda object each time external.value changes, unlike a lambda that reads a
 * `by remember { mutableStateOf(...) }` directly (which would already see live
 * values no matter when it was created). The "current: N" text exists purely so
 * the test can confirm the harness itself has recomposed with the new value.
 */
@Composable
private fun TimeoutNotifierHarness(
    delayMs: Long,
    external: MutableState<Int>,
    result: MutableState<Int>,
) {
    val current = external.value
    Column {
        Text("current: $current")
        TimeoutNotifier(delayMs = delayMs, onTimeout = { result.value = current })
    }
}

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T4TimeoutNotifierTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `onTimeout fires with the latest lambda, not the one captured when the effect launched`() {
        compose.mainClock.autoAdvance = false
        val externalState = mutableStateOf(1)
        val result = mutableStateOf(0)

        compose.setContent {
            MaterialTheme {
                TimeoutNotifierHarness(delayMs = 1000L, external = externalState, result = result)
            }
        }
        waitForText("current: 1")

        // Advance partway through the delay, then recompose with a new onTimeout
        // closure before LaunchedEffect(Unit) ever gets a chance to restart (it
        // can't, its key is Unit).
        compose.mainClock.advanceTimeBy(400L)
        compose.runOnIdle { externalState.value = 2 }
        waitForText("current: 2")

        compose.mainClock.advanceTimeBy(600L)
        compose.waitForIdle()

        // A naive implementation that closes over `onTimeout` directly inside
        // LaunchedEffect would report 1 here (the value at launch time).
        assertEquals(2, result.value)
    }

    // Advances the paused clock one frame at a time, up to a bound, until a
    // node with this exact text shows up. No real delay is ever used: this is
    // still fully driven by the deterministic test clock, just resilient to
    // however many frame ticks a recomposition happens to need to land.
    private fun waitForText(text: String, maxFrames: Int = 30) {
        repeat(maxFrames) {
            if (compose.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty()) return
            compose.mainClock.advanceTimeByFrame()
        }
    }
}
