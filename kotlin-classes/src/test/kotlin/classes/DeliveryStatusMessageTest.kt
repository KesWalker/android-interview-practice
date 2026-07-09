package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: an exhaustive mapping over a closed hierarchy.
class DeliveryStatusMessageTest {
    @Test fun `placed message includes the order id`() {
        assertEquals("Order A1 has been placed.", statusMessage(Placed("A1")))
    }

    @Test fun `shipped message includes the tracking code`() {
        assertEquals(
            "Your order is on the way (tracking: TRK9).",
            statusMessage(Shipped("TRK9")),
        )
    }

    @Test fun `delivered message includes who signed`() {
        assertEquals("Delivered, signed by Sam.", statusMessage(Delivered("Sam")))
    }
}
