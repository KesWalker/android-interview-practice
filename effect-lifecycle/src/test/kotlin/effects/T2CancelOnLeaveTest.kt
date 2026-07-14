package effects

import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: cancel the running job when the composable leaves.
class T2CancelOnLeaveTest {
    @Test fun `leave cancels the running job`() = runTest {
        val runner = LaunchedEffectRunner(this)
        var cancelled = false

        runner.launch(listOf(1)) {
            try {
                awaitCancellation()
            } finally {
                cancelled = true
            }
        }
        testScheduler.runCurrent()

        runner.leave()
        testScheduler.runCurrent()

        assertTrue(cancelled)
    }

    @Test fun `leave without a prior launch is a no-op`() = runTest {
        val runner = LaunchedEffectRunner(this)

        assertDoesNotThrow { runner.leave() }
    }

    @Test fun `after leave, the same keys launch again as if it were the first time`() = runTest {
        val runner = LaunchedEffectRunner(this)
        var launchCount = 0

        runner.launch(listOf(1)) { launchCount++; awaitCancellation() }
        testScheduler.runCurrent()
        runner.leave()
        runner.launch(listOf(1)) { launchCount++ }
        testScheduler.runCurrent()

        assertEquals(2, launchCount)
    }
}
