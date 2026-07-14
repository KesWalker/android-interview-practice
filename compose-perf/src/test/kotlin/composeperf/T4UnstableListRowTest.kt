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
class T4UnstableListRowTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `recomposes on every parent recomposition, even with an identical list`() {
        var trigger by mutableStateOf(0)
        var recomposeCount = 0

        compose.setContent {
            MaterialTheme {
                @Suppress("UNUSED_EXPRESSION") trigger
                // A fresh List instance with the same two elements every time.
                UnstableListRow(items = listOf("a", "b"), onRecompose = { recomposeCount++ })
            }
        }
        compose.onNodeWithText("Items: 2").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        trigger = 1
        compose.waitForIdle()
        assertEquals(2, recomposeCount)

        trigger = 2
        compose.waitForIdle()
        assertEquals(3, recomposeCount)
    }
}
