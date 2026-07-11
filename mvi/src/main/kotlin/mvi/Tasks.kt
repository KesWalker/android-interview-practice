package mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * MVI: reducer & state machine practice.
 *
 * Each piece below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it so its test
 * goes GREEN. Run a single test class from the gutter in Android Studio, or
 * run them all with:
 *
 *     ./gradlew :mvi:test
 */

// Screen state and the actions that can change it.
data class CounterState(val count: Int = 0, val error: String? = null)

sealed interface CounterIntent {
    data object Increment : CounterIntent
    data object Decrement : CounterIntent
    data class Fail(val message: String) : CounterIntent
    data object ClearError : CounterIntent
}

// TODO(t1): T1ReduceCounterTest
// Given the current state and an intent, compute and return the next state:
// Increment/Decrement change count by one, Fail sets error, ClearError clears
// it, and neither of those two touches count.
fun reduceCounter(state: CounterState, intent: CounterIntent): CounterState {
    TODO("t1: return the next CounterState for (state, intent)")
}

// TODO(t2): T2CounterStoreTest
// Holds the current state and exposes it for reading; `dispatch` is the one
// entry point every intent goes through to produce and publish the next state.
class CounterStore(initial: CounterState = CounterState()) {
    private val _state = MutableStateFlow(initial)
    val state: StateFlow<CounterState> = _state.asStateFlow()

    fun dispatch(intent: CounterIntent) {
        TODO("t2: compute the next state for this intent and publish it as the new state")
    }
}

// A second screen: loading a user by id, and folding the outcome back in.
data class UserState(
    val isLoading: Boolean = false,
    val user: String? = null,
    val error: String? = null
)

sealed interface UserIntent {
    data object Load : UserIntent
    data class Loaded(val user: String) : UserIntent
    data class Failed(val message: String) : UserIntent
}

fun reduceUser(state: UserState, intent: UserIntent): UserState = when (intent) {
    is UserIntent.Load -> state.copy(isLoading = true, error = null)
    is UserIntent.Loaded -> state.copy(isLoading = false, user = intent.user, error = null)
    is UserIntent.Failed -> state.copy(isLoading = false, error = intent.message)
}

class UserStore(initial: UserState = UserState()) {
    private val _state = MutableStateFlow(initial)
    val state: StateFlow<UserState> = _state.asStateFlow()
    fun dispatch(intent: UserIntent) {
        _state.value = reduceUser(_state.value, intent)
    }
}

interface UserRepository {
    suspend fun fetchUser(id: String): String
}

// TODO(t3): T3LoadUserTest
// Announce that loading has started, await the repository call, then fold its
// outcome back in: the fetched name on success, or its error message on
// failure. The repository call itself must stay outside any reducer.
suspend fun loadUser(store: UserStore, repo: UserRepository, id: String) {
    TODO("t3: dispatch Load, await repo.fetchUser(id), then dispatch Loaded or Failed")
}

// A one-off effect, distinct from state, that should be delivered once.
sealed interface CartEffect {
    data class ShowToast(val message: String) : CartEffect
}

class CartEffects {
    private val channel = Channel<CartEffect>(Channel.BUFFERED)
    val effects: Flow<CartEffect> = channel.receiveAsFlow()

    // TODO(t4): T4CartEffectsTest
    // Announce that `itemName` was added to the cart as a "<itemName> added to
    // cart" message, delivered through `effects` rather than any state field.
    suspend fun addToCart(itemName: String) {
        TODO("t4: send a ShowToast effect announcing itemName was added to the cart")
    }
}
