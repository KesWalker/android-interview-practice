package activitylife

/**
 * Activity lifecycle practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :activity-lifecycle:test
 */

/** Where an Activity instance currently sits in its lifecycle. */
enum class LifecycleState {
    INITIAL, CREATED, STARTED, RESUMED, PAUSED, STOPPED, DESTROYED
}

/** The lifecycle callback Android invokes on an Activity. */
enum class Callback {
    ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_RESTART, ON_DESTROY
}

// TODO(t1): T1NextStateTest
// Apply a single incoming callback to `current` and return the resulting
// state: ON_CREATE moves INITIAL to CREATED, ON_START moves CREATED (or
// STOPPED) to STARTED, ON_RESUME moves STARTED (or PAUSED) to RESUMED,
// ON_PAUSE moves RESUMED to PAUSED, ON_STOP moves PAUSED to STOPPED, and
// ON_DESTROY moves CREATED or STOPPED to DESTROYED. Any other
// (state, callback) combination is not a real Android transition, throw
// IllegalStateException for it.
fun nextState(current: LifecycleState, callback: Callback): LifecycleState {
    TODO("t1: look up the single valid next state for (current, callback), else throw IllegalStateException")
}

// TODO(t2): T2TransitionCallbacksTest
// Both `from` and `to` are one of INITIAL, CREATED, STARTED, RESUMED, in
// that forward order. Return the ordered list of callbacks Android fires to
// walk an Activity from `from` up to `to` (an empty list if they're equal).
// If `to` comes before `from` in that order, this function doesn't model
// that direction, throw IllegalArgumentException.
fun transitionCallbacks(from: LifecycleState, to: LifecycleState): List<Callback> {
    TODO("t2: walk the INITIAL->CREATED->STARTED->RESUMED order, collecting the entry callback for each step past from up to to")
}

// TODO(t3): T3BackgroundingCallbacksTest
// The user backgrounds the app (home button, switching apps) while the
// Activity is in `from`. Return the ordered callbacks Android fires: from
// RESUMED that's [ON_PAUSE, ON_STOP], from STARTED (already paused, e.g.
// partially covered in multi-window) that's just [ON_STOP]. Any other
// starting state can't be backgrounded this way, throw IllegalStateException.
fun backgroundingCallbacks(from: LifecycleState): List<Callback> {
    TODO("t3: RESUMED -> [ON_PAUSE, ON_STOP], STARTED -> [ON_STOP], else throw IllegalStateException")
}

// TODO(t4): T4FinishingCallbacksTest
// The Activity is finished (back button, finish()) while in `from`. Unlike
// backgrounding, finishing always ends in ON_DESTROY. Return the ordered
// callbacks: RESUMED -> [ON_PAUSE, ON_STOP, ON_DESTROY], STARTED ->
// [ON_STOP, ON_DESTROY], CREATED or STOPPED -> [ON_DESTROY]. Any other
// starting state can't be finished this way, throw IllegalStateException.
fun finishingCallbacks(from: LifecycleState): List<Callback> {
    TODO("t4: work from RESUMED/STARTED/CREATED/STOPPED down to a final ON_DESTROY, else throw IllegalStateException")
}

// TODO(t5): T5ResumeFromStopTest
// A STOPPED Activity is coming back to the foreground. If the same instance
// survived (wasRecreated == false), Android fires
// [ON_RESTART, ON_START, ON_RESUME], ON_RESTART is the signal that this
// instance was stopped before, not freshly created. If the process was
// killed and a brand-new instance was created instead (wasRecreated ==
// true), there's no prior instance to "restart": Android fires
// [ON_CREATE, ON_START, ON_RESUME].
fun resumeFromStop(wasRecreated: Boolean): List<Callback> {
    TODO("t5: wasRecreated picks between an ON_RESTART-led sequence and an ON_CREATE-led one")
}

/** A resource whose lifetime should be scoped to a pair of lifecycle callbacks. */
enum class WorkKind {
    VIEW_BINDING, CAMERA_PREVIEW, SENSOR_LISTENER, LOCATION_UPDATES
}

/** The callback that should acquire a resource, and the one that should release it. */
data class ResourceBinding(val acquire: Callback, val release: Callback)

// TODO(t6): T6ResourceBindingTest
// Return where each kind of work legally belongs. VIEW_BINDING is set up
// once in ON_CREATE and torn down in ON_DESTROY. CAMERA_PREVIEW and
// LOCATION_UPDATES are visibility-scoped: started in ON_START, stopped in
// ON_STOP, so they keep running across a transient overlay (a dialog) that
// only pauses the Activity. SENSOR_LISTENER is resume-scoped: registered in
// ON_RESUME and unregistered in ON_PAUSE, because a sensor left running
// while merely visible-but-paused (e.g. covered by a dialog) drains battery
// for no benefit.
fun lifecycleBinding(work: WorkKind): ResourceBinding {
    TODO("t6: map each WorkKind to the acquire/release callback pair that scopes its lifetime correctly")
}
