package composeperf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
class T1RecomposeCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `renders value and recomposes exactly once per value change`() {
        var value by mutableStateOf(0)
        var recomposeCount = 0

        compose.setContent {
            MaterialTheme { RecomposeCounter(value = value, onRecompose = { recomposeCount++ }) }
        }
        compose.onNodeWithText("Value: 0").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        value = 1
        compose.waitForIdle()
        compose.onNodeWithText("Value: 1").assertIsDisplayed()
        assertEquals(2, recomposeCount)

        value = 2
        compose.waitForIdle()
        compose.onNodeWithText("Value: 2").assertIsDisplayed()
        assertEquals(3, recomposeCount)
    }
}
