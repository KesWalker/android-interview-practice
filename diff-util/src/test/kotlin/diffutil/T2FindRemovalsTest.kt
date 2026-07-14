package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: an old item whose id is gone from the new list is a Remove.
class T2FindRemovalsTest {
    @Test fun `flags an old item whose id is missing from new`() {
        val old = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"), Card(3, "Pay rent"))
        val new = listOf(Card(2, "Walk dog"), Card(3, "Pay rent"), Card(4, "Call mom"))
        assertEquals(listOf(DiffOp.Remove(0)), findRemovals(old, new, Card::id))
    }

    @Test fun `no removals when every old id survives`() {
        val old = listOf(Card(1, "A"), Card(2, "B"))
        val new = listOf(Card(2, "B"), Card(1, "A"))
        assertEquals(emptyList<DiffOp.Remove>(), findRemovals(old, new, Card::id))
    }
}
