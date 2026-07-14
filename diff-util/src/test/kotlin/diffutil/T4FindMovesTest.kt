package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: an item whose relative order breaks is a Move.
class T4FindMovesTest {
    @Test fun `a single swap flags one item as moved`() {
        // old order: [0, 1, 2]; new order: [1, 0, 2] (item at old index 0 slid back).
        val matches = mapOf(0 to 1, 1 to 0, 2 to 2)
        assertEquals(listOf(DiffOp.Move(oldIndex = 0, newIndex = 1)), findMoves(matches))
    }

    @Test fun `moving the first item to the end flags only that item`() {
        // old order: [0, 1, 2]; new order: [1, 2, 0].
        val matches = mapOf(0 to 1, 1 to 2, 2 to 0)
        assertEquals(listOf(DiffOp.Move(oldIndex = 0, newIndex = 2)), findMoves(matches))
    }

    @Test fun `unchanged order produces no moves`() {
        val matches = mapOf(0 to 0, 1 to 1, 2 to 2)
        assertEquals(emptyList<DiffOp.Move>(), findMoves(matches))
    }
}
