package anr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 1: find the single item to blame.
class T1WorstBlockingCallTest {
    @Test fun `returns the item with the greatest duration`() {
        val items = listOf(
            WorkItem("measure", Category.LAYOUT, 0, 200),
            WorkItem("parseJson", Category.NETWORK, 200, 4500),
            WorkItem("draw", Category.LAYOUT, 4700, 100)
        )
        assertEquals(items[1], worstBlockingCall(items))
    }

    @Test fun `a tie for the max duration returns the first one in the list`() {
        val items = listOf(
            WorkItem("a", Category.LAYOUT, 0, 500),
            WorkItem("b", Category.NETWORK, 500, 500)
        )
        assertEquals(items[0], worstBlockingCall(items))
    }

    @Test fun `an empty list throws`() {
        assertThrows(IllegalArgumentException::class.java) { worstBlockingCall(emptyList()) }
    }
}
