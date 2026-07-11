package coroutines

import kotlinx.coroutines.delay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: runBlocking bridges blocking code into coroutines.
// Deliberately plain JUnit test methods (no runTest, not suspend) -- the
// point of blockingCall is that it works from ordinary blocking call sites.
class BlockingCallTest {
    @Test fun `returns the suspend block's result`() {
        val result = blockingCall { 42 }
        assertEquals(42, result)
    }

    @Test fun `blocks the calling thread until a suspending block actually finishes`() {
        var completed = false
        val result = blockingCall {
            delay(20)
            completed = true
            "done"
        }
        assertEquals("done", result)
        assertEquals(true, completed)
    }

    // Compare thread identity, not name: with assertions on (as Gradle runs
    // tests), coroutine debug mode appends "@coroutine#N" to the thread name.
    @Test fun `runs the block on the calling thread rather than a background one`() {
        val callingThread = Thread.currentThread()
        val result = blockingCall { Thread.currentThread() }
        assertEquals(callingThread, result)
    }
}
