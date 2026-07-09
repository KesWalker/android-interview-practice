package savedstate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: fill defaults' keys from saved state.
class RestoreStateTest {
    @Test fun `missing keys fall back to defaults`() {
        val defaults = mapOf("query" to "", "page" to 1, "sortAsc" to true)
        val saved = mapOf("query" to "kotlin")

        assertEquals(
            mapOf("query" to "kotlin", "page" to 1, "sortAsc" to true),
            restoreState(saved, defaults)
        )
    }

    @Test fun `keys not in defaults are ignored`() {
        val defaults = mapOf("page" to 1)
        val saved = mapOf("page" to 5, "extra" to "ignored")

        assertEquals(mapOf("page" to 5), restoreState(saved, defaults))
    }

    @Test fun `empty saved state returns defaults unchanged`() {
        val defaults = mapOf("page" to 1, "query" to "")
        assertEquals(defaults, restoreState(emptyMap(), defaults))
    }
}
