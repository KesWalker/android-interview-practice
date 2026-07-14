package composemental

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
class T2ScopedCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `clicking the inner counter never recomposes the outer scope`() {
        var parentRecomposeCount = 0
        compose.setContent { MaterialTheme { ScopedCounter(onParentRecompose = { parentRecomposeCount++ }) } }

        assertEquals(1, parentRecomposeCount)
        compose.onNodeWithText("Count: 0").assertIsDisplayed()

        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Count: 1").assertIsDisplayed()
        assertEquals(1, parentRecomposeCount)

        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Count: 3").assertIsDisplayed()
        assertEquals(1, parentRecomposeCount)
    }
}
