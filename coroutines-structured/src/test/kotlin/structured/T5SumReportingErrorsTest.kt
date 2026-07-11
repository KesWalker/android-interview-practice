package structured

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: ensureActive() + rethrowing CancellationException instead of reporting it.
class T5SumReportingErrorsTest {
    @Test fun `sums every entry when none throw`() = runTest {
        val total = sumReportingErrors(listOf({ 1 }, { 2 }, { 3 })) { }
        assertEquals(6, total)
    }

    @Test fun `reports a thrown exception via onError and keeps summing the rest`() = runTest {
        val errors = mutableListOf<Throwable>()
        val total = sumReportingErrors(
            listOf({ 1 }, { throw IllegalStateException("boom") }, { 3 })
        ) { errors.add(it) }
        assertEquals(4, total)
        assertEquals(1, errors.size)
        assertEquals("boom", errors.single().message)
    }

    @Test fun `a cancellation landing mid-loop is rethrown, not reported or swallowed`() = runTest {
        val processed = mutableListOf<Int>()
        val entries = listOf<suspend () -> Int>(
            { delay(10); processed.add(1); 1 },
            { delay(10); processed.add(2); 2 },
            { delay(10); processed.add(3); 3 }
        )
        val errors = mutableListOf<Throwable>()
        var completedNormally = false

        val job = launch {
            sumReportingErrors(entries) { errors.add(it) }
            completedNormally = true
        }

        advanceTimeBy(15) // first entry finishes; the loop is suspended mid-second-entry
        job.cancel()
        job.join()

        assertEquals(listOf(1), processed)
        assertTrue(job.isCancelled)
        assertTrue(errors.isEmpty())
        assertFalse(completedNormally)
    }
}
