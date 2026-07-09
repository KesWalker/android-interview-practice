package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StaleWhileRevalidateTest {

    @Test
    fun `emits only the fresh value when nothing is cached yet`() {
        val cache = mutableMapOf<String, String>()

        val emissions = staleWhileRevalidate(cache, "profile") { "server-v1" }

        assertEquals(listOf("server-v1"), emissions)
        assertEquals("server-v1", cache["profile"])
    }

    @Test
    fun `emits the cached value first, then the fresh value`() {
        val cache = mutableMapOf("profile" to "cached-v1")

        val emissions = staleWhileRevalidate(cache, "profile") { "server-v2" }

        assertEquals(listOf("cached-v1", "server-v2"), emissions)
        assertEquals("server-v2", cache["profile"])
    }

    @Test
    fun `only touches the requested key, leaving other cache entries untouched`() {
        val cache = mutableMapOf("profile" to "cached-v1", "settings" to "settings-v1")

        staleWhileRevalidate(cache, "profile") { "server-v2" }

        assertEquals("settings-v1", cache["settings"])
    }
}
