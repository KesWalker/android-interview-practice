package broadcast

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: capstone, an ordered broadcast where a receiver calls abortBroadcast().
class T7DispatchWithAbortTest {
    private val receivers = listOf(
        Receiver("high", IntentFilterSpec(setOf("A")), priority = 10),
        Receiver("mid", IntentFilterSpec(setOf("A")), priority = 5),
        Receiver("low", IntentFilterSpec(setOf("A")), priority = 0)
    )

    @Test fun `with nothing aborting, every matching receiver gets it in priority order`() {
        assertEquals(
            listOf("high", "mid", "low"),
            dispatchWithAbort(receivers, BroadcastIntent("A"), abortAt = emptySet())
        )
    }

    @Test fun `aborting mid-stream stops delivery right after that receiver`() {
        assertEquals(
            listOf("high", "mid"),
            dispatchWithAbort(receivers, BroadcastIntent("A"), abortAt = setOf("mid"))
        )
    }

    @Test fun `aborting at the last receiver changes nothing observable`() {
        assertEquals(
            listOf("high", "mid", "low"),
            dispatchWithAbort(receivers, BroadcastIntent("A"), abortAt = setOf("low"))
        )
    }
}
