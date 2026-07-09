package stateflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * StateFlow & SharedFlow practice.
 *
 * Each piece below is broken or unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to implement it
 * idiomatically so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :stateflow-sharedflow:test
 */

// TODO(t1): CounterTest
class Counter {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    // Increase the count by one, even when many callers do this at the same time.
    fun increment() {
        TODO("t1: increase count by 1, safely under concurrent calls")
    }
}

sealed interface LoginEvent {
    data class ShowError(val message: String) : LoginEvent
}

// TODO(t2): LoginControllerTest
class LoginController {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    // While checking the password (simulate 50ms), report loading, then report
    // a one-off failure if the password turns out to be blank.
    suspend fun login(password: String) {
        TODO("t2: report loading around a simulated 50ms check, fire a one-off failure event when password is blank")
    }
}

// TODO(t3): NotificationCenterTest
class NotificationCenter {
    // Keep the 2 most-recent messages available for late subscribers, and
    // never make a publisher wait for room in the buffer.
    private val _notifications: MutableSharedFlow<String> =
        TODO("t3: keep the last 2 messages for late subscribers, never suspend a publisher")
    val notifications: SharedFlow<String> = _notifications.asSharedFlow()

    // Send message to every current and future subscriber, reporting whether
    // it was accepted.
    fun publish(message: String): Boolean {
        TODO("t3: publish message without ever suspending the caller")
    }
}

// TODO(t4): ToHotStateFlowTest
// Share `source` as a single running flow across every collector, seeded with
// `initial`, that only runs while at least one collector is present.
fun <T> toHotStateFlow(scope: CoroutineScope, source: Flow<T>, initial: T): StateFlow<T> {
    TODO("t4: share source as one hot flow seeded with initial, only running while collected")
}
