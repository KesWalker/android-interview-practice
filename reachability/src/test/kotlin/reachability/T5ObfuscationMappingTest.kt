package reachability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: deterministic short-name assignment, sorted input for stable output.
class T5ObfuscationMappingTest {
    @Test fun `assigns short names in sorted order`() {
        val result = obfuscationMapping(setOf("Presenter", "MainActivity", "Repository"))
        assertEquals(
            mapOf("MainActivity" to "a", "Presenter" to "b", "Repository" to "c"),
            result
        )
    }

    @Test fun `reserved short names are skipped`() {
        val result = obfuscationMapping(
            setOf("MainActivity", "Presenter", "Repository"),
            reservedShortNames = setOf("a")
        )
        assertEquals(
            mapOf("MainActivity" to "b", "Presenter" to "c", "Repository" to "d"),
            result
        )
    }

    @Test fun `same input always produces the same mapping`() {
        val names = setOf("Zebra", "Alpha")
        assertEquals(obfuscationMapping(names), obfuscationMapping(names))
    }
}
