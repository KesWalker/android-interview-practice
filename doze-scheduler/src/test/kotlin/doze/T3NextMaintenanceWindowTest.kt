package doze

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: round an arrival time up to the next maintenance window boundary.
class T3NextMaintenanceWindowTest {
    private val interval = 900_000L

    @Test fun `arrival on a boundary stays on that boundary`() {
        assertEquals(0L, nextMaintenanceWindow(0L, interval))
        assertEquals(interval, nextMaintenanceWindow(interval, interval))
    }

    @Test fun `arrival mid window rounds up to the next boundary`() {
        assertEquals(interval, nextMaintenanceWindow(100_000L, interval))
        assertEquals(interval, nextMaintenanceWindow(interval - 1, interval))
    }

    @Test fun `arrival just past a boundary rounds up to the following one`() {
        assertEquals(2 * interval, nextMaintenanceWindow(interval + 1, interval))
    }
}
