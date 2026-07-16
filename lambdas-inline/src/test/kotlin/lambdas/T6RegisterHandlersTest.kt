package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: noinline opts a parameter out of inlining so it can be stored as a
// real object rather than only called directly. You declare registerHandlers
// from scratch in Tasks.kt; the test requires it to be inline, and storing
// `deferred` is what the compiler will refuse until one parameter opts out.
class T6RegisterHandlersTest {
    private fun registerHandlers(immediate: () -> Unit, deferred: () -> Unit, store: MutableList<() -> Unit>) {
        val m = findDeclaration("registerHandlers", 3)
            ?: notDeclaredYet(
                "t6: registerHandlers",
                "Declare an inline function registerHandlers(immediate: () -> Unit, " +
                    "deferred: () -> Unit, store: MutableList<() -> Unit>) that calls " +
                    "immediate right away and adds deferred to store for later.",
            )
        m.requireInline("t6: registerHandlers")
        m.call(immediate, deferred, store)
    }

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
