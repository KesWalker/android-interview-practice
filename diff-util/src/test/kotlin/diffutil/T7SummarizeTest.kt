package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: tally a diff's op kinds, then use that to see why a stable id matters.
class T7SummarizeTest {
    @Test fun `counts each kind of op`() {
        val ops = listOf(
            DiffOp.Insert(0),
            DiffOp.Remove(1),
            DiffOp.Move(oldIndex = 0, newIndex = 2),
            DiffOp.Change(oldIndex = 1, newIndex = 1, payload = null),
            DiffOp.Change(oldIndex = 2, newIndex = 2, payload = "label")
        )
        assertEquals(DiffSummary(inserts = 1, removes = 1, moves = 1, changes = 2), summarize(ops))
    }

    @Test fun `empty ops list summarizes to all zeros`() {
        assertEquals(DiffSummary(0, 0, 0, 0), summarize(emptyList()))
    }

    @Test fun `an unstable key turns a move into a remove and an insert`() {
        val old = listOf(Card(1, "A"), Card(2, "B"), Card(3, "C"))
        val new = listOf(Card(2, "B"), Card(1, "A"), Card(3, "C"))
        val contentsTheSame: (Card, Card) -> Boolean = { a, b -> a == b }

        // A stable id (the real `id` field) recognizes the reorder as one Move.
        val stableSummary = summarize(computeDiff(old, new, idOf = Card::id, contentsTheSame = contentsTheSame))
        assertEquals(DiffSummary(inserts = 0, removes = 0, moves = 1, changes = 0), stableSummary)

        // An unstable key (never equal to itself between old and new, e.g. an
        // object reference recreated on every fetch) can't recognize the same
        // card twice: every old entry looks removed and every new entry looks
        // inserted, instead of one item having moved.
        val unstableIdOf: (Card) -> Any = { Any() }
        val unstableSummary = summarize(computeDiff(old, new, idOf = unstableIdOf, contentsTheSame = contentsTheSame))
        assertEquals(DiffSummary(inserts = 3, removes = 3, moves = 0, changes = 0), unstableSummary)
    }
}
