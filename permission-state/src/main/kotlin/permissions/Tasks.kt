package permissions

/**
 * Runtime Permissions practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to model
 * the permission state machine so its test goes GREEN. Run a single test
 * class from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :permission-state:test
 */

sealed class PermissionState {
    object NotRequested : PermissionState()
    object Granted : PermissionState()
    object Denied : PermissionState()
    object PermanentlyDenied : PermissionState()
}

enum class PermissionAction {
    REQUEST,
    PROCEED,
    OPEN_SETTINGS
}

data class PermissionResult(val granted: Boolean, val showRationale: Boolean)

// TODO(t1): T1NextStateTest
// Work out the next permission state after a request result.
fun nextState(current: PermissionState, result: PermissionResult): PermissionState {
    TODO("t1: compute the state that follows `current` given this request result")
}

// TODO(t2): T2ShouldShowRationaleTest
// Decide whether a rationale should be shown before re-requesting.
fun shouldShowRationale(current: PermissionState): Boolean {
    TODO("t2: true only when the user has denied exactly once so far")
}

// TODO(t3): T3ActionForTest
// Decide what the UI should do next for a given permission state.
fun actionFor(current: PermissionState): PermissionAction {
    TODO("t3: map each state to the action the UI should take next")
}

// TODO(t4): T4FinalStateTest
// Work out the final permission state after a sequence of request results.
fun finalState(events: List<PermissionResult>): PermissionState {
    TODO("t4: fold the sequence of request results into the final state")
}
