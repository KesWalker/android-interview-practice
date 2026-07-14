package broadcast

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: ordered broadcasts deliver highest priority first.
class T6OrderedDispatchTest {
    @Test fun `delivers highest priority first`() {
        val receivers = listOf(
            Receiver("low", IntentFilterSpec(setOf("A")), priority = 0),
            Receiver("high", IntentFilterSpec(setOf("A")), priority = 10),
            Receiver("mid", IntentFilterSpec(setOf("A")), priority = 5)
        )
        assertEquals(listOf("high", "mid", "low"), orderedDispatch(receivers, BroadcastIntent("A")))
    }

    @Test fun `equal priority keeps the original relative order`() {
        val receivers = listOf(
            Receiver("a", IntentFilterSpec(setOf("A")), priority = 5),
            Receiver("b", IntentFilterSpec(setOf("A")), priority = 5),
            Receiver("c", IntentFilterSpec(setOf("A")), priority = 10)
        )
        assertEquals(listOf("c", "a", "b"), orderedDispatch(receivers, BroadcastIntent("A")))
    }

    @Test fun `non-matching receivers are left out of the order entirely`() {
        val receivers = listOf(
            Receiver("yes", IntentFilterSpec(setOf("A")), priority = 1),
            Receiver("no", IntentFilterSpec(setOf("B")), priority = 99)
        )
        assertEquals(listOf("yes"), orderedDispatch(receivers, BroadcastIntent("A")))
    }
}
