package coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

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

// TODO(t1): FetchGreetingTest
// Suspend, simulate 200ms of network latency, then return "Hello, <name>!".
suspend fun fetchGreeting(name: String): String {
    TODO("t1: after simulating 200ms of latency, return 'Hello, <name>!'")
}

// TODO(t2): SumConcurrentlyTest
// Run both suspend functions `a` and `b` at the same time and return the sum
// of their results.
suspend fun sumConcurrently(a: suspend () -> Int, b: suspend () -> Int): Int {
    TODO("t2: run a and b at the same time, return the sum of their results")
}

// TODO(t3): RunOnTest
// Run `block` using the given dispatcher and return its result.
suspend fun <T> runOn(dispatcher: CoroutineDispatcher, block: () -> T): T {
    TODO("t3: run block on the given dispatcher and return its result")
}

// TODO(t4): CountTicksTest
// Count ticks from 1 up to `limit`, calling `onTick` with the running count
// on every tick, but stop early and return the count so far if this
// coroutine gets cancelled before reaching `limit`.
suspend fun CoroutineScope.countTicks(limit: Int, onTick: (Int) -> Unit): Int {
    TODO("t4: count up to limit calling onTick, stopping early if cancelled")
}
