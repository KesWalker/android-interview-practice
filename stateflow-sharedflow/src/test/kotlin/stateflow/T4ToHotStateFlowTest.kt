package stateflow

import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

// Task 4: stateIn to share one upstream flow as hot, collector-driven UI state.
class T4ToHotStateFlowTest {
    @Test fun `does not start the upstream before anyone collects`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        var started = false
        val source: Flow<Int> = flow {
            started = true
            emit(1)
        }

        toHotStateFlow(scope, source, initial = 0)
        advanceUntilIdle()

        assertFalse(started)
        scope.cancel()
    }

    @Test fun `starts once collected and holds the latest emitted value`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        val source = flowOf(1, 2, 3)
        val state = toHotStateFlow(scope, source, initial = 0)

        val job = launch { state.collect {} }
        advanceUntilIdle()

        assertEquals(3, state.value)
        job.cancel()
        scope.cancel()
    }

    @Test fun `shares a single upstream across two concurrent collectors`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        var producerRuns = 0
        val source: Flow<Int> = flow {
            producerRuns++
            emit(42)
        }
        val state = toHotStateFlow(scope, source, initial = 0)

        val jobA = launch { state.collect {} }
        val jobB = launch { state.collect {} }
        advanceUntilIdle()

        assertEquals(42, state.value)
        assertEquals(1, producerRuns)
        jobA.cancel()
        jobB.cancel()
        scope.cancel()
    }
}
