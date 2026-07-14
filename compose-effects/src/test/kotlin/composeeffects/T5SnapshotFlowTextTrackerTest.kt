package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T5SnapshotFlowTextTrackerTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `snapshotFlow emits the initial value, then again on every later change`() {
        compose.setContent { MaterialTheme { SnapshotFlowTextTracker() } }
        compose.waitForIdle()

        // snapshotFlow emits once immediately with whatever value is current.
        compose.onNodeWithText("Emitted: 1").assertIsDisplayed()

        compose.onNodeWithTag("snapshotFlowInput").performTextInput("a")
        compose.waitForIdle()
        compose.onNodeWithText("Emitted: 2").assertIsDisplayed()

        compose.onNodeWithTag("snapshotFlowInput").performTextReplacement("hello")
        compose.waitForIdle()
        compose.onNodeWithText("Emitted: 3").assertIsDisplayed()
    }
}
