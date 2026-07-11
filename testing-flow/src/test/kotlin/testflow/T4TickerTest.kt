package testflow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: backgroundScope for collecting a flow that never completes on its own.
class T4TickerTest {
    @Test fun `never ticks before its first interval has elapsed`() = runTest {
        val ticks = mutableListOf<Int>()
        backgroundScope.launch { ticker(10).collect { ticks.add(it) } }

        advanceTimeBy(5)
        runCurrent()

        assertEquals(emptyList<Int>(), ticks)
    }

    @Test fun `emits the first tick once its interval elapses`() = runTest {
        val ticks = mutableListOf<Int>()
        backgroundScope.launch { ticker(10).collect { ticks.add(it) } }

        advanceTimeBy(15)
        runCurrent()

        assertEquals(listOf(1), ticks)
    }

    @Test fun `keeps ticking every interval without anyone cancelling it`() = runTest {
        val ticks = mutableListOf<Int>()
        backgroundScope.launch { ticker(10).collect { ticks.add(it) } }

        advanceTimeBy(35)
        runCurrent()

        assertEquals(listOf(1, 2, 3), ticks)
    }
}
