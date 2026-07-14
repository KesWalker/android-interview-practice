package anr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: the ANR itself, plus the close-call warning band.
class T3DetectAnrTest {
    @Test fun `input arriving during idle time is not blocked`() {
        val items = listOf(WorkItem("layout", Category.LAYOUT, 0, 1000))
        assertEquals(AnrResult(0, AnrStatus.OK), detectAnr(items, inputDispatchAtMs = 2000))
    }

    @Test fun `blocked past the close call band but under the threshold`() {
        val items = listOf(WorkItem("parseJson", Category.NETWORK, 0, 4500))
        assertEquals(AnrResult(4500, AnrStatus.CLOSE_CALL), detectAnr(items, inputDispatchAtMs = 0))
    }

    @Test fun `blocked past the 5s threshold by a single item is an ANR`() {
        val items = listOf(WorkItem("bindViews", Category.LAYOUT, 0, 6000))
        assertEquals(AnrResult(5500, AnrStatus.ANR), detectAnr(items, inputDispatchAtMs = 500))
    }

    @Test fun `back-to-back items chain into a single blocked window`() {
        val items = listOf(
            WorkItem("diskRead", Category.DISK_IO, 0, 3000),
            WorkItem("parseJson", Category.NETWORK, 3000, 3000)
        )
        assertEquals(AnrResult(5000, AnrStatus.ANR), detectAnr(items, inputDispatchAtMs = 1000))
    }
}
