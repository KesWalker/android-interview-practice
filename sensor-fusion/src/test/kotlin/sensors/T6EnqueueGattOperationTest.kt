package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: a second request queues instead of clobbering the one already running.
class T6EnqueueGattOperationTest {
    @Test fun `an idle queue starts the operation immediately`() {
        val state = GattQueueState(inFlight = null, pending = emptyList())
        val op = GattOperation("read-battery")

        val result = enqueueGattOperation(state, op)

        assertEquals(op, result.inFlight)
        assertEquals(emptyList<GattOperation>(), result.pending)
    }

    @Test fun `a busy queue appends to pending without disturbing inFlight`() {
        val inFlight = GattOperation("write-config")
        val state = GattQueueState(inFlight = inFlight, pending = emptyList())
        val op = GattOperation("read-battery")

        val result = enqueueGattOperation(state, op)

        assertEquals(inFlight, result.inFlight)
        assertEquals(listOf(op), result.pending)
    }

    @Test fun `pending operations are appended in submission order`() {
        val inFlight = GattOperation("write-config")
        val opB = GattOperation("read-battery")
        val state = GattQueueState(inFlight = inFlight, pending = listOf(opB))
        val opC = GattOperation("read-rssi")

        val result = enqueueGattOperation(state, opC)

        assertEquals(listOf(opB, opC), result.pending)
    }
}
