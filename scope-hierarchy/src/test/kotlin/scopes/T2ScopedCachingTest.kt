package scopes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

// Task 2: scoped bindings cache per component instance, unscoped ones don't.
class T2ScopedCachingTest {
    @Test fun `a scoped binding builds once and reuses the same instance`() {
        var buildCount = 0
        val provider = BindingProvider(scoped = true) { buildCount++ }

        val first = provider.get()
        val second = provider.get()
        val third = provider.get()

        assertEquals(first, second)
        assertEquals(second, third)
        assertEquals(1, buildCount)
    }

    @Test fun `an unscoped binding builds a fresh instance on every call`() {
        var buildCount = 0
        val provider = BindingProvider(scoped = false) { buildCount++ }

        val first = provider.get()
        val second = provider.get()
        val third = provider.get()

        assertNotEquals(first, second)
        assertNotEquals(second, third)
        assertEquals(3, buildCount)
    }
}
