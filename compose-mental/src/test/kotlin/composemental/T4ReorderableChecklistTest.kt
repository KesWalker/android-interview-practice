package composemental

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
class T4ReorderableChecklistTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `checked state follows item identity through a reorder`() {
        var items by mutableStateOf(listOf(ChecklistItem(1, "Buy milk"), ChecklistItem(2, "Walk the dog")))
        compose.setContent { MaterialTheme { ReorderableChecklist(items = items) } }

        compose.onNodeWithTag("checkbox-1").performClick()
        compose.onNodeWithTag("checkbox-1").assertIsOn()
        compose.onNodeWithTag("checkbox-2").assertIsOff()

        items = items.reversed()
        compose.waitForIdle()

        compose.onNodeWithTag("checkbox-1").assertIsOn()
        compose.onNodeWithTag("checkbox-2").assertIsOff()
    }
}
