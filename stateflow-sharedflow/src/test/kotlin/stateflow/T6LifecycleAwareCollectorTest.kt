package stateflow

import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: repeatOnLifecycle(STARTED) - cancel, not pause, below STARTED.
class T6LifecycleAwareCollectorTest {
    private fun tickingSource(runCounts: MutableList<Int>): Flow<Int> = flow {
        val run = runCounts.size + 1
        runCounts.add(run)
        while (true) {
            delay(10)
            emit(run)
        }
    }

    @Test fun `does not collect anything before the first STARTED`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        val runCounts = mutableListOf<Int>()
        val received = mutableListOf<Int>()
        val collector = LifecycleAwareCollector(scope, tickingSource(runCounts)) { received.add(it) }

        collector.onStateChanged(LifecycleState.CREATED)
        advanceUntilIdle()

        assertEquals(emptyList<Int>(), received)
        assertEquals(0, runCounts.size)
        scope.cancel()
    }

    @Test fun `stops collecting immediately once it drops below STARTED`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        val runCounts = mutableListOf<Int>()
        val received = mutableListOf<Int>()
        val collector = LifecycleAwareCollector(scope, tickingSource(runCounts)) { received.add(it) }

        collector.onStateChanged(LifecycleState.STARTED)
        advanceTimeBy(25)
        runCurrent()
        assertEquals(listOf(1, 1), received)

        collector.onStateChanged(LifecycleState.STOPPED)
        advanceTimeBy(100)
        runCurrent()

        assertEquals(listOf(1, 1), received)
        scope.cancel()
    }

    @Test fun `restarting after STOPPED collects with a brand-new job`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        val runCounts = mutableListOf<Int>()
        val received = mutableListOf<Int>()
        val collector = LifecycleAwareCollector(scope, tickingSource(runCounts)) { received.add(it) }

        collector.onStateChanged(LifecycleState.STARTED)
        advanceTimeBy(15)
        runCurrent()

        collector.onStateChanged(LifecycleState.STOPPED)
        advanceTimeBy(50)
        runCurrent()

        collector.onStateChanged(LifecycleState.STARTED)
        advanceTimeBy(15)
        runCurrent()

        assertEquals(2, runCounts.size)
        assertEquals(listOf(1, 2), received)
        scope.cancel()
    }
}
