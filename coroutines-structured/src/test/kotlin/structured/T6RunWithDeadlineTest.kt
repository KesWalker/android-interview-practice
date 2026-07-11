package structured

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: withTimeout / TimeoutCancellationException.
class T6RunWithDeadlineTest {
    @Test fun `returns work's result when it finishes inside the deadline`() = runTest {
        val result = runWithDeadline(100) { delay(10); "done" }
        assertEquals("done", result)
    }

    @Test fun `throws TimeoutCancellationException when work runs past the deadline`() = runTest {
        var thrown: Throwable? = null
        try {
            runWithDeadline(50) { delay(1000); "too slow" }
        } catch (e: TimeoutCancellationException) {
            thrown = e
        }
        assertTrue(thrown is TimeoutCancellationException)
        assertEquals(50L, testScheduler.currentTime)
    }
}
