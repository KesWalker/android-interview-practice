package testflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 2: swapping Dispatchers.Main for a test dispatcher so viewModelScope-style code is testable.
class SearchPresenterTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private class FakeSearchRepository(private val response: List<String>) : SearchRepository {
        override suspend fun search(query: String): List<String> {
            delay(30)
            return response
        }
    }

    @Test fun `starts idle with no results`() = runTest(testDispatcher) {
        val presenter = SearchPresenter(FakeSearchRepository(emptyList()))

        assertEquals(false, presenter.isLoading.value)
        assertEquals(emptyList<String>(), presenter.results.value)
    }

    @Test fun `toggles loading on then off around the search`() = runTest(testDispatcher) {
        val presenter = SearchPresenter(FakeSearchRepository(listOf("kotlin")))
        val loadingStates = mutableListOf<Boolean>()
        val job = launch { presenter.isLoading.collect { loadingStates.add(it) } }
        runCurrent()

        presenter.search("k")
        advanceUntilIdle()

        job.cancel()
        assertEquals(listOf(false, true, false), loadingStates)
    }

    @Test fun `publishes the repository's results once the search resolves`() = runTest(testDispatcher) {
        val presenter = SearchPresenter(FakeSearchRepository(listOf("kotlin", "coroutines")))

        presenter.search("k")
        advanceUntilIdle()

        assertEquals(listOf("kotlin", "coroutines"), presenter.results.value)
    }
}
