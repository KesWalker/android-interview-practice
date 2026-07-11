package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: launch returns a Job; fire-and-forget, no return value.
class FireAndForgetTest {
    @Test fun `join lets the caller observe the side effect after completion`() = runTest {
        var sideEffect = false
        val job = fireAndForget(this) { sideEffect = true }
        job.join()
        assertTrue(sideEffect)
    }

    @Test fun `cancelling the job before it finishes prevents the side effect`() = runTest {
        var sideEffect = false
        val job = fireAndForget(this) {
            delay(50)
            sideEffect = true
        }
        job.cancel()
        job.join()
        assertFalse(sideEffect)
    }

    @Test fun `returns a Job the caller controls, completed only after join`() = runTest {
        val job = fireAndForget(this) {}
        assertEquals(false, job.isCompleted)
        job.join()
        assertTrue(job.isCompleted)
    }
}
