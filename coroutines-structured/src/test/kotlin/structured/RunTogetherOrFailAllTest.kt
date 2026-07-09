package structured

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

// Task 1: coroutineScope's all-or-nothing failure propagation.
class RunTogetherOrFailAllTest {
    @Test fun `runs every task when none fail`() = runTest {
        val ran = mutableListOf<Int>()
        runTogetherOrFailAll(listOf({ ran.add(1) }, { ran.add(2) }, { ran.add(3) }))
        assertEquals(listOf(1, 2, 3), ran.sorted())
    }

    @Test fun `propagates a task's failure out of the call`() = runTest {
        var thrown: Throwable? = null
        try {
            runTogetherOrFailAll(listOf({ throw IllegalStateException("boom") }))
        } catch (e: IllegalStateException) {
            thrown = e
        }
        assertEquals("boom", thrown?.message)
    }

    @Test fun `a failing task cancels its still-running siblings`() = runTest {
        var slowTaskCompleted = false
        try {
            runTogetherOrFailAll(
                listOf(
                    { delay(10); throw IllegalStateException("boom") },
                    { delay(1000); slowTaskCompleted = true }
                )
            )
        } catch (e: IllegalStateException) {
            // expected - the call fails as a whole
        }
        assertFalse(slowTaskCompleted)
    }
}
