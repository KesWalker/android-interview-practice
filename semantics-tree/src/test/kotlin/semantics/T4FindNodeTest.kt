package semantics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 4: the finder's zero-match and many-match ambiguity errors.
class T4FindNodeTest {
    private val tree = SemanticsNode(
        children = listOf(
            SemanticsNode(text = "Save", testTag = "save-1"),
            SemanticsNode(text = "Save", testTag = "save-2"),
            SemanticsNode(text = "Cancel", testTag = "cancel")
        )
    )

    @Test fun `returns the single matching node`() {
        val found = findNode(tree, "testTag \"cancel\"", hasTestTag("cancel"))

        assertEquals("Cancel", found.text)
    }

    @Test fun `throws a clear error when nothing matches`() {
        val ex = assertThrows(AssertionError::class.java) {
            findNode(tree, "text \"Missing\"", hasText("Missing"))
        }

        assertEquals("no node found matching: text \"Missing\"", ex.message)
    }

    @Test fun `throws a clear error when more than one node matches`() {
        val ex = assertThrows(AssertionError::class.java) {
            findNode(tree, "text \"Save\"", hasText("Save"))
        }

        assertEquals("2 nodes found matching: text \"Save\", expected exactly one", ex.message)
    }
}
