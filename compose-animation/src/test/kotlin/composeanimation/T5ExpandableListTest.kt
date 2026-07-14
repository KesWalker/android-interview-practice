package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Assert.assertEquals
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
class T5ExpandableListTest {
    @get:Rule val compose = createComposeRule()

    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    @Test fun `container grows smoothly instead of snapping`() {
        compose.mainClock.autoAdvance = false
        var expanded by mutableStateOf(false)
        compose.setContent { MaterialTheme { ExpandableList(expanded = expanded) } }
        pump(2)
        val collapsedHeight = compose.onNodeWithTag("list").fetchSemanticsNode().size.height

        expanded = true
        Snapshot.sendApplyNotifications()
        pump(6)
        val midHeight = compose.onNodeWithTag("list").fetchSemanticsNode().size.height
        assertTrue("height should be growing", midHeight > collapsedHeight)

        pump(30)
        val settledHeight = compose.onNodeWithTag("list").fetchSemanticsNode().size.height
        assertTrue("settled height should exceed the mid-animation height", settledHeight > midHeight)

        pump(6)
        val stillSettledHeight = compose.onNodeWithTag("list").fetchSemanticsNode().size.height
        assertEquals("height should have stopped changing", settledHeight, stillSettledHeight)
    }
}
