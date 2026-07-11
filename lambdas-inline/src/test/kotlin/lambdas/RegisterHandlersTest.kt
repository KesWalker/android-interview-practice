package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: noinline opts a parameter out of inlining so it can be stored as a
// real object (here, added to a MutableList) rather than only called directly.
class RegisterHandlersTest {
    @Test fun `immediate runs synchronously`() {
        var immediateRan = false
        val store = mutableListOf<() -> Unit>()
        registerHandlers({ immediateRan = true }, {}, store)
        assertTrue(immediateRan)
    }

    @Test fun `deferred is stored, not run, until the test invokes it`() {
        var deferredRan = false
        val store = mutableListOf<() -> Unit>()
        registerHandlers({}, { deferredRan = true }, store)

        assertEquals(1, store.size)
        assertFalse(deferredRan)

        store[0]()
        assertTrue(deferredRan)
    }
}
