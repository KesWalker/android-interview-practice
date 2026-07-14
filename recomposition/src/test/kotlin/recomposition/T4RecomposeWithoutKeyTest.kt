package recomposition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: without key(), remembered state stays glued to slot position.
class T4RecomposeWithoutKeyTest {
    @Test fun `reordering keeps each position's old state under the new item`() {
        val before = listOf(Slot("A", 1), Slot("B", 2), Slot("C", 3))
        val newOrder = listOf("C", "A", "B")
        assertEquals(
            listOf(Slot("C", 1), Slot("A", 2), Slot("B", 3)),
            recomposeWithoutKey(before, newOrder)
        )
    }

    @Test fun `an item appended past the old size starts at state 0`() {
        val before = listOf(Slot("A", 1))
        val newOrder = listOf("A", "B")
        assertEquals(
            listOf(Slot("A", 1), Slot("B", 0)),
            recomposeWithoutKey(before, newOrder)
        )
    }

    @Test fun `an identity reorder leaves everything unchanged`() {
        val before = listOf(Slot("A", 1), Slot("B", 2))
        assertEquals(before, recomposeWithoutKey(before, listOf("A", "B")))
    }
}
