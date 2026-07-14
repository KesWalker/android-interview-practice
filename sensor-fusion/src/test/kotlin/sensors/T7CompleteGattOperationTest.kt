package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 7: finishing one operation promotes the next queued one, if any.
class T7CompleteGattOperationTest {
    @Test fun `promotes the next pending operation to inFlight`() {
        val opB = GattOperation("read-battery")
        val opC = GattOperation("read-rssi")
        val state = GattQueueState(inFlight = GattOperation("write-config"), pending = listOf(opB, opC))

        val result = completeGattOperation(state)

        assertEquals(opB, result.inFlight)
        assertEquals(listOf(opC), result.pending)
    }

    @Test fun `an empty pending list leaves the queue idle`() {
        val state = GattQueueState(inFlight = GattOperation("write-config"), pending = emptyList())

        val result = completeGattOperation(state)

        assertNull(result.inFlight)
        assertEquals(emptyList<GattOperation>(), result.pending)
    }

    @Test fun `completing an already-idle queue is a no-op`() {
        val state = GattQueueState(inFlight = null, pending = emptyList())

        val result = completeGattOperation(state)

        assertEquals(state, result)
    }
}
