package fragmentlife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: the fragment-level state machine, coarser than the view lifecycle.
class T1FragmentLifecycleStateTest {
    @Test fun `starts detached before any events`() {
        assertEquals(FragmentLifecycleState.DETACHED, fragmentLifecycleState(emptyList()))
    }

    @Test fun `attach then create reaches created`() {
        val events = listOf(FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE)
        assertEquals(FragmentLifecycleState.CREATED, fragmentLifecycleState(events))
    }

    @Test fun `view level events do not change the fragment state`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME
        )
        assertEquals(FragmentLifecycleState.CREATED, fragmentLifecycleState(events))
    }

    @Test fun `full teardown ends detached again`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME,
            FragmentEvent.ON_PAUSE, FragmentEvent.ON_STOP,
            FragmentEvent.ON_DESTROY_VIEW, FragmentEvent.ON_DESTROY, FragmentEvent.ON_DETACH
        )
        assertEquals(FragmentLifecycleState.DETACHED, fragmentLifecycleState(events))
    }
}
