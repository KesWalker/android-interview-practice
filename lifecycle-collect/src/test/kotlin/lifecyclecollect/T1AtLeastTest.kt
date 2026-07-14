package lifecyclecollect

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: ordering CREATED -> STARTED -> RESUMED.
class T1AtLeastTest {
    @Test fun `a state is at least itself`() {
        assertTrue(atLeast(LifecycleState.STARTED, LifecycleState.STARTED))
    }

    @Test fun `a further-along state is at least an earlier one`() {
        assertTrue(atLeast(LifecycleState.RESUMED, LifecycleState.STARTED))
        assertTrue(atLeast(LifecycleState.STARTED, LifecycleState.CREATED))
    }

    @Test fun `an earlier state is not at least a further-along one`() {
        assertFalse(atLeast(LifecycleState.CREATED, LifecycleState.STARTED))
        assertFalse(atLeast(LifecycleState.STARTED, LifecycleState.RESUMED))
    }
}
