package composeanimation

import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
class T4SlidingChipTest {
    @get:Rule val compose = createComposeRule()

    private fun pump(frames: Int) {
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            Snapshot.sendApplyNotifications()
            shadowOf(Looper.getMainLooper()).idle()
        }
    }

    private fun currentOffset(): Int {
        val node = compose.onNodeWithTag("chipValue").fetchSemanticsNode()
        val text = node.config.getOrNull(SemanticsProperties.Text)?.joinToString { it.text }.orEmpty()
        return text.substringAfter("Offset: ").trim().toInt()
    }

    @Test fun `retargeting mid-flight reverses the animation`() {
        compose.mainClock.autoAdvance = false
        compose.setContent { MaterialTheme { SlidingChip() } }
        pump(2)

        val startOffset = currentOffset()

        compose.onNodeWithText("Right").performClick()
        Snapshot.sendApplyNotifications()
        pump(8)
        val midOffset = currentOffset()
        assertTrue("chip should have moved right (was $startOffset, now $midOffset)", midOffset > startOffset)

        // Interrupt the in-flight tween by retargeting toward the start.
        compose.onNodeWithText("Left").performClick()
        Snapshot.sendApplyNotifications()
        pump(60)
        val endOffset = currentOffset()

        assertTrue("chip should have reversed course after retargeting (mid $midOffset, end $endOffset)", endOffset < midOffset)
        assertTrue("chip should settle back near its start (start $startOffset, end $endOffset)", Math.abs(endOffset - startOffset) <= 1)
    }
}
