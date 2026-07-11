package stateflow

import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: shareIn for one-off events (vs stateIn for state).
class T7ToSharedEventsTest {
    @Test fun `a subscriber that joins late does not receive values emitted before it subscribed`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        val source: Flow<Int> = flow {
            emit(1)
            delay(10)
            emit(2)
        }
        val shared = toSharedEvents(scope, source)

        val receivedA = mutableListOf<Int>()
        val jobA = launch { shared.collect { receivedA.add(it) } }
        advanceTimeBy(1)
        runCurrent()
        assertEquals(listOf(1), receivedA)

        val receivedB = mutableListOf<Int>()
        val jobB = launch { shared.collect { receivedB.add(it) } }
        advanceTimeBy(1)
        runCurrent()
        assertEquals(emptyList<Int>(), receivedB)

        advanceUntilIdle()
        assertEquals(listOf(1, 2), receivedA)
        assertEquals(listOf(2), receivedB)

        jobA.cancel()
        jobB.cancel()
        scope.cancel()
    }

    @Test fun `two concurrent collectors both see values emitted while they're subscribed, from a single upstream run`() = runTest {
        val scope = CoroutineScope(coroutineContext + Job())
        var producerRuns = 0
        val source: Flow<Int> = flow {
            producerRuns++
            emit(42)
        }
        val shared = toSharedEvents(scope, source)

        val receivedA = mutableListOf<Int>()
        val receivedB = mutableListOf<Int>()
        val jobA = launch { shared.collect { receivedA.add(it) } }
        val jobB = launch { shared.collect { receivedB.add(it) } }
        advanceUntilIdle()

        assertEquals(listOf(42), receivedA)
        assertEquals(listOf(42), receivedB)
        assertEquals(1, producerRuns)
        jobA.cancel()
        jobB.cancel()
        scope.cancel()
    }
}
