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

    // TODO(t1): EnqueueOrderTest
    // Add a job to the back of the queue.
    fun enqueue(job: Job) {
        TODO("t1: append job to the queue")
    }

    // TODO(t1): EnqueueOrderTest
    // Return the ids of the jobs still waiting to run, in the order they'll run.
    fun pendingIds(): List<String> {
        TODO("t1: ids of the still-pending jobs, in run order")
    }

    // TODO(t2): RetryUntilSuccessTest
    // TODO(t3): GiveUpAfterMaxAttemptsTest
    // Remove the next queued job and run its action, retrying on failure up to
    // its maxAttempts, then report how many attempts it took and whether it
    // ultimately succeeded. Return null when the queue is empty.
    fun runNext(): JobResult? {
        TODO("t2/t3: run the front job's action up to maxAttempts times and report the outcome")
    }
}

// TODO(t4): BackoffDelayTest
// Delay to wait before the given retry attempt (1-based), growing with each attempt.
fun backoffMillis(attempt: Int, baseMillis: Long): Long {
    TODO("t4: delay before retry `attempt`, based on baseMillis")
}
