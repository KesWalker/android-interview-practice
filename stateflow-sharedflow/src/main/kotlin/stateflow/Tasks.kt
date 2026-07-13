package stateflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
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

// TODO(t1): T1CounterTest
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

// TODO(t2): T2LoginControllerTest
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

// TODO(t3): T3NotificationCenterTest
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

// TODO(t4): T4ToHotStateFlowTest
// Share `source` as a single running flow across every collector, seeded with
// `initial`, that only runs while at least one collector is present.
fun <T> toHotStateFlow(scope: CoroutineScope, source: Flow<T>, initial: T): StateFlow<T> {
    TODO("t4: share source as one hot flow seeded with initial, only running while collected")
}

// TODO(t5): T5BoundedEventBusTest
class BoundedEventBus {
    private val _events = MutableSharedFlow<Int>()
    val events: SharedFlow<Int> = _events.asSharedFlow()

    // Publish a value onto a shared event bus backed by a default
    // MutableSharedFlow. With a subscriber attached but not yet ready, the call
    // must suspend until it takes the value (the default SUSPEND buffer policy)
    // rather than dropping it.
    suspend fun publish(value: Int) {
        TODO("t5: emit value, suspending until an attached subscriber takes it")
    }
}

enum class LifecycleState { CREATED, STARTED, STOPPED }

// TODO(t6): T6LifecycleAwareCollectorTest
class LifecycleAwareCollector<T>(
    private val scope: CoroutineScope,
    private val source: Flow<T>,
    private val onEach: (T) -> Unit,
) {
    private var job: Job? = null

    // Start collecting `source` fresh each time the lifecycle reaches STARTED, and
    // fully cancel (not merely pause) that collection the moment it drops below
    // STARTED -- the repeatOnLifecycle(STARTED) contract, modelled without any real
    // Android Lifecycle class.
    fun onStateChanged(state: LifecycleState) {
        TODO("t6: collect source in a fresh job on STARTED, cancel it entirely below STARTED")
    }
}

// TODO(t7): T7ToSharedEventsTest
// Share one upstream flow across every collector as a live SharedFlow of one-off
// events, no replay and no initial value, only running while at least one
// collector is present.
fun <T> toSharedEvents(scope: CoroutineScope, source: Flow<T>): SharedFlow<T> {
    TODO("t7: share source as a hot SharedFlow with no replay, running only while collected")
}
