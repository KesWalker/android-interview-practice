package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: fold precedence-ordered layers, later layer's keys win.
class T3MergeResourceValuesTest {
    @Test fun `no layers, empty result`() {
        assertEquals(emptyMap<String, String>(), mergeResourceValues(emptyList()))
    }

    @Test fun `later layer overwrites same key, unique keys survive`() {
        val layers = listOf(
            mapOf("a" to "1", "b" to "2"),
            mapOf("b" to "20", "c" to "3")
        )
        assertEquals(mapOf("a" to "1", "b" to "20", "c" to "3"), mergeResourceValues(layers))
    }

    @Test fun `three layers fold left to right`() {
        val layers = listOf(
            mapOf("key" to "main"),
            mapOf("key" to "buildType"),
            mapOf("key" to "variant")
        )
        assertEquals(mapOf("key" to "variant"), mergeResourceValues(layers))
    }
}
