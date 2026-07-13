package flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Flow & Operators practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :flow-operators:test
 */

// TODO(t1): T1ColdNumbersTest
// Return a flow that emits 1, 2, 3, calling `onStart` right before it starts
// emitting, every single time it gets collected.
fun coldNumbers(onStart: () -> Unit): Flow<Int> {
    TODO("t1: emit 1, 2, 3, calling onStart at the beginning of every collection")
}

// TODO(t2): T2LatestPairTest
// Return a flow that emits "<number><letter>", combining whichever value just
// arrived from `numbers` or `letters` with the other one's most recent value.
fun latestPair(numbers: Flow<Int>, letters: Flow<String>): Flow<String> {
    TODO("t2: emit '<number><letter>' whenever either source emits, using the other's most recent value")
}

// TODO(t3): T3SafeDivideFlowTest
// Return a flow of 100 divided by each entry in `divisors`, turning a
// divide-by-zero crash into a single -1 value instead of propagating it.
fun safeDivideFlow(divisors: List<Int>): Flow<Int> {
    TODO("t3: emit 100 / d for each d, recovering a divide-by-zero as -1")
}

// TODO(t4): T4SearchResultsTest
// For each value emitted by `queries`, run `search` and emit its result, but
// abandon any still-running search for an older query as soon as a newer one
// arrives.
fun searchResults(queries: Flow<String>, search: suspend (String) -> String): Flow<String> {
    TODO("t4: switch to searching the newest query, cancelling any older search")
}

// TODO(t5): T5NumbersOnTest
// Return a flow that emits 1, 2, 3, reporting the producer's thread name via
// `onProducerThread` before each emission, then runs that upstream work on
// `dispatcher` while leaving collection on whichever context the caller
// collects with.
fun numbersOn(dispatcher: CoroutineDispatcher, onProducerThread: (String) -> Unit): Flow<Int> {
    TODO("t5: emit 1, 2, 3 with the producer running on dispatcher, reporting its thread name")
}

// TODO(t6): T6ConflatedTicksTest
// Return `source` with conflate applied, so a slow collector only ever
// processes the most recently emitted value instead of every one queued up
// behind it.
fun conflatedTicks(source: Flow<Int>): Flow<Int> {
    TODO("t6: return source with conflate applied")
}

class Ticker {
    private var onTick: ((Int) -> Unit)? = null
    var stopCount: Int = 0
        private set

    fun start(onTick: (Int) -> Unit) {
        this.onTick = onTick
    }

    fun tick(value: Int) {
        onTick?.invoke(value)
    }

    fun stop() {
        onTick = null
        stopCount++
    }
}

// TODO(t7): T7ListenerAsFlowTest
// Wrap a callback-style Ticker (calls `onTick(Int)` on each tick; `stop()`
// unregisters it) as a cold Flow<Int> using callbackFlow, unregistering the
// listener when the collector stops.
fun listenerAsFlow(ticker: Ticker): Flow<Int> {
    TODO("t7: wrap the ticker's callback as a cold Flow<Int>, stopping it when collection ends")
}
