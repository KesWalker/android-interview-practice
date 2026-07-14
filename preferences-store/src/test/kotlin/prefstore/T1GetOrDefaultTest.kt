package prefstore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: default value when a key is missing.
class T1GetOrDefaultTest {
    private val count = intKey("count")
    private val name = stringKey("name")

    @Test fun `returns the stored value when present`() {
        val prefs = Preferences(mapOf("count" to 5))
        assertEquals(5, prefs.getOrDefault(count, default = 0))
    }

    @Test fun `returns the default when the key is missing`() {
        val prefs = Preferences(emptyMap())
        assertEquals(0, prefs.getOrDefault(count, default = 0))
    }

    @Test fun `different keys don't collide`() {
        val prefs = Preferences(mapOf("count" to 5))
        assertEquals("anon", prefs.getOrDefault(name, default = "anon"))
    }
}
