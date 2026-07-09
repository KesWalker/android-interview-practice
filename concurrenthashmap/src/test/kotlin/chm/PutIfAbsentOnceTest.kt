package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

// Task 1: install-if-absent without a check-then-act race.
class PutIfAbsentOnceTest {
    @Test fun `first call inserts and returns true`() {
        val map = ConcurrentHashMap<String, Int>()
        assertTrue(putIfAbsentOnce(map, "a", 1))
        assertEquals(1, map["a"])
    }

    @Test fun `second call for the same key returns false and keeps the original value`() {
        val map = ConcurrentHashMap<String, Int>()
        putIfAbsentOnce(map, "a", 1)
        assertEquals(false, putIfAbsentOnce(map, "a", 99))
        assertEquals(1, map["a"])
    }

    @Test fun `only one of many concurrent callers wins the same key`() {
        val map = ConcurrentHashMap<String, Int>()
        val winners = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 200).map { i ->
            Callable { if (putIfAbsentOnce(map, "shared", i)) winners.incrementAndGet() }
        }
        pool.invokeAll(tasks)
        pool.shutdown()
        assertEquals(1, winners.get())
    }
}
