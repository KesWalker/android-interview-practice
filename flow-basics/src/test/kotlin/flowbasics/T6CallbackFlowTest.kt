package flowbasics

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T6CallbackFlowTest {

    private class FakeTicker : Ticker {
        var started = false
            private set
        var stopped = false
            private set

        override fun start(onTick: (Int) -> Unit) {
            started = true
            onTick(1)
            onTick(2)
            onTick(3)
        }

        override fun stop() {
            stopped = true
        }
    }

    @Test
    fun `forwards ticks from the callback source`() = runTest {
        val ticker = FakeTicker()

        val result = tickerFlow(ticker).take(3).toList()

        assertEquals(listOf(1, 2, 3), result)
        assertTrue(ticker.started)
    }

    @Test
    fun `stops the ticker once the collector goes away`() = runTest {
        val ticker = FakeTicker()

        tickerFlow(ticker).take(3).toList()

        assertTrue(ticker.stopped)
    }
}
