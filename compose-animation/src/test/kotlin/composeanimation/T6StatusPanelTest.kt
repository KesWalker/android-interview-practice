package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T6StatusPanelTest {
    @get:Rule val compose = createComposeRule()

    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    @Test fun `content crossfades between states`() {
        compose.mainClock.autoAdvance = false
        var state by mutableStateOf(LoadState.Loading)
        compose.setContent { MaterialTheme { StatusPanel(state = state) } }
        pump(2)
        compose.onNodeWithText("Loading...").assertIsDisplayed()

        state = LoadState.Loaded
        Snapshot.sendApplyNotifications()
        pump(30)
        compose.onNodeWithText("Loading...").assertDoesNotExist()
        compose.onNodeWithText("Done!").assertIsDisplayed()
    }
}
