package structured

/**
 * Structured Concurrency & Cancellation practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :coroutines-structured:test
 */

// TODO(t1): T1RunTogetherOrFailAllTest
// Run every task in `work` at the same time; if any one of them throws, the
// whole call fails and every other still-running task is stopped.
suspend fun runTogetherOrFailAll(work: List<suspend () -> Unit>) {
    TODO("t1: run every task at once; one failure fails the call and stops the rest")
}

// TODO(t2): T2ProcessWithCleanupTest
// Run `work`, then always run `onCleanup` afterwards - even if `work` throws,
// or this coroutine gets cancelled partway through `work`.
suspend fun processWithCleanup(work: suspend () -> Unit, onCleanup: suspend () -> Unit) {
    TODO("t2: run work, then always run onCleanup, even on failure or cancellation")
}

// TODO(t3): T3RunIsolatedReportingTest
// Run every task in `tasks` at the same time. If one throws, hand its
// exception to `onError` instead of throwing it, and let the rest keep running.
suspend fun runIsolatedReporting(tasks: List<suspend () -> Unit>, onError: (Throwable) -> Unit) {
    TODO("t3: run every task at once, routing a failure to onError without stopping the others")
}

// TODO(t4): T4LoadAllResultsTest
// Run every task in `tasks` at the same time and return one Result per task,
// in the same order, whether it succeeded or failed - one failure must not
// stop or lose the others.
suspend fun loadAllResults(tasks: List<suspend () -> Int>): List<Result<Int>> {
    TODO("t4: run every task at once, collecting a Result per task in order")
}

// TODO(t5): T5SumReportingErrorsTest
// Sum the results of `entries`, reporting any of an entry's ordinary exceptions
// to `onError` and continuing with the rest - but check `ensureActive()` every
// iteration, and never treat a CancellationException as just another error to
// report.
suspend fun sumReportingErrors(entries: List<suspend () -> Int>, onError: (Throwable) -> Unit): Int {
    TODO("t5: sum the entries, reporting ordinary failures to onError but rethrowing cancellation")
}

// TODO(t6): T6RunWithDeadlineTest
// Run `work`, but cancel it and fail the call if it hasn't finished within
// `timeoutMs`.
suspend fun <T> runWithDeadline(timeoutMs: Long, work: suspend () -> T): T {
    TODO("t6: run work under a deadline, failing the call if it overruns timeoutMs")
}
