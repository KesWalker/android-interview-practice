package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: deferred, cached computation. You declare `value` from scratch in
// Tasks.kt; this test finds it by name once it exists.
class T1LazyConfigTest {
    private fun valueOf(config: LazyConfig): Any? {
        val getter = config.property("getValue")
            ?: notDeclaredYet(
                "t1: LazyConfig.value",
                "Declare a val `value: String` in LazyConfig whose initializer calls " +
                    "loader() on the first read only. Hand-rolling a cached getter is " +
                    "the hard way - Kotlin has a property delegate for exactly this.",
            )
        return getter.call(config)
    }

    @Test fun `does not compute the value until it's read`() {
        var calls = 0
        val config = LazyConfig { calls++; "loaded" }
        config.property("getValue") ?: valueOf(config)
        assertEquals(0, calls)
    }

    @Test fun `returns the loader's result`() {
        val config = LazyConfig { "hello" }
        assertEquals("hello", valueOf(config))
    }

    @Test fun `only ever calls the loader once, however many times it's read`() {
        var calls = 0
        val config = LazyConfig { calls++; "call-$calls" }

        val first = valueOf(config)
        val second = valueOf(config)
        val third = valueOf(config)

        assertEquals(1, calls)
        assertEquals(first, second)
        assertEquals(first, third)
    }
}
