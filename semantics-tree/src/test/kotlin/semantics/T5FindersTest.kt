package semantics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: onNodeWithText / onNodeWithTag wired onto findNode.
class T5FindersTest {
    private val tree = SemanticsNode(
        children = listOf(
            SemanticsNode(text = "Save", testTag = "save-button"),
            SemanticsNode(text = "Save", testTag = "save-icon"),
            SemanticsNode(text = "Cancel", testTag = "cancel-button")
        )
    )

    @Test fun `onNodeWithText finds the unique node with that text`() {
        val found = onNodeWithText(tree, "Cancel")

        assertEquals("cancel-button", found.testTag)
    }

    @Test fun `onNodeWithTag finds the unique node with that tag`() {
        val found = onNodeWithTag(tree, "save-icon")

        assertEquals("Save", found.text)
    }

    @Test fun `onNodeWithText reports ambiguity the same way findNode does`() {
        val ex = assertThrows(AssertionError::class.java) {
            onNodeWithText(tree, "Save")
        }

        assertEquals("2 nodes found matching: text \"Save\", expected exactly one", ex.message)
    }
}
