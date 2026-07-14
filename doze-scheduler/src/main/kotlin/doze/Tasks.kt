package doze

/**
 * Doze / App Standby scheduling practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :doze-scheduler:test
 */

/** A snapshot of the device conditions Doze decides on. */
data class DeviceState(
    val screenOn: Boolean,
    val charging: Boolean,
    val stationary: Boolean,
    val idleMillis: Long
)

// TODO(t1): T1IsDozingTest
// The device has entered Doze only when the screen is off, it is not
// charging, it is stationary (no motion detected), and it has been idle for
// at least `idleThresholdMillis`. If any of those conditions fails, the
// device is not dozing.
fun isDozing(state: DeviceState, idleThresholdMillis: Long): Boolean {
    TODO("t1: true only when screen off, not charging, stationary, and idle long enough")
}

/** How urgently a job wants to run. */
enum class JobPriority { NORMAL, EXPEDITED }

// TODO(t2): T2CanRunImmediatelyTest
// When the device isn't dozing, any job may run right away. When it is
// dozing, only an EXPEDITED job is allowed to run immediately; a NORMAL job
// must wait for the next maintenance window.
fun canRunImmediately(dozing: Boolean, priority: JobPriority): Boolean {
    TODO("t2: not dozing always runs now, dozing only lets EXPEDITED run now")
}

// TODO(t3): T3NextMaintenanceWindowTest
// Doze opens a maintenance window every `intervalMillis`, at 0, intervalMillis,
// 2 * intervalMillis, and so on. Given a job's arrival time, return the
// timestamp of the next maintenance window at or after that arrival (if the
// arrival lands exactly on a window boundary, that same timestamp is the
// answer).
fun nextMaintenanceWindow(arrivalMillis: Long, intervalMillis: Long): Long {
    TODO("t3: round arrivalMillis up to the next multiple of intervalMillis")
}

/** A job waiting to be batched into a maintenance window. */
data class PendingJob(val id: String, val arrivalMillis: Long)

/** A job assigned to the maintenance window it will run in. */
data class ScheduledJob(val id: String, val windowMillis: Long)

/** The result of batching a set of deferred jobs. */
data class BatchResult(val scheduled: List<ScheduledJob>, val radioWakeupsSaved: Int)

// TODO(t4): T4BatchJobsTest
// This is the actual battery win Doze buys you: instead of waking the radio
// once per job, assign every deferred job to its next maintenance window
// (reuse nextMaintenanceWindow) and let jobs that land in the same window
// share a single radio wakeup. radioWakeupsSaved is how many wakeups that
// batching avoided: the number of jobs minus the number of distinct windows
// actually used. An empty job list saves nothing.
fun batchJobs(jobs: List<PendingJob>, intervalMillis: Long): BatchResult {
    TODO("t4: assign each job its next window, count wakeups saved by batching")
}

/** Constraints a job declares it needs before it's allowed to run. */
data class JobConstraints(
    val requiresCharging: Boolean = false,
    val requiresUnmetered: Boolean = false
)

/** The device's current conditions relevant to a job's constraints. */
data class DeviceConditions(val charging: Boolean, val unmetered: Boolean)

// TODO(t5): T5JobMayRunNowTest
// Put t2's Doze/priority check together with a job's declared constraints.
// A job may run now only when it clears the Doze check (see
// canRunImmediately) AND every constraint it declared is currently met by
// the device. An EXPEDITED job still bypasses Doze, but it does NOT bypass
// its own declared constraints: a job that requires charging never runs on
// a device that isn't charging, expedited or not.
fun jobMayRunNow(
    dozing: Boolean,
    priority: JobPriority,
    constraints: JobConstraints,
    device: DeviceConditions
): Boolean {
    TODO("t5: dozing/priority check AND every declared constraint must hold")
}

// TODO(t6): T6BackoffForAttemptTest
// Delay to wait before retrying a failing job, growing exponentially with
// each attempt (attempt is 1-based, so attempt 1 waits baseMillis, attempt 2
// waits baseMillis * 2, attempt 3 waits baseMillis * 4, and so on), but never
// more than capMillis.
fun backoffForAttempt(attempt: Int, baseMillis: Long, capMillis: Long): Long {
    TODO("t6: baseMillis doubled per attempt, capped at capMillis")
}
