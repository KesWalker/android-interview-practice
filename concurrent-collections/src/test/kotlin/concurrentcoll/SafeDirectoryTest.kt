package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

// Task 4: a read-write lock.
class SafeDirectoryTest {
    @Test fun `put then get returns the stored value`() {
        val directory = SafeDirectory()
        directory.put("room-1", "Reception")
        assertEquals("Reception", directory.get("room-1"))
    }

    @Test fun `many concurrent writers land every entry with none lost`() {
        val directory = SafeDirectory()
        val writers = 20
        val entriesPerWriter = 200
        val start = CountDownLatch(1)
        val done = CountDownLatch(writers)

        repeat(writers) { writerIndex ->
            Thread {
                start.await()
                repeat(entriesPerWriter) { i -> directory.put("w$writerIndex-$i", "v$i") }
                done.countDown()
            }.start()
        }

        start.countDown()
        done.await()

        assertEquals(writers * entriesPerWriter, directory.size())
    }

    @Test fun `readers never fail while writes are in flight`() {
        val directory = SafeDirectory()
        repeat(100) { directory.put("k$it", "v$it") }

        val readers = 10
        val start = CountDownLatch(1)
        val done = CountDownLatch(readers + 1)
        val failures = AtomicInteger(0)

        repeat(readers) {
            Thread {
                start.await()
                repeat(500) { i ->
                    try {
                        directory.get("k${i % 100}")
                    } catch (e: Exception) {
                        failures.incrementAndGet()
                    }
                }
                done.countDown()
            }.start()
        }
        Thread {
            start.await()
            repeat(500) { i -> directory.put("k${i % 100}", "updated$i") }
            done.countDown()
        }.start()

        start.countDown()
        done.await()

        assertEquals(0, failures.get())
    }
}
