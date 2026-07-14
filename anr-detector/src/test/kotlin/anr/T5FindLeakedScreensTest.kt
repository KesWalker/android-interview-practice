package anr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a long-lived object retaining a destroyed screen.
class T5FindLeakedScreensTest {
    @Test fun `a destroyed screen still reachable from a gc root is flagged as leaked`() {
        val heap = mapOf(
            "staticCache" to HeapObject("staticCache", listOf("listener")),
            "listener" to HeapObject("listener", listOf("oldActivity")),
            "oldActivity" to HeapObject("oldActivity", listOf("view")),
            "view" to HeapObject("view"),
            "freshActivity" to HeapObject("freshActivity")
        )
        val leaked = findLeakedScreens(
            heap,
            gcRoots = setOf("staticCache"),
            destroyedScreenIds = setOf("oldActivity", "freshActivity")
        )
        assertEquals(setOf("oldActivity"), leaked)
    }

    @Test fun `a destroyed screen with no path from any root is not a leak`() {
        val heap = mapOf(
            "staticCache" to HeapObject("staticCache"),
            "oldActivity" to HeapObject("oldActivity")
        )
        val leaked = findLeakedScreens(
            heap,
            gcRoots = setOf("staticCache"),
            destroyedScreenIds = setOf("oldActivity")
        )
        assertEquals(emptySet<String>(), leaked)
    }
}
