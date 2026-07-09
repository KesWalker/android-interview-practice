package repository

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private class RecordingDispatcher : CoroutineDispatcher() {
    var wasUsed = false
        private set

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        wasUsed = true
        block.run()
    }
}

// Task 3: main-safety - moving blocking work onto an injected dispatcher.
class LoadOnDispatcherTest {
    @Test fun `returns the result of the given work`() = runTest {
        val recorder = RecordingDispatcher()

        val result = loadOnDispatcher(recorder) { 42 }

        assertEquals(42, result)
    }

    @Test fun `actually runs the work on the given dispatcher`() = runTest {
        val recorder = RecordingDispatcher()

        loadOnDispatcher(recorder) { "ignored" }

        assertTrue(recorder.wasUsed)
    }

    @Test fun `works with a different dispatcher instance and result type`() = runTest {
        val recorder = RecordingDispatcher()

        val result = loadOnDispatcher(recorder) { listOf(1, 2, 3) }

        assertEquals(listOf(1, 2, 3), result)
        assertTrue(recorder.wasUsed)
    }
}
