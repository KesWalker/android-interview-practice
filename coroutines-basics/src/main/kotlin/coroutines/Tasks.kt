package coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * Coroutines Basics practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :coroutines-basics:test
 */

// TODO(t1): T1FetchGreetingTest
// Suspend, simulate 200ms of network latency, then return "Hello, <name>!".
suspend fun fetchGreeting(name: String): String {
    TODO("t1: after simulating 200ms of latency, return 'Hello, <name>!'")
}

// TODO(t2): T2SumConcurrentlyTest
// Run both suspend functions `a` and `b` at the same time and return the sum
// of their results.
suspend fun sumConcurrently(a: suspend () -> Int, b: suspend () -> Int): Int {
    TODO("t2: run a and b at the same time, return the sum of their results")
}

// TODO(t3): T3RunOnTest
// Run `block` using the given dispatcher and return its result.
suspend fun <T> runOn(dispatcher: CoroutineDispatcher, block: () -> T): T {
    TODO("t3: run block on the given dispatcher and return its result")
}

// TODO(t4): T4CountTicksTest
// Count ticks from 1 up to `limit`, calling `onTick` with the running count
// on every tick, but stop early and return the count so far if this
// coroutine gets cancelled before reaching `limit`.
suspend fun CoroutineScope.countTicks(limit: Int, onTick: (Int) -> Unit): Int {
    TODO("t4: count up to limit calling onTick, stopping early if cancelled")
}

// TODO(t5): T5FireAndForgetTest
// Launch `block` on `scope` without waiting for it, returning the Job the caller
// can join() or cancel(). The side effect only happens if the job runs to completion.
fun fireAndForget(scope: CoroutineScope, block: suspend () -> Unit): Job {
    TODO("t5: launch block on scope and return its Job without waiting for it")
}

// TODO(t6): T6BlockingCallTest
// Bridge a suspending `block` into a blocking call site: block the calling
// thread until `block` finishes, running it on that same thread, and return its result.
fun <T> blockingCall(block: suspend () -> T): T {
    TODO("t6: block the calling thread until the suspend block finishes, returning its result")
}
