package jmm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: Thread.start()/join() as happens-before edges.
class T4RunOnBackgroundThreadTest {
    @Test fun `runs the work on a different thread and returns its result`() {
        val callingThread = Thread.currentThread()
        var sawDifferentThread = false

        val result = runOnBackgroundThread {
            sawDifferentThread = Thread.currentThread() !== callingThread
            2 + 2
        }

        assertEquals(4, result)
        assertTrue(sawDifferentThread)
    }

    @Test fun `propagates a non-numeric result too`() {
        assertEquals("done", runOnBackgroundThread { "done" })
    }

    @Test fun `sees writes the caller made before starting the work`() {
        val input = 41
        val result = runOnBackgroundThread { input + 1 }
        assertEquals(42, result)
    }
}
