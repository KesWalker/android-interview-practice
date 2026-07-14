package fragmentlife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: capstone, run legalNextEvent across a whole callback sequence.
class T6ValidateSequenceTest {
    @Test fun `a normal single-view lifecycle is fully legal`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME,
            FragmentEvent.ON_PAUSE, FragmentEvent.ON_STOP,
            FragmentEvent.ON_DESTROY_VIEW, FragmentEvent.ON_DESTROY, FragmentEvent.ON_DETACH
        )
        assertEquals(-1, validateSequence(events))
    }

    @Test fun `a back stack round trip is fully legal`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME,
            FragmentEvent.ON_PAUSE, FragmentEvent.ON_STOP, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME,
            FragmentEvent.ON_PAUSE, FragmentEvent.ON_STOP, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_DESTROY, FragmentEvent.ON_DETACH
        )
        assertEquals(-1, validateSequence(events))
    }

    @Test fun `resume before the view exists is caught at that index`() {
        val events = listOf(FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE, FragmentEvent.ON_RESUME)
        assertEquals(2, validateSequence(events))
    }

    @Test fun `creating the view twice without destroying it is caught`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_CREATE_VIEW
        )
        assertEquals(3, validateSequence(events))
    }
}
