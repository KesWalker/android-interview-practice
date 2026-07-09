package di

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class SingletonScopeTest {

    @Test
    fun `returns the same instance on every call`() {
        var createCount = 0
        val provider = SingletonProvider {
            createCount++
            Logger()
        }

        val first = provider.get()
        val second = provider.get()
        val third = provider.get()

        assertSame(first, second)
        assertSame(second, third)
        assertEquals(1, createCount)
    }
}
