package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.assertHeightIsEqualTo
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
class T3ExpandableCardTest {
    @get:Rule val compose = createComposeRule()

    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    @Test fun `width and height animate together off one state`() {
        compose.mainClock.autoAdvance = false
        var state by mutableStateOf(CardState.Collapsed)
        compose.setContent { MaterialTheme { ExpandableCard(state = state) } }
        pump(2)
        compose.onNodeWithTag("card").assertWidthIsEqualTo(80.dp)
        compose.onNodeWithTag("card").assertHeightIsEqualTo(40.dp)

        val startWidth = compose.onNodeWithTag("card").fetchSemanticsNode().size.width
        val startHeight = compose.onNodeWithTag("card").fetchSemanticsNode().size.height

        state = CardState.Expanded
        Snapshot.sendApplyNotifications()
        pump(6)
        val midWidth = compose.onNodeWithTag("card").fetchSemanticsNode().size.width
        val midHeight = compose.onNodeWithTag("card").fetchSemanticsNode().size.height
        assertTrue("width should be animating", midWidth > startWidth)
        assertTrue("height should be animating too", midHeight > startHeight)

        pump(30)
        compose.onNodeWithTag("card").assertWidthIsEqualTo(200.dp)
        compose.onNodeWithTag("card").assertHeightIsEqualTo(120.dp)
    }
}
