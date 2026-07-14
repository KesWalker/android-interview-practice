package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: notify listeners only when the value actually changes.
class T2NotifyOnChangeTest {
    @Test fun `notifies listeners with the new value on a real change`() {
        val state = ObservableState(0)
        val seen = mutableListOf<Int>()
        state.observe { seen += it }

        state.update(1)
        state.update(2)

        assertEquals(listOf(1, 2), seen)
        assertEquals(2, state.value)
    }

    @Test fun `does not notify when the new value equals the current one`() {
        val state = ObservableState("idle")
        var notifications = 0
        state.observe { notifications++ }

        state.update("idle")
        state.update("idle")

        assertEquals(0, notifications)
        assertEquals("idle", state.value)
    }

    @Test fun `every observer is notified`() {
        val state = ObservableState(0)
        var firstCalls = 0
        var secondCalls = 0
        state.observe { firstCalls++ }
        state.observe { secondCalls++ }

        state.update(1)

        assertEquals(1, firstCalls)
        assertEquals(1, secondCalls)
    }
}
