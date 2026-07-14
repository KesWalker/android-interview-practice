package anr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: what a GC root retains.
class T4ReachableFromTest {
    @Test fun `follows references transitively, root included, unreachable objects excluded`() {
        val heap = mapOf(
            "a" to HeapObject("a", listOf("b", "c")),
            "b" to HeapObject("b", listOf("d")),
            "c" to HeapObject("c"),
            "d" to HeapObject("d"),
            "e" to HeapObject("e")
        )
        assertEquals(setOf("a", "b", "c", "d"), reachableFrom(heap, "a"))
    }

    @Test fun `a reference cycle does not loop forever`() {
        val heap = mapOf(
            "a" to HeapObject("a", listOf("b")),
            "b" to HeapObject("b", listOf("a"))
        )
        assertEquals(setOf("a", "b"), reachableFrom(heap, "a"))
    }
}
