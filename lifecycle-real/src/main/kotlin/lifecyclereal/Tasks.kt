package lifecyclereal

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Lifecycle-aware collection practice, using the real androidx.lifecycle APIs (not a
 * hand-rolled stand-in). Each function or class below is broken or unwritten and has a
 * matching test in src/test that is currently RED. Run a single test class from the gutter in
 * Android Studio, or:
 *
 *     ./gradlew :lifecycle-real:test
 */

// TODO(t1): T1RecordingObserverTest
// Implement each DefaultLifecycleObserver callback to append its event name to `events`, so a
// test can see exactly which callbacks fired, and in what order, as the owner moves through
// its states.
class RecordingObserver(private val events: MutableList<String>) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        TODO("t1: events.add(\"CREATE\")")
    }

    override fun onStart(owner: LifecycleOwner) {
        TODO("t1: events.add(\"START\")")
    }

    override fun onResume(owner: LifecycleOwner) {
        TODO("t1: events.add(\"RESUME\")")
    }

    override fun onPause(owner: LifecycleOwner) {
        TODO("t1: events.add(\"PAUSE\")")
    }

    override fun onStop(owner: LifecycleOwner) {
        TODO("t1: events.add(\"STOP\")")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        TODO("t1: events.add(\"DESTROY\")")
    }
}

// TODO(t2): T2RepeatOnLifecycleTest
// Launch `source`'s collection inside repeatOnLifecycle(STARTED) on owner.lifecycle, appending
// every collected value to `collected`. The collector must actually cancel, not just ignore
// values, when the owner drops below STARTED, and must start a fresh collection when it
// reaches STARTED again.
fun collectWhileStarted(
    owner: LifecycleOwner,
    source: Flow<Int>,
    scope: CoroutineScope,
    collected: MutableList<Int>
): Job {
    TODO(
        "t2: scope.launch { owner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) { " +
            "source.collect { collected.add(it) } } }"
    )
}

// TODO(t3): T3PlainLifecycleScopeTest
// Launch `source`'s collection directly on owner.lifecycleScope, with no repeatOnLifecycle
// guard. Unlike collectWhileStarted, this keeps running even while the owner drops below
// STARTED, wasting work in the background until the owner is destroyed.
fun collectAlways(
    owner: LifecycleOwner,
    source: Flow<Int>,
    collected: MutableList<Int>
): Job {
    TODO("t3: owner.lifecycleScope.launch { source.collect { collected.add(it) } }")
}

// TODO(t4): T4FlowWithLifecycleTest
// Wrap `source` with flowWithLifecycle so it only emits while the owner is at least STARTED,
// then collect it on `scope`. This should behave just like collectWhileStarted, but through
// the operator form instead of calling repeatOnLifecycle directly.
fun collectWithFlowWithLifecycle(
    owner: LifecycleOwner,
    source: Flow<Int>,
    scope: CoroutineScope,
    collected: MutableList<Int>
): Job {
    TODO(
        "t4: scope.launch { source.flowWithLifecycle(owner.lifecycle, Lifecycle.State.STARTED)" +
            ".collect { collected.add(it) } }"
    )
}

// TODO(t5): T5LiveDataObservationTest
// Observe `source` the LiveData way, appending every delivered value to `observed`. Unlike a
// raw StateFlow collector, LiveData's own observe() call is already lifecycle-aware: no
// repeatOnLifecycle needed, and no manual re-subscribing as the owner starts and stops.
fun observeWhileActive(
    owner: LifecycleOwner,
    source: LiveData<Int>,
    observed: MutableList<Int>
) {
    TODO("t5: source.observe(owner) { value -> observed.add(value) }")
}
