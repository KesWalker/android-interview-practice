package savedstate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: reject saved state that's too big to persist.
class SizeLimitTest {
    @Test fun `state within the limit is returned unchanged`() {
        val state = mapOf("id" to 5, "active" to true)
        assertEquals(state, ensureWithinSizeLimit(state, 1000))
    }

    @Test fun `state exactly at the limit is not rejected`() {
        // key "a" -> 1 char * 2 = 2 bytes, Int value -> 4 bytes, total 6 bytes.
        val state = mapOf("a" to 1)
        assertEquals(state, ensureWithinSizeLimit(state, 6))
    }

    @Test fun `state over the limit throws`() {
        val state = mapOf("blob" to "x".repeat(1000))
        assertThrows(StateTooLargeException::class.java) {
            ensureWithinSizeLimit(state, 100)
        }
    }
}
