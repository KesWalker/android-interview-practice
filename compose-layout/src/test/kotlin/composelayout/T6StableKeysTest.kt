package composelayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
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
class T6StableKeysTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun `toggled state follows its item's key through a reorder, not its index`() {
        val itemsState = mutableStateOf(
            listOf(
                ToggleItem("1", "Alpha"),
                ToggleItem("2", "Bravo"),
                ToggleItem("3", "Charlie"),
            )
        )
        compose.setContent { MaterialTheme { ToggleList(items = itemsState.value) } }

        // Toggle Bravo, at index 1.
        compose.onNodeWithTag("item-2").performClick()
        compose.onNodeWithTag("label-2", useUnmergedTree = true).assertTextEquals("Bravo - EXPANDED")

        // Reorder: Bravo moves from index 1 to index 2, Charlie takes its old spot.
        compose.runOnIdle {
            itemsState.value = listOf(
                ToggleItem("1", "Alpha"),
                ToggleItem("3", "Charlie"),
                ToggleItem("2", "Bravo"),
            )
        }

        // Bravo's expanded state followed it to its new position...
        compose.onNodeWithTag("label-2", useUnmergedTree = true).assertTextEquals("Bravo - EXPANDED")
        // ...and Charlie, now sitting in Bravo's old slot, did not inherit it.
        compose.onNodeWithTag("label-3", useUnmergedTree = true).assertTextEquals("Charlie")
    }
}
