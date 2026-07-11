package coroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: cooperative cancellation.
class T4CountTicksTest {
    @Test fun `counts all the way to the limit when never cancelled`() = runTest {
        var finalCount = 0
        val job = launch { finalCount = countTicks(5) {} }
        job.join()
        assertEquals(5, finalCount)
    }

    @Test fun `stops early once its own coroutine is cancelled`() = runTest {
        var finalCount = 0
        val job = launch {
            finalCount = countTicks(1000) { tick -> if (tick == 5) cancel() }
        }
        job.join()
        assertEquals(5, finalCount)
    }

    @Test fun `reports every tick along the way`() = runTest {
        val ticks = mutableListOf<Int>()
        val job = launch { countTicks(3) { ticks.add(it) } }
        job.join()
        assertEquals(listOf(1, 2, 3), ticks)
    }
}
