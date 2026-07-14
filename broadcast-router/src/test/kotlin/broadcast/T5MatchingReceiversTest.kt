package broadcast

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: combine filter matching with the implicit-broadcast restriction.
class T5MatchingReceiversTest {
    @Test fun `only the action-matching dynamic receiver gets a plain custom action`() {
        val receivers = listOf(
            Receiver("r1", IntentFilterSpec(setOf("A")), Registration.DYNAMIC),
            Receiver("r2", IntentFilterSpec(setOf("A")), Registration.MANIFEST),
            Receiver("r3", IntentFilterSpec(setOf("B")), Registration.DYNAMIC)
        )
        assertEquals(listOf("r1"), matchingReceivers(receivers, BroadcastIntent("A")))
    }

    @Test fun `an exempt action reaches both manifest and dynamic receivers, in order`() {
        val receivers = listOf(
            Receiver("m1", IntentFilterSpec(setOf("android.intent.action.BOOT_COMPLETED")), Registration.MANIFEST),
            Receiver("d1", IntentFilterSpec(setOf("android.intent.action.BOOT_COMPLETED")), Registration.DYNAMIC)
        )
        assertEquals(
            listOf("m1", "d1"),
            matchingReceivers(receivers, BroadcastIntent("android.intent.action.BOOT_COMPLETED"))
        )
    }

    @Test fun `no matches returns an empty list`() {
        val receivers = listOf(Receiver("r1", IntentFilterSpec(setOf("A")), Registration.DYNAMIC))
        assertEquals(emptyList<String>(), matchingReceivers(receivers, BroadcastIntent("Z")))
    }
}
