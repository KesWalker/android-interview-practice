package fragmentlife

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: which callback can legally fire next, given the two intertwined
// state machines.
class T5LegalNextEventTest {
    @Test fun `attach is the only legal first event`() {
        assertTrue(legalNextEvent(FragmentLifecycleState.DETACHED, false, FragmentEvent.ON_ATTACH))
        assertFalse(legalNextEvent(FragmentLifecycleState.DETACHED, false, FragmentEvent.ON_CREATE))
    }

    @Test fun `resume is illegal before the view exists`() {
        assertFalse(legalNextEvent(FragmentLifecycleState.CREATED, false, FragmentEvent.ON_RESUME))
        assertTrue(legalNextEvent(FragmentLifecycleState.CREATED, false, FragmentEvent.ON_CREATE_VIEW))
    }

    @Test fun `destroy is illegal while the view is still alive`() {
        assertFalse(legalNextEvent(FragmentLifecycleState.CREATED, true, FragmentEvent.ON_DESTROY))
        assertTrue(legalNextEvent(FragmentLifecycleState.CREATED, true, FragmentEvent.ON_DESTROY_VIEW))
    }

    @Test fun `detach is only legal once destroyed`() {
        assertFalse(legalNextEvent(FragmentLifecycleState.CREATED, false, FragmentEvent.ON_DETACH))
        assertTrue(legalNextEvent(FragmentLifecycleState.DESTROYED, false, FragmentEvent.ON_DETACH))
    }
}
