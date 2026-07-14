package composeperf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T6DeferredOffsetBoxTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `never recomposes while the offset changes, only re-lays-out`() {
        val offsetPx = mutableStateOf(0)
        var recomposeCount = 0

        compose.setContent {
            MaterialTheme { DeferredOffsetBox(offsetPx = offsetPx, onRecompose = { recomposeCount++ }) }
        }
        assertEquals(1, recomposeCount)

        repeat(5) { i ->
            offsetPx.value = (i + 1) * 10
            compose.waitForIdle()
        }

        // The box moved five times, but Modifier.offset { } reads offsetPx during
        // placement, not composition, so the composable itself never reran.
        assertEquals(1, recomposeCount)
    }
}
