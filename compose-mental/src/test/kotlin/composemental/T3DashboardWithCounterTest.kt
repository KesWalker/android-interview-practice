package composemental

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
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
class T3DashboardWithCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `header never recomposes when the counter changes`() {
        val counter = mutableStateOf(0)
        var headerRecomposeCount = 0
        compose.setContent {
            MaterialTheme { DashboardWithCounter(counter = counter, onHeaderRecompose = { headerRecomposeCount++ }) }
        }

        compose.onNodeWithText("Dashboard").assertIsDisplayed()
        compose.onNodeWithText("Count: 0").assertIsDisplayed()
        assertEquals(1, headerRecomposeCount)

        counter.value = 1
        compose.waitForIdle()
        compose.onNodeWithText("Count: 1").assertIsDisplayed()
        assertEquals(1, headerRecomposeCount)

        counter.value = 2
        compose.waitForIdle()
        compose.onNodeWithText("Count: 2").assertIsDisplayed()
        assertEquals(1, headerRecomposeCount)
    }
}
