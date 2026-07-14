package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: pick APPLICATION vs ACTIVITY for a use site.
class T1PickContextTest {
    @Test fun `a long-lived singleton field gets the application context`() {
        assertEquals(ContextKind.APPLICATION, pickContext(ContextUseSite.LONG_LIVED_SINGLETON))
    }

    @Test fun `view inflation and starting an activity both need the activity context`() {
        assertEquals(ContextKind.ACTIVITY, pickContext(ContextUseSite.VIEW_INFLATION))
        assertEquals(ContextKind.ACTIVITY, pickContext(ContextUseSite.START_ACTIVITY))
    }

    @Test fun `registering a receiver and showing a toast both get the application context`() {
        assertEquals(ContextKind.APPLICATION, pickContext(ContextUseSite.REGISTER_BROADCAST_RECEIVER))
        assertEquals(ContextKind.APPLICATION, pickContext(ContextUseSite.SHOW_TOAST))
    }
}
