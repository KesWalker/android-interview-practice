package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: a remember-alike cache that only recomputes when its keys change.
class T3RememberTest {
    @Test fun `first call always computes`() {
        val cache = RememberCache()
        var computeCount = 0

        val result = cache.getOrCompute(listOf(1)) {
            computeCount++
            "A"
        }

        assertEquals("A", result)
        assertEquals(1, computeCount)
    }

    @Test fun `same keys reuse the cached value without recomputing`() {
        val cache = RememberCache()
        var computeCount = 0
        cache.getOrCompute(listOf(1)) { computeCount++; "A" }

        val result = cache.getOrCompute(listOf(1)) { computeCount++; "B" }

        assertEquals("A", result)
        assertEquals(1, computeCount)
    }

    @Test fun `different keys trigger a recompute`() {
        val cache = RememberCache()
        var computeCount = 0
        cache.getOrCompute(listOf(1)) { computeCount++; "A" }

        val result = cache.getOrCompute(listOf(2)) { computeCount++; "C" }

        assertEquals("C", result)
        assertEquals(2, computeCount)
    }
}
