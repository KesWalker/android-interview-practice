package semantics

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: assertion helpers a test calls on a node it already found.
class T6AssertionsTest {
    @Test fun `assertHasText passes when merged text matches`() {
        assertDoesNotThrow { assertHasText(SemanticsNode(text = "Save"), "Save") }
    }

    @Test fun `assertHasText throws a clear error when merged text doesn't match`() {
        val ex = assertThrows(AssertionError::class.java) {
            assertHasText(SemanticsNode(text = "Save"), "Cancel")
        }

        assertEquals("expected text \"Cancel\" but was \"Save\"", ex.message)
    }

    @Test fun `assertIsEnabled passes for an enabled node`() {
        assertDoesNotThrow { assertIsEnabled(SemanticsNode(enabled = true)) }
    }

    @Test fun `assertIsEnabled throws a clear error for a disabled node`() {
        val ex = assertThrows(AssertionError::class.java) {
            assertIsEnabled(SemanticsNode(enabled = false))
        }

        assertEquals("expected node to be enabled but it was disabled", ex.message)
    }
}
