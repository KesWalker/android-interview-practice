package statelogic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: modelling loading/content/error as a closed set of cases.
class T1ToUiStateTest {
    @Test fun `wraps a successful result as Content`() {
        assertEquals(UiState.Content(42), toUiState(Result.success(42)))
    }

    @Test fun `wraps a failed result as Error with its message`() {
        val state = toUiState(Result.failure<Int>(IllegalStateException("boom")))
        assertEquals(UiState.Error("boom"), state)
    }

    @Test fun `falls back to a default message when the exception has none`() {
        val state = toUiState(Result.failure<Int>(RuntimeException()))
        assertEquals(UiState.Error("Unknown error"), state)
    }
}
