package manifestmerge

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: an application-scoped holder of an activity context leaks.
class T2WouldLeakTest {
    @Test fun `application-scoped holder of an activity context leaks`() {
        val holder = ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.ACTIVITY)
        assertTrue(wouldLeak(holder))
    }

    @Test fun `application-scoped holder of the application context is safe`() {
        val holder = ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.APPLICATION)
        assertFalse(wouldLeak(holder))
    }

    @Test fun `activity-scoped holder of an activity context is safe`() {
        val holder = ContextHolder(HolderLifetime.ACTIVITY_SCOPED, ContextKind.ACTIVITY)
        assertFalse(wouldLeak(holder))
    }

    @Test fun `view-scoped holder of an activity context is safe`() {
        val holder = ContextHolder(HolderLifetime.VIEW_SCOPED, ContextKind.ACTIVITY)
        assertFalse(wouldLeak(holder))
    }
}
