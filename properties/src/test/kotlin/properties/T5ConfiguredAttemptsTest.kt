package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: Delegates.notNull() for a primitive var that can't use lateinit.
// You declare `maxAttempts` from scratch in Tasks.kt.
class T5ConfiguredAttemptsTest {
    private fun RetryPolicy.maxAttemptsProperty() = Pair(
        property("getMaxAttempts") ?: missing(),
        setter("setMaxAttempts", Int::class.javaPrimitiveType!!) ?: missing(),
    )

    private fun missing(): Nothing = notDeclaredYet(
        "t5: RetryPolicy.maxAttempts",
        "Declare a var `maxAttempts: Int` in RetryPolicy that is assigned after " +
            "construction and throws IllegalStateException if read first. lateinit " +
            "is the obvious reach but it refuses primitive types - the standard " +
            "library has a property delegate for exactly this gap.",
    )

    @Test fun `throws if read before being assigned`() {
        val policy = RetryPolicy()
        val (get, _) = policy.maxAttemptsProperty()
        assertThrows(IllegalStateException::class.java) { get.call(policy) }
    }

    @Test fun `returns the assigned value`() {
        val policy = RetryPolicy()
        val (get, set) = policy.maxAttemptsProperty()
        set.call(policy, 3)
        assertEquals(3, get.call(policy))
    }

    @Test fun `reflects a later reassignment`() {
        val policy = RetryPolicy()
        val (get, set) = policy.maxAttemptsProperty()
        set.call(policy, 3)
        set.call(policy, 5)
        assertEquals(5, get.call(policy))
    }
}
