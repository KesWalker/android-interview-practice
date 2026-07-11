package mvvm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: one immutable state, one derived status.
class T1ScreenStatusTest {
    @Test fun `an error message wins over loading and content`() {
        val state = FeedUiState(items = listOf("a"), isLoading = true, error = "Oops")
        assertEquals(ScreenStatus.Error("Oops"), screenStatus(state))
    }

    @Test fun `loading wins when there is no error`() {
        val state = FeedUiState(items = listOf("a"), isLoading = true)
        assertEquals(ScreenStatus.Loading, screenStatus(state))
    }

    @Test fun `no items and nothing else going on means empty`() {
        val state = FeedUiState()
        assertEquals(ScreenStatus.Empty, screenStatus(state))
    }

    @Test fun `items with no error or loading means content`() {
        val state = FeedUiState(items = listOf("a", "b"))
        assertEquals(ScreenStatus.Content(listOf("a", "b")), screenStatus(state))
    }
}
