package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T2ClickLaunchedCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `each click launches its own coroutine that increments after the delay`() {
        compose.mainClock.autoAdvance = false
        compose.setContent { MaterialTheme { ClickLaunchedCounter(delayMs = 1000L) } }

        compose.onNodeWithText("Count: 0").assertIsDisplayed()
        compose.onNodeWithText("Add").performClick()

        // The increment doesn't happen synchronously with the click.
        compose.mainClock.advanceTimeBy(500L)
        compose.onNodeWithText("Count: 0").assertIsDisplayed()

        compose.mainClock.advanceTimeBy(500L)
        compose.onNodeWithText("Count: 1").assertIsDisplayed()

        // Two more clicks should each start their own independent coroutine.
        compose.onNodeWithText("Add").performClick()
        compose.onNodeWithText("Add").performClick()
        compose.mainClock.advanceTimeBy(1000L)
        compose.onNodeWithText("Count: 3").assertIsDisplayed()
    }
}
