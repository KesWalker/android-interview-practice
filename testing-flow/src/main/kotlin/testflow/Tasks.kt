package testflow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Testing Coroutines & Flow practice.
 *
 * Each piece below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN, using the tools kotlinx-coroutines-test gives you:
 * virtual time, test dispatchers, and deterministic flow collection. Run a
 * single test class from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :testing-flow:test
 */

// TODO(t1): T1GreetingLoaderTest
class GreetingLoader(
    dispatcher: CoroutineDispatcher,
    private val fetch: suspend (String) -> String,
) {
    private val scope = CoroutineScope(dispatcher + Job())
    private val _state = MutableStateFlow<String?>(null)
    val state: StateFlow<String?> = _state.asStateFlow()

    // Fetch the greeting for `name` and publish the result into state once it's ready.
    fun load(name: String) {
        TODO("t1: fetch the greeting for name and publish the result into state")
    }
}

interface SearchRepository {
    suspend fun search(query: String): List<String>
}

// TODO(t2): T2SearchPresenterTest
class SearchPresenter(private val repository: SearchRepository) {
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _results = MutableStateFlow<List<String>>(emptyList())
    val results: StateFlow<List<String>> = _results.asStateFlow()

    // Report loading around the repository call, then publish its results.
    fun search(query: String) {
        TODO("t2: report loading around the repository call, then publish its results")
    }
}

// TODO(t3): T3AboveThresholdTest
// Emit only the values from `numbers` that are strictly greater than `threshold`.
fun <T : Comparable<T>> aboveThreshold(numbers: Flow<T>, threshold: T): Flow<T> {
    TODO("t3: emit only the values from numbers that are greater than threshold")
}

// TODO(t4): T4TickerTest
// Emit 1, 2, 3, ... every intervalMillis, forever, without ever completing.
fun ticker(intervalMillis: Long): Flow<Int> {
    TODO("t4: emit an incrementing count every intervalMillis, forever")
}
