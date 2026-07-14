package doze

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: only EXPEDITED jobs skip the maintenance-window wait while dozing.
class T2CanRunImmediatelyTest {
    @Test fun `not dozing always runs now regardless of priority`() {
        assertTrue(canRunImmediately(dozing = false, priority = JobPriority.NORMAL))
        assertTrue(canRunImmediately(dozing = false, priority = JobPriority.EXPEDITED))
    }

    @Test fun `dozing blocks a normal job`() {
        assertFalse(canRunImmediately(dozing = true, priority = JobPriority.NORMAL))
    }

    @Test fun `dozing still lets an expedited job through`() {
        assertTrue(canRunImmediately(dozing = true, priority = JobPriority.EXPEDITED))
    }
}
