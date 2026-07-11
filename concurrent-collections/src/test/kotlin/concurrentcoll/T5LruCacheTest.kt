package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: LinkedHashMap access-order + removeEldestEntry as an LRU cache.
class T5LruCacheTest {
    @Test fun `inserting beyond capacity evicts the oldest untouched entry`() {
        val cache = LruCache<String, Int>(2)
        cache.put("a", 1)
        cache.put("b", 2)
        cache.put("c", 3)

        assertNull(cache.get("a"))
        assertEquals(2, cache.get("b"))
        assertEquals(3, cache.get("c"))
    }

    @Test fun `reading an entry protects it from the next eviction`() {
        val cache = LruCache<String, Int>(2)
        cache.put("a", 1)
        cache.put("b", 2)

        cache.get("a")
        cache.put("c", 3)

        assertEquals(1, cache.get("a"))
        assertNull(cache.get("b"))
        assertEquals(3, cache.get("c"))
    }

    @Test fun `keysInOrder reflects least-to-most-recently-used`() {
        val cache = LruCache<String, Int>(3)
        cache.put("a", 1)
        cache.put("b", 2)
        cache.put("c", 3)
        cache.get("a")

        assertEquals(listOf("b", "c", "a"), cache.keysInOrder())
    }
}
