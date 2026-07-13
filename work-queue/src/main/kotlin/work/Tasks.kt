package work

/**
 * Background work queue with retry/backoff practice.
 *
 * Each piece below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :work-queue:test
 */

data class Job(
    val id: String,
    val maxAttempts: Int,
    val action: () -> Boolean
)

data class JobResult(
    val id: String,
    val attempts: Int,
    val succeeded: Boolean
)

class WorkQueue {
    private val pending = mutableListOf<Job>()

    // TODO(t1): T1EnqueueOrderTest
    // Add a job to the back of the queue.
    fun enqueue(job: Job) {
        TODO("t1: append job to the queue")
    }

    // TODO(t1): T1EnqueueOrderTest
    // Return the ids of the jobs still waiting to run, in the order they'll run.
    fun pendingIds(): List<String> {
        TODO("t1: ids of the still-pending jobs, in run order")
    }

    // TODO(t2): T2RetryUntilSuccessTest
    // TODO(t3): T3GiveUpAfterMaxAttemptsTest
    // Remove the next queued job and run its action, retrying on failure up to
    // its maxAttempts, then report how many attempts it took and whether it
    // ultimately succeeded. Return null when the queue is empty.
    fun runNext(): JobResult? {
        TODO("t2/t3: run the front job's action up to maxAttempts times and report the outcome")
    }
}

// TODO(t4): T4BackoffDelayTest
// Delay to wait before the given retry attempt (1-based), growing with each attempt.
fun backoffMillis(attempt: Int, baseMillis: Long): Long {
    TODO("t4: delay before retry `attempt`, based on baseMillis")
}

enum class StartCommandFlag { STICKY, NOT_STICKY, REDELIVER_INTENT }

data class RestartOutcome(
    val recreated: Boolean,
    val intentRedelivered: Boolean
)

// TODO(t5): T5RestartBehaviorTest
// Work out what happens when the system kills and restarts a started service,
// given its onStartCommand return flag and whether a start intent was still
// pending when it died.
fun restartBehavior(flag: StartCommandFlag, hadPendingStartIntent: Boolean): RestartOutcome {
    TODO("t5: map the start-command flag + pending intent to a restart outcome")
}

enum class AppProcessState { FOREGROUND, BACKGROUND, BACKGROUND_EXEMPT }

// TODO(t6): T6CanStartForegroundServiceTest
// Decide whether the app is currently allowed to call startForegroundService(),
// given Android 12's rule that a non-exempt app already in the background can no
// longer start one.
fun canStartForegroundService(state: AppProcessState): Boolean {
    TODO("t6: allow the start unless the app is plainly in the background")
}

data class WorkConstraints(
    val requiresUnmeteredNetwork: Boolean = false,
    val requiresCharging: Boolean = false,
    val requiresBatteryNotLow: Boolean = false
)

data class DeviceConditions(
    val isUnmetered: Boolean,
    val isCharging: Boolean,
    val isBatteryLow: Boolean
)

// TODO(t7): T7ConstraintsSatisfiedTest
// Decide whether a job's declared constraints (network, charging, battery-not-low)
// are currently satisfied by the device -- the check the system runs before it
// will even attempt to run the work.
fun constraintsSatisfied(constraints: WorkConstraints, device: DeviceConditions): Boolean {
    TODO("t7: true only when every required constraint is met by the device")
}

enum class SchedulerChoice { ALARM_MANAGER, WORK_MANAGER, COROUTINE }

data class WorkCharacteristics(
    val needsExactTime: Boolean,
    val mustSurviveProcessDeath: Boolean
)

// TODO(t8): T8ChooseSchedulerTest
// Pick the right background-work tool for a job's characteristics: exact
// wall-clock timing needs AlarmManager, guaranteed work that must survive process
// death or reboot needs WorkManager, and everything else just needs a coroutine.
fun chooseScheduler(work: WorkCharacteristics): SchedulerChoice {
    TODO("t8: pick the scheduler that matches the job's characteristics")
}
