package fragmentlife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: the actual interview point, fragment-owner observers leak across
// a view recreation, viewLifecycleOwner observers do not.
class T4ActiveObserverCountTest {
    @Test fun `one view creation leaves one observer under either owner`() {
        val events = listOf(FragmentEvent.ON_CREATE_VIEW)
        assertEquals(1, activeObserverCount(events, ObserverOwner.FRAGMENT_LIFECYCLE))
        assertEquals(1, activeObserverCount(events, ObserverOwner.VIEW_LIFECYCLE))
    }

    @Test fun `a back stack cycle leaks a second observer on the fragment owner`() {
        val events = listOf(
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW
        )
        assertEquals(2, activeObserverCount(events, ObserverOwner.FRAGMENT_LIFECYCLE))
    }

    @Test fun `the same back stack cycle stays clean on the view lifecycle owner`() {
        val events = listOf(
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW
        )
        assertEquals(1, activeObserverCount(events, ObserverOwner.VIEW_LIFECYCLE))
    }

    @Test fun `destroying the fragment always clears every observer`() {
        val events = listOf(
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY
        )
        assertEquals(0, activeObserverCount(events, ObserverOwner.FRAGMENT_LIFECYCLE))
    }
}
