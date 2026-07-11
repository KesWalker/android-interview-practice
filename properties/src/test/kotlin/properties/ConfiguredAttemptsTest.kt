package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: Delegates.notNull() for a primitive var that can't use lateinit.
class ConfiguredAttemptsTest {
    @Test fun `throws if read before being assigned`() {
        val policy = RetryPolicy()
        assertThrows(IllegalStateException::class.java) { configuredAttempts(policy) }
    }

    @Test fun `returns the assigned value`() {
        val policy = RetryPolicy()
        policy.maxAttempts = 3
        assertEquals(3, configuredAttempts(policy))
    }

    @Test fun `reflects a later reassignment`() {
        val policy = RetryPolicy()
        policy.maxAttempts = 3
        policy.maxAttempts = 5
        assertEquals(5, configuredAttempts(policy))
    }
}
