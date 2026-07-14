package imagecache

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

// Task 1: cache key must fold in size and transformations, not just the url.
class T1CacheKeyTest {
    @Test fun `same url, size and transformations produce the same key`() {
        assertEquals(
            cacheKey("http://x/img.png", 100, 100, listOf("grayscale")),
            cacheKey("http://x/img.png", 100, 100, listOf("grayscale"))
        )
    }

    @Test fun `different sizes for the same url produce different keys`() {
        val thumb = cacheKey("http://x/img.png", 100, 100)
        val full = cacheKey("http://x/img.png", 1000, 1000)
        assertNotEquals(thumb, full)
    }

    @Test fun `different transformations for the same url and size produce different keys`() {
        val plain = cacheKey("http://x/img.png", 200, 200)
        val grayscale = cacheKey("http://x/img.png", 200, 200, listOf("grayscale"))
        assertNotEquals(plain, grayscale)
    }
}
