package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: fail-fast iteration / ConcurrentModificationException, fixed via iterator.remove().
class T6RemoveMatchingTest {
    @Test fun `removes only the entries whose value matches the predicate`() {
        val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3, "d" to 4)

        removeMatching(map) { it % 2 == 0 }

        assertEquals(mapOf("a" to 1, "c" to 3), map)
    }

    @Test fun `removes every entry when all values match`() {
        val map = mutableMapOf("a" to 1, "b" to 2)

        removeMatching(map) { true }

        assertEquals(emptyMap<String, Int>(), map)
    }

    @Test fun `leaves the map untouched when no values match`() {
        val map = mutableMapOf("a" to 1, "b" to 2)

        removeMatching(map) { it > 100 }

        assertEquals(mapOf("a" to 1, "b" to 2), map)
    }

    @Test fun `removing from a large map while iterating throws no exception`() {
        val map = (0 until 1000).associateWith { it }.toMutableMap()

        removeMatching(map) { it % 2 == 0 }

        assertEquals(500, map.size)
        assertEquals(setOf(1, 3, 5), map.keys.filter { it < 6 }.toSet())
    }
}
