package structured

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: supervisorScope + per-failure reporting instead of crashing.
class RunIsolatedReportingTest {
    @Test fun `runs all tasks when none fail`() = runTest {
        val ran = mutableListOf<Int>()
        runIsolatedReporting(listOf({ ran.add(1) }, { ran.add(2) })) { }
        assertEquals(listOf(1, 2), ran.sorted())
    }

    @Test fun `reports a failing task's exception instead of throwing it`() = runTest {
        val errors = mutableListOf<Throwable>()
        runIsolatedReporting(listOf({ throw IllegalStateException("boom") })) { errors.add(it) }
        assertEquals(1, errors.size)
        assertEquals("boom", errors.single().message)
    }

    @Test fun `a failing task does not stop the others from running`() = runTest {
        val ran = mutableListOf<Int>()
        val errors = mutableListOf<Throwable>()
        runIsolatedReporting(
            listOf(
                { throw IllegalStateException("boom") },
                { delay(50); ran.add(2) }
            )
        ) { errors.add(it) }
        assertEquals(listOf(2), ran)
        assertEquals(1, errors.size)
    }
}
