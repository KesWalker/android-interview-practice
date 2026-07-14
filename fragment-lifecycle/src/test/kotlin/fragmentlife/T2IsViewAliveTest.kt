package fragmentlife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: the separate view-lifecycle window, onCreateView..onDestroyView.
class T2IsViewAliveTest {
    @Test fun `no events means no view`() {
        assertFalse(isViewAlive(emptyList()))
    }

    @Test fun `alive after create view`() {
        assertTrue(isViewAlive(listOf(FragmentEvent.ON_CREATE_VIEW)))
    }

    @Test fun `dead after destroy view`() {
        val events = listOf(FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW)
        assertFalse(isViewAlive(events))
    }

    @Test fun `alive again after a second create view following a backstack pop`() {
        val events = listOf(
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW
        )
        assertTrue(isViewAlive(events))
    }
}
