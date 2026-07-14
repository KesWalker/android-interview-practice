package doze

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: Doze priority AND declared constraints both have to clear.
class T5JobMayRunNowTest {
    @Test fun `not dozing with no constraints runs now`() {
        assertTrue(
            jobMayRunNow(
                dozing = false,
                priority = JobPriority.NORMAL,
                constraints = JobConstraints(),
                device = DeviceConditions(charging = false, unmetered = false)
            )
        )
    }

    @Test fun `dozing blocks a normal job even with constraints met`() {
        assertFalse(
            jobMayRunNow(
                dozing = true,
                priority = JobPriority.NORMAL,
                constraints = JobConstraints(),
                device = DeviceConditions(charging = true, unmetered = true)
            )
        )
    }

    @Test fun `expedited bypasses doze but not its own unmet constraint`() {
        assertFalse(
            jobMayRunNow(
                dozing = true,
                priority = JobPriority.EXPEDITED,
                constraints = JobConstraints(requiresCharging = true),
                device = DeviceConditions(charging = false, unmetered = true)
            )
        )
    }

    @Test fun `expedited with its constraint satisfied runs now`() {
        assertTrue(
            jobMayRunNow(
                dozing = true,
                priority = JobPriority.EXPEDITED,
                constraints = JobConstraints(requiresCharging = true),
                device = DeviceConditions(charging = true, unmetered = false)
            )
        )
    }
}
