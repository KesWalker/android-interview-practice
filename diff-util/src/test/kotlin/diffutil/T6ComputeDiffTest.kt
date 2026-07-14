package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: combine matches, removals, moves, insertions and changes into one ordered diff.
class T6ComputeDiffTest {
    private val contentsTheSame: (Card, Card) -> Boolean = { a, b -> a == b }
    private val payloadFor: (Card, Card) -> Any? = { a, b -> if (a.done != b.done) "done" else null }

    @Test fun `combines a removal, an insertion and a change with no moves`() {
        val old = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"), Card(3, "Pay rent"))
        val new = listOf(Card(2, "Walk dog", done = true), Card(4, "Call mom"), Card(3, "Pay rent"))

        val result = computeDiff(old, new, Card::id, contentsTheSame, payloadFor)

        assertEquals(
            listOf(
                DiffOp.Remove(0),
                DiffOp.Insert(1),
                DiffOp.Change(oldIndex = 1, newIndex = 0, payload = "done")
            ),
            result
        )
    }

    @Test fun `a pure reorder with no content change produces just a Move`() {
        val old = listOf(Card(1, "A"), Card(2, "B"), Card(3, "C"))
        val new = listOf(Card(2, "B"), Card(1, "A"), Card(3, "C"))

        val result = computeDiff(old, new, Card::id, contentsTheSame, payloadFor)

        assertEquals(listOf(DiffOp.Move(oldIndex = 0, newIndex = 1)), result)
    }
}
