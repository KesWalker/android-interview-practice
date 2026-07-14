package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
class T2ToggleBannerTest {
    @get:Rule val compose = createComposeRule()

    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    @Test fun `banner enters and exits with the visible flag`() {
        compose.mainClock.autoAdvance = false
        var visible by mutableStateOf(false)
        compose.setContent { MaterialTheme { ToggleBanner(visible = visible) } }
        pump(2)
        compose.onNodeWithTag("banner").assertDoesNotExist()

        visible = true
        Snapshot.sendApplyNotifications()
        pump(30)
        compose.onNodeWithTag("banner").assertIsDisplayed()

        visible = false
        Snapshot.sendApplyNotifications()
        pump(30)
        compose.onNodeWithTag("banner").assertDoesNotExist()
    }
}
