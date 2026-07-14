package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertTrue
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
class T1PulseDotTest {
    @get:Rule val compose = createComposeRule()

    // Robolectric doesn't relayout on its own while the clock is paused, so
    // every step here manually flushes the pending snapshot writes and the
    // Android measure/layout pass, one simulated frame at a time.
    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    @Test fun `size animates from collapsed to expanded over the clock`() {
        compose.mainClock.autoAdvance = false
        var expanded by mutableStateOf(false)
        compose.setContent { MaterialTheme { PulseDot(expanded = expanded) } }
        pump(2)

        val start = compose.onNodeWithTag("dot").fetchSemanticsNode().size.width

        expanded = true
        Snapshot.sendApplyNotifications()
        pump(6)
        val mid = compose.onNodeWithTag("dot").fetchSemanticsNode().size.width

        pump(30)
        val end = compose.onNodeWithTag("dot").fetchSemanticsNode().size.width

        assertTrue("mid ($mid) should be past start ($start)", mid > start)
        assertTrue("end ($end) should be past mid ($mid)", end > mid)
        compose.onNodeWithTag("dot").assertWidthIsEqualTo(96.dp)
    }
}
