package statelogic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: savedStateHandle.getStateFlow(key, default) as an observable API.
class FakeSavedStateHandleTest {
    @Test fun `starts at the given default when nothing is stored yet`() {
        val handle = FakeSavedStateHandle()

        val flow = handle.getStateFlow("query", "")

        assertEquals("", flow.value)
    }

    @Test fun `a later set is reflected by a StateFlow obtained before the set`() {
        val handle = FakeSavedStateHandle()
        val flow = handle.getStateFlow("query", "")

        handle.set("query", "kotlin")

        assertEquals("kotlin", flow.value)
    }

    @Test fun `a second getStateFlow call returns the current value, not the original default`() {
        val handle = FakeSavedStateHandle()
        handle.getStateFlow("query", "")
        handle.set("query", "kotlin")

        val second = handle.getStateFlow("query", "unused default")

        assertEquals("kotlin", second.value)
    }
}
