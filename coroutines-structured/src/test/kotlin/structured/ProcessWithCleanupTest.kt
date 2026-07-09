package structured

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: guaranteed cleanup that survives cancellation.
class ProcessWithCleanupTest {
    @Test fun `runs work then cleanup when never cancelled`() = runTest {
        val log = mutableListOf<String>()
        processWithCleanup(work = { log.add("work") }, onCleanup = { log.add("cleanup") })
        assertEquals(listOf("work", "cleanup"), log)
    }

    @Test fun `still runs a suspending cleanup after cancellation`() = runTest {
        var cleanupRan = false
        val job = launch {
            processWithCleanup(
                work = { delay(1000) },
                onCleanup = {
                    delay(50)
                    cleanupRan = true
                }
            )
        }
        advanceTimeBy(10)
        job.cancel()
        job.join()
        assertTrue(cleanupRan)
    }

    @Test fun `propagates cancellation after cleanup completes`() = runTest {
        val job = launch {
            processWithCleanup(work = { delay(1000) }, onCleanup = {})
        }
        advanceTimeBy(10)
        job.cancel()
        job.join()
        assertTrue(job.isCancelled)
    }
}
