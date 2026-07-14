package recomposition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: with key(), remembered state follows the item's identity.
class T5RecomposeWithKeyTest {
    @Test fun `reordering moves each item's old state along with it`() {
        val before = listOf(Slot("A", 1), Slot("B", 2), Slot("C", 3))
        val newOrder = listOf("C", "A", "B")
        assertEquals(
            listOf(Slot("C", 3), Slot("A", 1), Slot("B", 2)),
            recomposeWithKey(before, newOrder)
        )
    }

    @Test fun `a key that wasn't present before starts fresh at 0`() {
        val before = listOf(Slot("A", 1))
        val newOrder = listOf("B", "A")
        assertEquals(
            listOf(Slot("B", 0), Slot("A", 1)),
            recomposeWithKey(before, newOrder)
        )
    }

    @Test fun `a removed item simply isn't in the result`() {
        val before = listOf(Slot("A", 1), Slot("B", 2), Slot("C", 3))
        val newOrder = listOf("C", "A")
        assertEquals(
            listOf(Slot("C", 3), Slot("A", 1)),
            recomposeWithKey(before, newOrder)
        )
    }
}
