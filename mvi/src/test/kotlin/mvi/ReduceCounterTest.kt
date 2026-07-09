package mvi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 1: a pure reducer -- (state, intent) -> newState.
class ReduceCounterTest {
    @Test fun `increment adds one`() {
        assertEquals(CounterState(count = 1), reduceCounter(CounterState(count = 0), CounterIntent.Increment))
    }

    @Test fun `decrement subtracts one`() {
        assertEquals(CounterState(count = 4), reduceCounter(CounterState(count = 5), CounterIntent.Decrement))
    }

    @Test fun `fail sets the error message without touching count`() {
        val result = reduceCounter(CounterState(count = 2), CounterIntent.Fail("boom"))
        assertEquals(2, result.count)
        assertEquals("boom", result.error)
    }

    @Test fun `clearError removes the error but keeps count`() {
        val result = reduceCounter(CounterState(count = 3, error = "boom"), CounterIntent.ClearError)
        assertEquals(3, result.count)
        assertNull(result.error)
    }

    @Test fun `folds a sequence of intents left to right`() {
        val intents = listOf(
            CounterIntent.Increment,
            CounterIntent.Increment,
            CounterIntent.Decrement,
            CounterIntent.Fail("x")
        )
        val result = intents.fold(CounterState()) { state, intent -> reduceCounter(state, intent) }
        assertEquals(CounterState(count = 1, error = "x"), result)
    }
}
