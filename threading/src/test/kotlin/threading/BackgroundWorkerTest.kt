package threading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import org.junit.jupiter.api.Test

// Task 2: a dedicated worker thread that any caller can safely post to.
class BackgroundWorkerTest {
    @Test fun `runs every posted action exactly once`() {
        val worker = BackgroundWorker()
        worker.start()
        val total = 200
        val counter = AtomicInteger(0)
        val latch = CountDownLatch(total)

        repeat(total) {
            worker.post {
                counter.incrementAndGet()
                latch.countDown()
            }
        }

        assertTrue(latch.await(5, TimeUnit.SECONDS), "actions did not all complete in time")
        assertEquals(total, counter.get())
        worker.shutdown()
    }

    @Test fun `accepts posts from many caller threads at once`() {
        val worker = BackgroundWorker()
        worker.start()
        val callerCount = 4
        val postsPerCaller = 50
        val total = callerCount * postsPerCaller
        val counter = AtomicInteger(0)
        val latch = CountDownLatch(total)

        val callers = List(callerCount) {
            Thread {
                repeat(postsPerCaller) {
                    worker.post {
                        counter.incrementAndGet()
                        latch.countDown()
                    }
                }
            }
        }
        callers.forEach { it.start() }
        callers.forEach { it.join() }

        assertTrue(latch.await(5, TimeUnit.SECONDS), "actions did not all complete in time")
        assertEquals(total, counter.get())
        worker.shutdown()
    }
}
