package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

// Task 2: atomic compound update instead of racy get-then-put.
class IncrementCountTest {
    @Test fun `first increment starts the counter at 1`() {
        val counts = ConcurrentHashMap<String, Int>()
        assertEquals(1, incrementCount(counts, "views"))
    }

    @Test fun `repeated increments accumulate`() {
        val counts = ConcurrentHashMap<String, Int>()
        repeat(5) { incrementCount(counts, "views") }
        assertEquals(5, counts["views"])
    }

    @Test fun `many concurrent increments never lose an update`() {
        val counts = ConcurrentHashMap<String, Int>()
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 2000).map { Callable { incrementCount(counts, "views") } }
        pool.invokeAll(tasks)
        pool.shutdown()
        assertEquals(2000, counts["views"])
    }
}
