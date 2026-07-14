package composemental

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
class T5DeferredOffsetBoxTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `changing the offset never recomposes the box`() {
        val offsetX = mutableStateOf(0)
        var recomposeCount = 0
        compose.setContent {
            MaterialTheme { DeferredOffsetBox(offsetX = offsetX, onRecompose = { recomposeCount++ }) }
        }

        assertEquals(1, recomposeCount)

        offsetX.value = 50
        compose.waitForIdle()
        assertEquals(1, recomposeCount)

        offsetX.value = 100
        compose.waitForIdle()
        assertEquals(1, recomposeCount)
    }
}
