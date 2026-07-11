package jmm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

// Task 2: synchronized guarding a compound check-then-act.
class T2HighWaterMarkTest {
    @Test fun `starts at zero`() {
        assertEquals(0, HighWaterMark().current())
    }

    @Test fun `ignores a lower value recorded after a higher one`() {
        val tracker = HighWaterMark()
        tracker.record(10)
        tracker.record(3)
        assertEquals(10, tracker.current())
    }

    @Test fun `keeps the true maximum when many threads race to record`() {
        val tracker = HighWaterMark()
        val values = (1..200).shuffled()
        val ready = CountDownLatch(values.size)
        val start = CountDownLatch(1)

        val threads = values.map { value ->
            Thread {
                ready.countDown()
                start.await()
                tracker.record(value)
            }
        }
        threads.forEach { it.start() }
        ready.await()
        start.countDown()
        threads.forEach { it.join() }

        assertEquals(200, tracker.current())
    }
}
