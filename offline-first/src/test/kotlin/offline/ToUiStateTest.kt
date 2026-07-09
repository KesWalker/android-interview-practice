package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ToUiStateTest {

    @Test
    fun `shows loading when there is no cached value and no error`() {
        val state = toUiState<String>(cached = null, errorMessage = null)

        assertEquals(UiState.Loading, state)
    }

    @Test
    fun `shows content when a value is cached and there is no error`() {
        val state = toUiState(cached = "hello", errorMessage = null)

        assertEquals(UiState.Content("hello"), state)
    }

    @Test
    fun `falls back to the cached value when a fetch error occurs`() {
        val state = toUiState(cached = "stale but usable", errorMessage = "network unreachable")

        assertEquals(UiState.Content("stale but usable"), state)
    }

    @Test
    fun `shows an error only when there is no cached value to fall back on`() {
        val state = toUiState<String>(cached = null, errorMessage = "network unreachable")

        assertEquals(UiState.Error("network unreachable"), state)
    }
}
