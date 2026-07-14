package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T1CounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `count starts at zero and increments on click`() {
        compose.setContent { MaterialTheme { Counter() } }
        compose.onNodeWithText("Count: 0").assertIsDisplayed()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Count: 1").assertIsDisplayed()
    }

    @Test fun `count survives an unrelated recomposition`() {
        compose.setContent { MaterialTheme { Counter() } }
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()
        // A second click forces this composable to recompose again. If count
        // isn't held in remember, the click that changes it also resets it,
        // and we'd never see "Count: 2".
        compose.onNodeWithText("Count: 2").assertIsDisplayed()
    }
}
