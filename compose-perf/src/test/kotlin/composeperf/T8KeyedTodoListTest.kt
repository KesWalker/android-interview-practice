package composeperf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
class T8KeyedTodoListTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `checked state follows the todo's id across a reorder, not its position`() {
        var items by mutableStateOf(listOf(Todo(1, "Write tests"), Todo(2, "Ship it")))

        compose.setContent { MaterialTheme { KeyedTodoList(items = items) } }

        compose.onNodeWithTag("checkbox-1").assertIsOff()
        compose.onNodeWithTag("checkbox-1").performClick()
        compose.onNodeWithTag("checkbox-1").assertIsOn()
        compose.onNodeWithTag("checkbox-2").assertIsOff()

        // Reorder the same two todos. If rows were keyed by position instead of
        // id, this swap would drag the "checked" state along with the slot,
        // landing it on the wrong todo.
        items = listOf(items[1], items[0])
        compose.waitForIdle()

        compose.onNodeWithTag("checkbox-1").assertIsOn()
        compose.onNodeWithTag("checkbox-2").assertIsOff()
    }
}
