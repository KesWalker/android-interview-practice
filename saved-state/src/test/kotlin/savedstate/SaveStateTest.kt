package savedstate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: filter down to saved-state-safe types.
class SaveStateTest {
    @Test fun `keeps scalars, null and lists of scalars, drops everything else`() {
        val input = mapOf(
            "name" to "Ada",
            "age" to 30,
            "ratio" to 1.5,
            "tags" to listOf("kotlin", "android"),
            "empty" to null,
            "mixedList" to listOf("ok", Any()),
            "callback" to { println("never called") },
            "raw" to Any()
        )

        val expected = mapOf(
            "name" to "Ada",
            "age" to 30,
            "ratio" to 1.5,
            "tags" to listOf("kotlin", "android"),
            "empty" to null
        )

        assertEquals(expected, saveState(input))
    }

    @Test fun `all-safe map is unchanged`() {
        val input = mapOf("id" to 1L, "active" to true, "score" to 9.5f)
        assertEquals(input, saveState(input))
    }

    @Test fun `empty map stays empty`() {
        assertEquals(emptyMap<String, Any?>(), saveState(emptyMap()))
    }
}
