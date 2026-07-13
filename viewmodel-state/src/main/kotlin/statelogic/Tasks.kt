package statelogic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * UI State modelling (loading/content/error) practice.
 *
 * Each type below is unfinished and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :viewmodel-state:test
 */

// A screen's state is always exactly one of these three things.
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Content<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

// TODO(t1): T1ToUiStateTest
// Turn a Result into the matching case above (Result itself has no way to represent Loading).
fun <T> toUiState(result: Result<T>): UiState<T> {
    TODO("t1: map a successful Result to Content and a failed one to Error (fall back to \"Unknown error\" when the failure has no message)")
}

// TODO(t2): T2UiStateHolderTest
// Holds the current screen state and lets outside code observe it without being able to overwrite it directly.
class UiStateHolder<T> {
    private val _state = MutableStateFlow<UiState<T>>(UiState.Loading)

    val state: StateFlow<UiState<T>> = TODO("t2: expose _state so outside code can read it but not assign to it")

    // Run fetch and publish the outcome, starting from Loading every time.
    suspend fun load(fetch: suspend () -> T) {
        TODO("t2: publish Loading, then Content on success or Error (with the failure's message) on failure")
    }
}

// TODO(t3): T3ClearableHolderTest
// A holder whose background work can all be shut down at once from the outside, the way a screen's
// state holder stops doing work once nobody is showing that screen any more.
class ClearableHolder(dispatcher: CoroutineDispatcher) {
    val scope: CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

    fun clear() {
        TODO("t3: stop everything currently running in scope, and make sure nothing newly launched on it runs afterwards")
    }
}

// TODO(t4): T4SingleFlightLoaderTest
// A loader that shares one in-progress fetch among callers instead of kicking off a duplicate for each one.
class SingleFlightLoader<T>(private val scope: CoroutineScope) {
    private var inFlight: Deferred<T>? = null

    suspend fun load(fetch: suspend () -> T): T {
        TODO("t4: if a fetch is already running, wait for and return its result instead of starting a new one")
    }
}

// TODO(t5): T5FakeSavedStateHandleTest
// A tiny key-value handle whose getStateFlow(key, default) returns a StateFlow
// seeded from any existing value under that key (or the given default when
// absent), and that keeps emitting the current value after later set(key, ...)
// calls change it.
class FakeSavedStateHandle {
    fun <T> getStateFlow(key: String, default: T): StateFlow<T> {
        TODO("t5: return one StateFlow per key, seeded from the stored value or the default when absent")
    }

    fun <T> set(key: String, value: T) {
        TODO("t5: store the value and push it to the StateFlow already handed out for this key")
    }
}
