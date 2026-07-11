package statelogic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: a private mutable source of truth exposed as read-only, updated through Loading/Content/Error.
class T2UiStateHolderTest {
    @Test fun `starts in Loading before any fetch runs`() = runTest {
        val holder = UiStateHolder<Int>()
        assertEquals(UiState.Loading, holder.state.value)
    }

    @Test fun `publishes Content once the fetch succeeds`() = runTest {
        val holder = UiStateHolder<Int>()
        holder.load { 7 }
        assertEquals(UiState.Content(7), holder.state.value)
    }

    @Test fun `publishes Error with the failure's message`() = runTest {
        val holder = UiStateHolder<Int>()
        holder.load { throw IllegalStateException("network down") }
        assertEquals(UiState.Error("network down"), holder.state.value)
    }

    @Test fun `stays in Loading while the fetch is still in flight`() = runTest {
        val holder = UiStateHolder<Int>()
        val job = launch { holder.load { delay(100); 7 } }

        advanceTimeBy(50)
        assertEquals(UiState.Loading, holder.state.value)

        advanceUntilIdle()
        job.join()
        assertEquals(UiState.Content(7), holder.state.value)
    }
}
