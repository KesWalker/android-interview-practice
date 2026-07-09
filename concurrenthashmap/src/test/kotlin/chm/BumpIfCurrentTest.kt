package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

// Task 4: compare-and-swap on a single entry via replace(key, expected, new).
class BumpIfCurrentTest {
    @Test fun `updates when the current value matches expected`() {
        val scores = ConcurrentHashMap<String, Int>()
        scores["p1"] = 10
        assertTrue(bumpIfCurrent(scores, "p1", 10, 20))
        assertEquals(20, scores["p1"])
    }

    @Test fun `leaves the value untouched when expected is stale`() {
        val scores = ConcurrentHashMap<String, Int>()
        scores["p1"] = 10
        assertEquals(false, bumpIfCurrent(scores, "p1", 999, 20))
        assertEquals(10, scores["p1"])
    }

    @Test fun `exactly one of many racing updates from the same starting value wins`() {
        val scores = ConcurrentHashMap<String, Int>()
        scores["p1"] = 0
        val winners = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (1..200).map { i ->
            Callable { if (bumpIfCurrent(scores, "p1", 0, i)) winners.incrementAndGet() }
        }
        pool.invokeAll(tasks)
        pool.shutdown()
        assertEquals(1, winners.get())
    }
}
