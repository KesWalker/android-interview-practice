package di

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

class T3FactoryScopeTest {

    @Test
    fun `returns a new instance on every call`() {
        var createCount = 0
        val provider = FactoryProvider {
            createCount++
            Logger()
        }

        val first = provider.get()
        val second = provider.get()

        assertNotSame(first, second)
        assertEquals(2, createCount)
    }
}
