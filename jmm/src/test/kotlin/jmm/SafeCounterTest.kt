package jmm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

// Task 1: atomic read-modify-write for a shared counter.
class SafeCounterTest {
    @Test fun `starts at zero`() {
        assertEquals(0, SafeCounter().get())
    }

    @Test fun `many threads incrementing at once never lose an update`() {
        val counter = SafeCounter()
        val threadCount = 20
        val incrementsPerThread = 5_000
        val ready = CountDownLatch(threadCount)
        val start = CountDownLatch(1)

        val threads = List(threadCount) {
            Thread {
                ready.countDown()
                start.await()
                repeat(incrementsPerThread) { counter.increment() }
            }
        }
        threads.forEach { it.start() }
        ready.await()
        start.countDown()
        threads.forEach { it.join() }

        assertEquals(threadCount * incrementsPerThread, counter.get())
    }
}
