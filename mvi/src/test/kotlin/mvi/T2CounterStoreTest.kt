package mvi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: one entry point that funnels every intent through the reducer.
class T2CounterStoreTest {
    @Test fun `starts at the initial state`() {
        val store = CounterStore()
        assertEquals(CounterState(), store.state.value)
    }

    @Test fun `dispatch publishes the reduced state`() {
        val store = CounterStore()
        store.dispatch(CounterIntent.Increment)
        assertEquals(1, store.state.value.count)
    }

    @Test fun `every dispatch runs through the same reducer, in order`() {
        val store = CounterStore(CounterState(count = 10))
        store.dispatch(CounterIntent.Decrement)
        store.dispatch(CounterIntent.Decrement)
        store.dispatch(CounterIntent.Fail("oops"))
        assertEquals(CounterState(count = 8, error = "oops"), store.state.value)
    }
}
