package flow

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: callbackFlow + awaitClose to wrap a callback-style listener API.
class T7ListenerAsFlowTest {
    @Test fun `delivers each tick from the listener as a flow emission, in order`() = runTest {
        val ticker = Ticker()
        val received = mutableListOf<Int>()

        val job = launch {
            listenerAsFlow(ticker).collect { received.add(it) }
        }
        runCurrent() // let the flow start collecting and register with the ticker

        ticker.tick(1)
        ticker.tick(2)
        ticker.tick(3)
        runCurrent()

        job.cancel()
        job.join()

        assertEquals(listOf(1, 2, 3), received)
    }

    @Test fun `stops the ticker exactly once when collection is cancelled`() = runTest {
        val ticker = Ticker()

        val job = launch {
            listenerAsFlow(ticker).collect { }
        }
        runCurrent()

        ticker.tick(1)
        runCurrent()

        job.cancel()
        job.join()

        assertEquals(1, ticker.stopCount)
    }
}
