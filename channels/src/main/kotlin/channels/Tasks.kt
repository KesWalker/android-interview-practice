package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select

/**
 * Channels practice.
 *
 * Each function below is unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * make the test go GREEN. Run a single test class from the gutter
 * in Android Studio, or run them all with:
 *
 *     ./gradlew :channels:test
 */

// TODO(t1): T1ProduceSquaresTest
// Use produce {} to return a ReceiveChannel that sends the squares of 1..n.
fun CoroutineScope.produceSquares(n: Int): ReceiveChannel<Int> {
    TODO("t1: produce { send squares of 1..n }")
}

// TODO(t2): T2PipelineTest
// Return a ReceiveChannel that doubles each value from the source channel.
fun CoroutineScope.doubled(source: ReceiveChannel<Int>): ReceiveChannel<Int> {
    TODO("t2: produce { read source, send doubled values }")
}

// TODO(t3): T3FanInTest
// Merge all input channels into a single output channel.
fun <T> CoroutineScope.fanIn(inputs: List<ReceiveChannel<T>>): ReceiveChannel<T> {
    TODO("t3: create output channel, launch per input, forward all elements")
}

// TODO(t4): T4ConflatedLatestTest
// Send 1..n into a CONFLATED channel and return the single received value.
suspend fun conflatedLatest(n: Int): Int {
    TODO("t4: create conflated channel, send 1..n, receive once")
}

// TODO(t5): T5SelectFirstTest
// Return whichever channel's value arrives first using select.
suspend fun <T> selectFirst(a: ReceiveChannel<T>, b: ReceiveChannel<T>): T {
    TODO("t5: select { a.onReceive { it }; b.onReceive { it } }")
}

// TODO(t6): T6ChannelToFlowTest
// Convert a ReceiveChannel into a Flow using channelFlow.
fun <T> channelToFlow(channel: ReceiveChannel<T>): Flow<T> {
    TODO("t6: channelFlow { for (v in channel) send(v) }")
}
