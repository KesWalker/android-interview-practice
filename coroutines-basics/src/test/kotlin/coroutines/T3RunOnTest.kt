package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

// Task 3: withContext to move work onto a given dispatcher.
class T3RunOnTest {
    @Test fun `runs the block on the given dispatcher's thread`() = runTest {
        val executor = Executors.newSingleThreadExecutor { runnable -> Thread(runnable, "worker-thread") }
        val dispatcher = executor.asCoroutineDispatcher()
        try {
            val threadName = runOn(dispatcher) { Thread.currentThread().name }
            assertEquals("worker-thread", threadName)
        } finally {
            dispatcher.close()
        }
    }

    @Test fun `returns the block's result`() = runTest {
        assertEquals(4, runOn(Dispatchers.Default) { 2 + 2 })
    }

    @Test fun `returns non-numeric results too`() = runTest {
        assertEquals("done", runOn(Dispatchers.Default) { "done" })
    }
}
