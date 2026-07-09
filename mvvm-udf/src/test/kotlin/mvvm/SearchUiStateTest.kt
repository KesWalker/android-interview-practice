package mvvm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: one derived state, recomputed whenever any of its inputs change.
class SearchUiStateTest {
    private val allItems = listOf("Apple" to true, "Banana" to false, "Cherry" to true)

    @Test fun `starts from the current query and toggle values`() = runTest {
        val query = MutableStateFlow("ap")
        val onlyFavorites = MutableStateFlow(false)

        val first = searchUiState(query, onlyFavorites, allItems).first()

        assertEquals(SearchUiState("ap", false, listOf("Apple")), first)
    }

    @Test fun `restricts results to favorites when the toggle starts on`() = runTest {
        val query = MutableStateFlow("")
        val onlyFavorites = MutableStateFlow(true)

        val first = searchUiState(query, onlyFavorites, allItems).first()

        assertEquals(SearchUiState("", true, listOf("Apple", "Cherry")), first)
    }

    @Test fun `recomputes results as the query and toggle change`() = runTest {
        val query = MutableStateFlow("")
        val onlyFavorites = MutableStateFlow(false)
        var states: List<SearchUiState> = emptyList()

        val job = launch {
            states = searchUiState(query, onlyFavorites, allItems).take(3).toList()
        }
        advanceUntilIdle()

        query.value = "a"
        advanceUntilIdle()

        onlyFavorites.value = true
        advanceUntilIdle()

        job.join()

        assertEquals(3, states.size)
        assertEquals(SearchUiState("", false, listOf("Apple", "Banana", "Cherry")), states[0])
        assertEquals(SearchUiState("a", false, listOf("Apple", "Banana")), states[1])
        assertEquals(SearchUiState("a", true, listOf("Apple")), states[2])
    }
}
