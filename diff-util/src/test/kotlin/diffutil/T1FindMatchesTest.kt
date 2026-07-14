package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: areItemsTheSame, identity matching by id.
class T1FindMatchesTest {
    @Test fun `maps matching ids across a removal and an insertion`() {
        val old = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"), Card(3, "Pay rent"))
        val new = listOf(Card(2, "Walk dog"), Card(3, "Pay rent"), Card(4, "Call mom"))
        assertEquals(mapOf(0 to 1, 1 to 2), findMatches(old, new, Card::id))
    }

    @Test fun `identical lists match every position to itself`() {
        val list = listOf(Card(1, "A"), Card(2, "B"))
        assertEquals(mapOf(0 to 0, 1 to 1), findMatches(list, list, Card::id))
    }

    @Test fun `no shared ids means no matches`() {
        val old = listOf(Card(1, "A"))
        val new = listOf(Card(2, "B"))
        assertEquals(emptyMap<Int, Int>(), findMatches(old, new, Card::id))
    }
}
