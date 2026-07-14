package lifecyclecollect

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

/**
 * Lifecycle-aware collection practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :lifecycle-collect:test
 */

/** A simplified lifecycle: three states, in increasing order. */
enum class LifecycleState { CREATED, STARTED, RESUMED }

/** A lifecycle owner you can drive by hand from a test. */
class FakeLifecycleOwner(initial: LifecycleState = LifecycleState.CREATED) {
    private val _state = MutableStateFlow(initial)
    val state: StateFlow<LifecycleState> = _state.asStateFlow()

    fun moveTo(newState: LifecycleState) {
        _state.value = newState
    }
}

/** Counts something that happened, so a test can prove whether it happened at all. */
class Counter {
    var value: Int = 0
        private set

    fun increment() {
        value++
    }
}

/**
 * A cold flow wrapping a Channel: every element pulled out of [source] bumps
 * [producedCounter] right before it's re-emitted downstream. Because it's cold
 * and Channel-backed, [producedCounter] only advances while something is
 * actively collecting -- cancel the collector and production stops dead, but
 * whatever's still sitting in the channel is picked up by whichever collector
 * reads it next.
 */
fun countedSource(source: Channel<Int>, producedCounter: Counter): Flow<Int> = flow {
    for (value in source) {
        producedCounter.increment()
        emit(value)
    }
}

// TODO(t1): T1AtLeastTest
// Return true when `state` is the same as `min` or further along the
// CREATED -> STARTED -> RESUMED lifecycle, false otherwise.
fun atLeast(state: LifecycleState, min: LifecycleState): Boolean {
    TODO("t1: compare state and min by their position in CREATED -> STARTED -> RESUMED")
}

// TODO(t2): T2RepeatOnStartedTest
// Model repeatOnLifecycle(STARTED): watch owner.state, and every time it
// crosses into STARTED or beyond, launch a fresh child coroutine collecting
// `source` and forwarding each value to `onValue`. Every time it drops back
// below STARTED, CANCEL that child coroutine outright (don't just stop
// forwarding) so nothing keeps running while the screen isn't visible.
// Returns the outer Job watching the lifecycle, so the caller can shut the
// whole thing down.
fun repeatOnStarted(
    owner: FakeLifecycleOwner,
    scope: CoroutineScope,
    source: Flow<Int>,
    onValue: (Int) -> Unit,
): Job {
    TODO("t2: launch a fresh collector of source on STARTED, cancel it below STARTED")
}

// TODO(t3): T3LaunchWhenStartedTest
// Model the older launchWhenStarted(): start collecting `source` exactly
// once and never cancel that collection for the lifetime of the returned
// Job. Forward a value to `onValue` only while owner.state is currently at
// least STARTED; while it isn't, keep collecting anyway (the upstream keeps
// producing) and just drop the value instead of delivering it.
fun launchWhenStarted(
    owner: FakeLifecycleOwner,
    scope: CoroutineScope,
    source: Flow<Int>,
    onValue: (Int) -> Unit,
): Job {
    TODO("t3: collect source once for good, gate delivery on state without ever cancelling it")
}

// TODO(t4): T4ColdTicksTest
// Return a flow that increments `startCounter` once right before it starts
// emitting, then emits 1, 2, 3. Being cold means this startup step re-runs
// from scratch every single time something collects the flow, not just the
// first time.
fun coldTicks(startCounter: Counter): Flow<Int> {
    TODO("t4: bump startCounter once per collection, then emit 1, 2, 3")
}

// TODO(t5): T5SharedTicksTest
// Wrap coldTicks(startCounter) with shareIn so every collector attaches to
// the SAME running upstream instead of starting a new one: share it in
// `scope`, start producing eagerly the moment this function is called
// (SharingStarted.Eagerly), and replay all 3 values to any collector that
// attaches after the fact (replay = 3).
fun sharedTicks(scope: CoroutineScope, startCounter: Counter): SharedFlow<Int> {
    TODO("t5: coldTicks(startCounter).shareIn(scope, SharingStarted.Eagerly, replay = 3)")
}
