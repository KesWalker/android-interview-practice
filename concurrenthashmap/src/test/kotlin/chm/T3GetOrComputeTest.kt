package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

// Task 3: lazily populate a cache without computing twice for the same key.
class T3GetOrComputeTest {
    @Test fun `computes and stores the value on first access`() {
        val cache = ConcurrentHashMap<String, Int>()
        val result = cache.getOrCompute("a") { 42 }
        assertEquals(42, result)
        assertEquals(42, cache["a"])
    }

    @Test fun `does not recompute once a value is present`() {
        val cache = ConcurrentHashMap<String, Int>()
        val calls = AtomicInteger(0)
        cache.getOrCompute("a") { calls.incrementAndGet(); 1 }
        cache.getOrCompute("a") { calls.incrementAndGet(); 2 }
        assertEquals(1, calls.get())
        assertEquals(1, cache["a"])
    }

    @Test fun `concurrent callers for the same key trigger the compute block at most once`() {
        val cache = ConcurrentHashMap<String, Int>()
        val calls = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 200).map {
            Callable { cache.getOrCompute("shared") { calls.incrementAndGet(); 7 } }
        }
        val results = pool.invokeAll(tasks)
        pool.shutdown()
        assertEquals(1, calls.get())
        assertTrue(results.all { it.get() == 7 })
    }
}
