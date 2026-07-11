package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: async/await for parallel decomposition.
class T2SumConcurrentlyTest {
    @Test fun `adds both results`() = runTest {
        val result = sumConcurrently({ 2 }, { 3 })
        assertEquals(5, result)
    }

    @Test fun `works when both results are zero`() = runTest {
        assertEquals(0, sumConcurrently({ 0 }, { 0 }))
    }

    @Test fun `runs both branches at the same time, not one after another`() = runTest {
        suspend fun slow(value: Int): Int {
            delay(100)
            return value
        }

        val result = sumConcurrently({ slow(4) }, { slow(6) })

        assertEquals(10, result)
        assertEquals(100L, testScheduler.currentTime)
    }
}
