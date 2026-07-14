package effects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: only publish a side effect after a successful commit.
class T6SideEffectTest {
    @Test fun `publishes the result after a successful commit`() {
        var published: String? = null

        val result = commitThenPublish({ "done" }) { published = it }

        assertEquals("done", result)
        assertEquals("done", published)
    }

    @Test fun `does not publish when commit throws`() {
        var published = false

        assertThrows(IllegalStateException::class.java) {
            commitThenPublish<Unit>({ throw IllegalStateException("boom") }) { published = true }
        }

        assertFalse(published)
    }

    @Test fun `propagates the original exception`() {
        val ex = assertThrows(RuntimeException::class.java) {
            commitThenPublish<Int>({ throw RuntimeException("failed") }) { }
        }

        assertEquals("failed", ex.message)
    }
}
