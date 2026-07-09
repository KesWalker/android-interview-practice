package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Collections
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

// Task 3: fixing a check-then-act race.
class ComputeOnceCacheTest {
    @Test fun `computes the value for a single caller`() {
        val cache = ComputeOnceCache()
        assertEquals(42, cache.getOrCompute("a") { 42 })
    }

    @Test fun `a second call for the same key reuses the cached value`() {
        val cache = ComputeOnceCache()
        val computeCount = AtomicInteger(0)
        val compute = { _: String -> computeCount.incrementAndGet(); 7 }

        cache.getOrCompute("a", compute)
        cache.getOrCompute("a", compute)

        assertEquals(1, computeCount.get())
    }

    @Test fun `many concurrent callers only trigger one computation`() {
        val cache = ComputeOnceCache()
        val computeCount = AtomicInteger(0)
        val threads = 30
        val start = CountDownLatch(1)
        val done = CountDownLatch(threads)
        val results = Collections.synchronizedList(mutableListOf<Int>())

        repeat(threads) {
            Thread {
                start.await()
                val value = cache.getOrCompute("shared") {
                    computeCount.incrementAndGet()
                    Thread.sleep(20)
                    99
                }
                results.add(value)
                done.countDown()
            }.start()
        }

        start.countDown()
        done.await()

        assertEquals(1, computeCount.get())
        assertEquals(List(threads) { 99 }, results.toList())
    }
}
