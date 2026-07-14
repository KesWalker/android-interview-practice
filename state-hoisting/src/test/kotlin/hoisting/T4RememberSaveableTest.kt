package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: rememberSaveable for a single slot, restore-or-init.
class T4RememberSaveableTest {
    @Test fun `restores a saveable-safe value when present`() {
        val saved = mapOf("count" to 5)

        val result = rememberSaveable("count", saved) { 0 }

        assertEquals(5, result)
    }

    @Test fun `falls back to initial when the key is missing`() {
        val saved = emptyMap<String, Any?>()

        val result = rememberSaveable("count", saved) { 42 }

        assertEquals(42, result)
    }

    @Test fun `falls back to initial when the saved value is not saveable-safe`() {
        val saved = mapOf("draft" to { "never called" })

        val result = rememberSaveable("draft", saved) { "fresh" }

        assertEquals("fresh", result)
    }
}
