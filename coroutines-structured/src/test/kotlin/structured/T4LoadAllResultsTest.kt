package structured

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: parallel decomposition that collects a Result per branch.
class T4LoadAllResultsTest {
    @Test fun `collects a successful result for every task`() = runTest {
        val results = loadAllResults(listOf({ 1 }, { 2 }, { 3 }))
        assertEquals(listOf(1, 2, 3), results.map { it.getOrThrow() })
    }

    @Test fun `one task failing does not stop or lose the others`() = runTest {
        val results = loadAllResults(
            listOf(
                { throw IllegalStateException("boom") },
                { delay(100); 42 }
            )
        )
        assertTrue(results[0].isFailure)
        assertEquals(42, results[1].getOrThrow())
        assertEquals(100L, testScheduler.currentTime)
    }
}
