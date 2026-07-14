package imagecache

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 3: eviction picks the least-recently-USED entry, and get() is a use.
class T3LruEvictionTest {
    @Test fun `evicts the least-recently-used entry when over budget`() {
        val cache = LruMemoryCache(maxBytes = 3)
        cache.put("a", byteArrayOf(1))
        cache.put("b", byteArrayOf(1))
        cache.put("c", byteArrayOf(1))

        cache.put("d", byteArrayOf(1))

        assertNull(cache.get("a"))
        assertArrayEquals(byteArrayOf(1), cache.get("b"))
        assertArrayEquals(byteArrayOf(1), cache.get("c"))
        assertArrayEquals(byteArrayOf(1), cache.get("d"))
    }

    @Test fun `a get counts as a use, protecting the entry from eviction`() {
        val cache = LruMemoryCache(maxBytes = 3)
        cache.put("a", byteArrayOf(1))
        cache.put("b", byteArrayOf(1))
        cache.put("c", byteArrayOf(1))

        cache.get("a") // touch "a" so "b" becomes the least-recently-used
        cache.put("d", byteArrayOf(1))

        assertNull(cache.get("b"))
        assertArrayEquals(byteArrayOf(1), cache.get("a"))
        assertArrayEquals(byteArrayOf(1), cache.get("c"))
        assertArrayEquals(byteArrayOf(1), cache.get("d"))
    }
}
