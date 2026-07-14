package viewmodelreal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel practice, using the real androidx.lifecycle ViewModel APIs (not a hand-rolled
 * stand-in). Each class below is broken or unwritten and has a matching test in src/test that
 * is currently RED. Run a single test class from the gutter in Android Studio, or:
 *
 *     ./gradlew :viewmodel-real:test
 */

// TODO(t1): T1CounterViewModelTest
// Expose `count` as read-only, and mutate it in increment/reset via
// MutableStateFlow.update {}, never by reassigning `.value` directly.
class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() {
        _count.update { it + 1 }
    }

    fun reset() {
        _count.update { 0 }
    }
}

// TODO(t2): T2TickerViewModelTest
// Launch a repeating coroutine in viewModelScope that increments `tickCount` once a second,
// forever. viewModelScope is cancelled automatically the moment the ViewModel is cleared
// (right before onCleared runs), so this loop must actually stop producing ticks once that
// happens, not just get ignored.
class TickerViewModel : ViewModel() {
    private val _tickCount = MutableStateFlow(0)
    val tickCount: StateFlow<Int> = _tickCount.asStateFlow()

    fun startTicking() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _tickCount.update { it + 1 }
            }
        }
    }
}

// TODO(t3): T3NoteViewModelTest
// Back `noteState` with savedStateHandle.getStateFlow so the current note is restored after a
// simulated process death, and make `updateNote` write through the same handle so a ViewModel
// rebuilt with it picks up the latest value.
class NoteViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val noteState: StateFlow<String> = savedStateHandle.getStateFlow(KEY_NOTE, "")

    fun updateNote(text: String) {
        savedStateHandle[KEY_NOTE] = text
    }

    companion object {
        const val KEY_NOTE = "note"
    }
}

// TODO(t4): T4GreeterViewModelFactoryTest
// Build a GreeterViewModel using the greeting this factory was constructed with, not a
// hardcoded value, so ViewModelProvider can create one without ever calling GreeterViewModel's
// constructor directly.
class GreeterViewModel(val greeting: String) : ViewModel()

class GreeterViewModelFactory(private val greeting: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return GreeterViewModel(greeting) as T
    }
}

/** A repository whose data a screen wants to observe as UI state. */
interface ItemsRepository {
    val items: Flow<List<String>>
}

// TODO(t5): T5ItemsViewModelTest
// Turn the repository's cold Flow into UI state with stateIn, so every collector shares one
// upstream subscription: it starts on the first subscriber and is kept alive for 5 seconds
// after the last one leaves, so a quick rotation doesn't retrigger the repository's fetch.
class ItemsViewModel(repository: ItemsRepository) : ViewModel() {
    val items: StateFlow<List<String>> = repository.items.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}
