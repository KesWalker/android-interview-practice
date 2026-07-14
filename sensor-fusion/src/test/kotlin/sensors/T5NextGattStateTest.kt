package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: the GATT connection state machine, and rejecting illegal transitions.
class T5NextGattStateTest {
    @Test fun `walks the real connection sequence forward`() {
        assertEquals(GattState.CONNECTING, nextGattState(GattState.DISCONNECTED, GattEvent.CONNECT))
        assertEquals(GattState.CONNECTED, nextGattState(GattState.CONNECTING, GattEvent.CONNECTION_ESTABLISHED))
        assertEquals(
            GattState.DISCOVERING_SERVICES,
            nextGattState(GattState.CONNECTED, GattEvent.START_SERVICE_DISCOVERY)
        )
        assertEquals(GattState.READY, nextGattState(GattState.DISCOVERING_SERVICES, GattEvent.SERVICES_DISCOVERED))
    }

    @Test fun `DISCONNECT is legal from every non-disconnected state and always lands on DISCONNECTED`() {
        assertEquals(GattState.DISCONNECTED, nextGattState(GattState.CONNECTING, GattEvent.DISCONNECT))
        assertEquals(GattState.DISCONNECTED, nextGattState(GattState.CONNECTED, GattEvent.DISCONNECT))
        assertEquals(GattState.DISCONNECTED, nextGattState(GattState.DISCOVERING_SERVICES, GattEvent.DISCONNECT))
        assertEquals(GattState.DISCONNECTED, nextGattState(GattState.READY, GattEvent.DISCONNECT))
    }

    @Test fun `skipping a step is rejected`() {
        assertThrows(IllegalStateException::class.java) {
            nextGattState(GattState.CONNECTING, GattEvent.START_SERVICE_DISCOVERY)
        }
    }

    @Test fun `an event that requires an active connection is rejected while disconnected`() {
        assertThrows(IllegalStateException::class.java) {
            nextGattState(GattState.DISCONNECTED, GattEvent.CONNECTION_ESTABLISHED)
        }
    }

    @Test fun `disconnecting while already disconnected is rejected`() {
        assertThrows(IllegalStateException::class.java) {
            nextGattState(GattState.DISCONNECTED, GattEvent.DISCONNECT)
        }
    }
}
