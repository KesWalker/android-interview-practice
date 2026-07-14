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
class T6TodoListScreenTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `adding a task is reflected immediately in the UI`() {
        compose.setContent { MaterialTheme { TodoListScreen() } }

        compose.onNodeWithText("Count: 0").assertIsDisplayed()

        compose.onNodeWithText("Add task").performClick()
        compose.onNodeWithText("Count: 1").assertIsDisplayed()
        compose.onNodeWithText("Task 1").assertIsDisplayed()

        compose.onNodeWithText("Add task").performClick()
        compose.onNodeWithText("Count: 2").assertIsDisplayed()
        compose.onNodeWithText("Task 2").assertIsDisplayed()
    }
}
