package imagecache

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 2: basic storage, no eviction pressure yet (see T3 for that).
class T2BasicPutGetTest {
    @Test fun `stores and retrieves bytes under a key`() {
        val cache = LruMemoryCache(maxBytes = 1000)
        cache.put("a", byteArrayOf(1, 2, 3))
        assertArrayEquals(byteArrayOf(1, 2, 3), cache.get("a"))
    }

    @Test fun `missing key returns null`() {
        val cache = LruMemoryCache(maxBytes = 1000)
        assertNull(cache.get("missing"))
    }

    @Test fun `overwriting a key replaces its bytes`() {
        val cache = LruMemoryCache(maxBytes = 1000)
        cache.put("a", byteArrayOf(1))
        cache.put("a", byteArrayOf(9, 9))
        assertArrayEquals(byteArrayOf(9, 9), cache.get("a"))
    }
}
