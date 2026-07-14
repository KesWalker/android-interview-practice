package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: a new item whose id wasn't in the old list is an Insert.
class T3FindInsertionsTest {
    @Test fun `flags a new item whose id is missing from old`() {
        val old = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"), Card(3, "Pay rent"))
        val new = listOf(Card(2, "Walk dog"), Card(3, "Pay rent"), Card(4, "Call mom"))
        assertEquals(listOf(DiffOp.Insert(2)), findInsertions(old, new, Card::id))
    }

    @Test fun `no insertions when every new id already existed`() {
        val old = listOf(Card(1, "A"), Card(2, "B"))
        val new = listOf(Card(2, "B"), Card(1, "A"))
        assertEquals(emptyList<DiffOp.Insert>(), findInsertions(old, new, Card::id))
    }
}
