package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

// Task 5: ConcurrentHashMap.newKeySet() as the concurrent Set.
class T5InFlightRequestsTest {
    @Test fun `sequential start and finish behaves like a normal set`() {
        val requests = InFlightRequests()

        assertTrue(requests.start("r1"))
        assertFalse(requests.start("r1"))
        assertEquals(1, requests.count())

        requests.finish("r1")
        assertEquals(0, requests.count())
        assertTrue(requests.start("r1"))
    }

    @Test fun `only one of 200 threads racing to start the same id wins`() {
        val requests = InFlightRequests()
        val winners = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 200).map {
            Callable { if (requests.start("shared")) winners.incrementAndGet() }
        }
        pool.invokeAll(tasks)
        pool.shutdown()

        assertEquals(1, winners.get())
        assertEquals(1, requests.count())
    }

    @Test fun `50 threads starting and finishing 100 distinct ids each end at zero in-flight`() {
        val requests = InFlightRequests()
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 50).map { threadIndex ->
            Callable {
                repeat(100) { i ->
                    val id = "t$threadIndex-$i"
                    requests.start(id)
                    requests.finish(id)
                }
            }
        }
        pool.invokeAll(tasks)
        pool.shutdown()

        assertEquals(0, requests.count())
    }
}
