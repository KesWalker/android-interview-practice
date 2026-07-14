package recomposition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: the priciest phase a changed-state set invalidates for a node.
class T6InvalidationForTest {
    @Test fun `a color-only read invalidates just draw`() {
        val node = RenderNode("box", setOf(StateRead.COLOR))
        assertEquals(Invalidation.DRAW, invalidationFor(node, setOf(StateRead.COLOR)))
    }

    @Test fun `composition beats draw when both are overlapping reads`() {
        val node = RenderNode("box", setOf(StateRead.COLOR, StateRead.TEXT_CONTENT))
        assertEquals(
            Invalidation.COMPOSITION,
            invalidationFor(node, setOf(StateRead.COLOR, StateRead.TEXT_CONTENT))
        )
    }

    @Test fun `only the overlapping read counts, unread changes are ignored`() {
        val node = RenderNode("box", setOf(StateRead.SIZE_PX, StateRead.COLOR))
        assertEquals(Invalidation.DRAW, invalidationFor(node, setOf(StateRead.COLOR)))
    }

    @Test fun `no overlap at all means no invalidation`() {
        val node = RenderNode("box", setOf(StateRead.TEXT_CONTENT))
        assertEquals(Invalidation.NONE, invalidationFor(node, setOf(StateRead.COLOR)))
    }
}
