package mvvm

import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: stateIn(WhileSubscribed(5000)) -- survives config change, stops on backgrounding.
class NewsUiStateTest {
    @Test fun `does not start the upstream before anyone collects`() = runTest {
        var startCount = 0
        val source: Flow<List<String>> = flow {
            startCount++
            emit(listOf("a"))
            awaitCancellation()
        }

        newsUiState(source, backgroundScope)
        runCurrent()

        assertEquals(0, startCount)
    }

    @Test fun `keeps the same upstream run across a gap under 5 seconds`() = runTest {
        var startCount = 0
        val source: Flow<List<String>> = flow {
            startCount++
            emit(listOf("a"))
            awaitCancellation()
        }
        val state = newsUiState(source, backgroundScope)

        val first = backgroundScope.launch { state.collect {} }
        runCurrent()
        assertEquals(1, startCount)
        first.cancel()
        runCurrent()

        advanceTimeBy(3_000)
        runCurrent()

        val second = backgroundScope.launch { state.collect {} }
        runCurrent()

        assertEquals(1, startCount)
        second.cancel()
    }

    @Test fun `stops the upstream once nothing has collected for 5 seconds`() = runTest {
        var startCount = 0
        val source: Flow<List<String>> = flow {
            startCount++
            emit(listOf("a"))
            awaitCancellation()
        }
        val state = newsUiState(source, backgroundScope)

        val first = backgroundScope.launch { state.collect {} }
        runCurrent()
        assertEquals(1, startCount)
        first.cancel()
        runCurrent()

        advanceTimeBy(5_001)
        runCurrent()

        val second = backgroundScope.launch { state.collect {} }
        runCurrent()

        assertEquals(2, startCount)
        second.cancel()
    }
}
