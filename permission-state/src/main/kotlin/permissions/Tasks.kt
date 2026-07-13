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

enum class LocationPrecision {
    FINE,
    COARSE,
    DENIED
}

data class LocationRequestResult(val fineGranted: Boolean, val coarseGranted: Boolean)

enum class LocationGrantMode {
    Always,
    OneTime,
    Denied
}

// TODO(t5): T5LocationAccessTest
// Work out what precision of location access was actually granted from a
// foreground-location request result, given Android 12's Precise/Approximate
// toggle that lets a user grant only coarse access even when fine was requested.
fun locationAccess(result: LocationRequestResult): LocationPrecision {
    TODO("t5: report the precision this result actually granted")
}

// TODO(t6): T6CanRequestBackgroundLocationTest
// Decide whether the app is allowed to request ACCESS_BACKGROUND_LOCATION yet,
// given Android 10+'s rule that background location can only be requested as a
// separate step after foreground location is already granted.
fun canRequestBackgroundLocation(foregroundState: PermissionState): Boolean {
    TODO("t6: allow the background request only once foreground location is granted")
}

// TODO(t7): T7AfterBackgroundingTest
// Work out what a location grant mode becomes after the app spends time in the
// background, given that Android's "Only this time" one-time grant is
// automatically revoked once the app has been backgrounded for a while, while
// an Always grant is unaffected.
fun afterBackgrounding(mode: LocationGrantMode, wasLongBackground: Boolean): LocationGrantMode {
    TODO("t7: revoke a one-time grant after a long background stint, leave the rest alone")
}
