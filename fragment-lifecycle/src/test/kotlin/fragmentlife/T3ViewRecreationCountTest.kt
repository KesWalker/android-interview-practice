package fragmentlife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: how many times has this fragment's view been (re)created.
class T3ViewRecreationCountTest {
    @Test fun `no events means zero`() {
        assertEquals(0, viewRecreationCount(emptyList()))
    }

    @Test fun `a normal single-view fragment counts one`() {
        val events = listOf(
            FragmentEvent.ON_ATTACH, FragmentEvent.ON_CREATE,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_VIEW_CREATED,
            FragmentEvent.ON_START, FragmentEvent.ON_RESUME
        )
        assertEquals(1, viewRecreationCount(events))
    }

    @Test fun `three trips through the back stack count three`() {
        val events = listOf(
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW, FragmentEvent.ON_DESTROY_VIEW,
            FragmentEvent.ON_CREATE_VIEW
        )
        assertEquals(3, viewRecreationCount(events))
    }
}
