package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: lazy computation, cached after the first read.
class ConfigCacheTest {
    @Test fun `does not call loader until value is read`() {
        var calls = 0
        cachedConfig { calls++; "prod" }
        assertEquals(0, calls)
    }

    @Test fun `computes the value on first read`() {
        val config = cachedConfig { "prod" }
        assertEquals("prod", config.value)
    }

    @Test fun `only calls loader once even after repeated reads`() {
        var calls = 0
        val config = cachedConfig { calls++; "v$calls" }
        config.value
        config.value
        assertEquals(1, calls)
        assertEquals("v1", config.value)
    }
}
