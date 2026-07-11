package di

import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class FlowScopeTest {

    @Test
    fun `resolving the same key twice in one scope returns the same instance`() {
        val scope = FlowScope()

        val first = scope.resolve("logger") { Logger() }
        val second = scope.resolve("logger") { Logger() }

        assertSame(first, second)
    }

    @Test
    fun `a different scope never shares another scope's cache`() {
        val scopeA = FlowScope()
        val scopeB = FlowScope()

        val fromA = scopeA.resolve("logger") { Logger() }
        val fromB = scopeB.resolve("logger") { Logger() }

        assertNotSame(fromA, fromB)
    }

    @Test
    fun `resolve after release builds a fresh instance for the same key`() {
        val scope = FlowScope()
        val first = scope.resolve("logger") { Logger() }

        scope.release()
        val second = scope.resolve("logger") { Logger() }

        assertNotSame(first, second)
    }
}
