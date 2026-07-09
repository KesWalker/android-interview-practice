package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: deferred, cached computation.
class LazyConfigTest {
    @Test fun `does not compute the value until it's read`() {
        var calls = 0
        LazyConfig { calls++; "loaded" }
        assertEquals(0, calls)
    }

    @Test fun `returns the loader's result`() {
        val config = LazyConfig { "hello" }
        assertEquals("hello", config.value)
    }

    @Test fun `only ever calls the loader once, however many times it's read`() {
        var calls = 0
        val config = LazyConfig { calls++; "call-$calls" }

        val first = config.value
        val second = config.value
        val third = config.value

        assertEquals(1, calls)
        assertEquals(first, second)
        assertEquals(first, third)
    }
}
