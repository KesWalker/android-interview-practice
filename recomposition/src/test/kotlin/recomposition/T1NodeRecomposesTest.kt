package recomposition

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: a node recomposes iff its reads overlap the changed keys.
class T1NodeRecomposesTest {
    @Test fun `a node whose reads overlap the changed keys recomposes`() {
        val node = Node("counter", setOf("a", "b"))
        assertTrue(nodeRecomposes(node, setOf("b", "c")))
    }

    @Test fun `a node whose reads don't overlap the changed keys skips`() {
        val node = Node("label", setOf("a"))
        assertFalse(nodeRecomposes(node, setOf("b")))
    }

    @Test fun `a node with no reads at all never recomposes`() {
        val node = Node("decoration", emptySet())
        assertFalse(nodeRecomposes(node, setOf("a")))
    }
}
