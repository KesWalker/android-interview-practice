package effects

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * EffectLifecycle practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :effect-lifecycle:test
 */

/** A LaunchedEffect-alike: runs a coroutine keyed by `keys`. */
class LaunchedEffectRunner(private val scope: CoroutineScope) {
    private var currentKeys: List<Any?>? = null
    private var currentJob: Job? = null

    // TODO(t1): T1LaunchedEffectTest
    // Launch `block` in `scope`. If `keys` equals the keys from the
    // previous call, leave the currently running job alone (don't
    // relaunch). Otherwise cancel the currently running job (if any) and
    // launch a new one with `block`, remembering the new keys.
    fun launch(keys: List<Any?>, block: suspend CoroutineScope.() -> Unit) {
        TODO("t1: cancel and relaunch only when keys differ from the previous call")
    }

    // TODO(t2): T2CancelOnLeaveTest
    // Cancel the currently running job, if any, and forget the tracked
    // keys, mirroring a LaunchedEffect's coroutine being cancelled when its
    // composable leaves composition.
    fun leave() {
        TODO("t2: cancel the running job and clear tracked state")
    }
}

/** A DisposableEffect-alike: runs an effect and disposes it on key change. */
class DisposableEffectRunner {
    private var currentKeys: List<Any?>? = null
    private var currentDispose: (() -> Unit)? = null

    // TODO(t3): T3DisposableEffectTest
    // Run `effect` (it returns an onDispose cleanup lambda) the first time.
    // Whenever `keys` differs from the keys used on the previous call, call
    // the PREVIOUS effect's onDispose lambda BEFORE running the new effect.
    // When `keys` equals the previous keys, do nothing.
    fun run(keys: List<Any?>, effect: () -> () -> Unit) {
        TODO("t3: dispose the previous effect before running a new one on key change")
    }

    // TODO(t4): T4DisposableLeaveTest
    // Call the current effect's onDispose lambda, if any, and forget the
    // tracked state, mirroring a DisposableEffect being torn down when its
    // composable leaves composition.
    fun leave() {
        TODO("t4: dispose the current effect and clear tracked state")
    }
}

/** A rememberUpdatedState-alike: a slot a running effect can read fresh. */
class UpdatedStateRef<T>(initial: T) {
    private var current: T = initial

    // TODO(t5): T5RememberUpdatedStateTest
    // Replace the tracked value with newValue, so a long-running effect
    // that only captured this UpdatedStateRef (not the value itself) sees
    // the latest value the next time it reads current(), instead of the
    // one that was current when the effect launched.
    fun update(newValue: T) {
        TODO("t5: replace the tracked value with newValue")
    }

    fun current(): T = current
}

// TODO(t6): T6SideEffectTest
// Model SideEffect: run `commit` and, only if it completes without
// throwing, call `publish` with its result and return that result. If
// `commit` throws, propagate the exception and never call `publish`.
fun <T> commitThenPublish(commit: () -> T, publish: (T) -> Unit): T {
    TODO("t6: only call publish after commit completes without throwing")
}
