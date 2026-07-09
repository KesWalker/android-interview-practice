package jmm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

// Task 3: thread-safe lazy initialization (double-checked locking).
class LazyOnceTest {
    @Test fun `returns the computed value on repeated calls`() {
        val once = LazyOnce { "hello" }
        assertEquals("hello", once.value())
        assertEquals("hello", once.value())
    }

    @Test fun `computes the value only once even when many threads race for it`() {
        val calls = AtomicInteger(0)
        val once = LazyOnce {
            calls.incrementAndGet()
            42
        }
        val threadCount = 50
        val ready = CountDownLatch(threadCount)
        val start = CountDownLatch(1)
        val results = java.util.Collections.synchronizedList(mutableListOf<Int>())

        val threads = List(threadCount) {
            Thread {
                ready.countDown()
                start.await()
                results.add(once.value())
            }
        }
        threads.forEach { it.start() }
        ready.await()
        start.countDown()
        threads.forEach { it.join() }

        assertEquals(1, calls.get())
        assertEquals(List(threadCount) { 42 }, results.sorted())
    }
}
