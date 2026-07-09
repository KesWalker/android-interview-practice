package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

// Task 2: fixing a lost-update race.
class HitCounterTest {
    @Test fun `single-threaded recording counts correctly`() {
        val counter = HitCounter()
        repeat(5) { counter.record("home") }
        assertEquals(5, counter.countFor("home"))
    }

    @Test fun `many threads recording the same key lose no updates`() {
        val counter = HitCounter()
        val threads = 20
        val hitsPerThread = 500
        val start = CountDownLatch(1)
        val done = CountDownLatch(threads)

        repeat(threads) {
            Thread {
                start.await()
                repeat(hitsPerThread) { counter.record("home") }
                done.countDown()
            }.start()
        }

        start.countDown()
        done.await()

        assertEquals(threads * hitsPerThread, counter.countFor("home"))
    }
}
