package effects

import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: relaunch a keyed effect only when its keys change.
class T1LaunchedEffectTest {
    @Test fun `does not relaunch when keys are unchanged`() = runTest {
        val runner = LaunchedEffectRunner(this)
        var launchCount = 0

        runner.launch(listOf("k")) { launchCount++ }
        testScheduler.runCurrent()
        runner.launch(listOf("k")) { launchCount++ }
        testScheduler.runCurrent()

        assertEquals(1, launchCount)
    }

    @Test fun `cancels the running job and relaunches when keys change`() = runTest {
        val runner = LaunchedEffectRunner(this)
        val log = mutableListOf<String>()
        var firstJobCancelled = false

        runner.launch(listOf(1)) {
            try {
                log += "start-1"
                awaitCancellation()
            } finally {
                firstJobCancelled = true
                log += "cancel-1"
            }
        }
        testScheduler.runCurrent()

        runner.launch(listOf(2)) {
            log += "start-2"
        }
        testScheduler.runCurrent()

        assertTrue(firstJobCancelled)
        assertEquals(listOf("start-1", "cancel-1", "start-2"), log)
    }
}
