package flowbasics

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

/**
 * Flow basics practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :flow-basics:test
 */

/** A callback-based API you don't control, the kind Flow is meant to wrap. */
interface Ticker {
    fun start(onTick: (Int) -> Unit)
    fun stop()
}

// TODO(t1): T1ColdFlowTest
// Build a cold `flow { }` producer that counts up from 1 to `count`. Call
// `onStart` once, synchronously, right before emitting the first value, so a
// test can prove the producer block re-runs from scratch every time the
// flow is collected: a cold flow has no memory of previous collectors.
fun countUpFlow(count: Int, onStart: () -> Unit): Flow<Int> {
    TODO("t1: flow { onStart(); for (i in 1..count) emit(i) }")
}

// TODO(t2): T2MapFilterTakeTest
// Square every value from `source`, keep only the even squares, then stop
// after the first `limit` of them. Chain map/filter/take, don't collect
// inside this function.
fun firstEvenSquares(source: Flow<Int>, limit: Int): Flow<Int> {
    TODO("t2: source.map { it * it }.filter { it % 2 == 0 }.take(limit)")
}

// TODO(t3): T3RunningTotalsTest
// Turn `source` into its list of running totals (the running sum after each
// emission), using a terminal operator to materialize the flow into a List.
suspend fun runningTotals(source: Flow<Int>): List<Int> {
    TODO("t3: source.scan(0) { acc, v -> acc + v }.drop(1).toList()")
}

// TODO(t4): T4UpstreamCatchTest
// Collect `source` into a List, but if the upstream flow throws partway
// through, catch that exception with the `.catch { }` operator and emit
// `fallback` in its place so collection still completes normally.
suspend fun collectWithFallback(source: Flow<Int>, fallback: Int): List<Int> {
    TODO("t4: source.catch { emit(fallback) }.toList()")
}

// TODO(t5): T5CollectorThrowsTest
// Collect `source` yourself with `.collect { }`, counting every value until
// you see `failOn`, at which point your own collector lambda throws. That's
// a collector-side failure: an upstream `.catch { }` never sees exceptions
// thrown inside the collect block, only a try/catch wrapped around the
// whole `collect` call does. Return how many values were counted before
// the throw.
suspend fun countUntilCollectorThrows(source: Flow<Int>, failOn: Int): Int {
    TODO("t5: try { source.collect { throw ... on failOn, else count++ } } catch (e: IllegalStateException) { }")
}

// TODO(t6): T6CallbackFlowTest
// Adapt the callback-based `Ticker` into a cold Flow<Int> with callbackFlow.
// Start the ticker, forward every tick into the flow with trySend, and make
// sure ticker.stop() runs once the collector goes away, using awaitClose.
fun tickerFlow(ticker: Ticker): Flow<Int> {
    TODO("t6: callbackFlow { ticker.start { trySend(it) }; awaitClose { ticker.stop() } }")
}
