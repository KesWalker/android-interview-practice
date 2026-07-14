package diffutil

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: areContentsTheSame, plus a payload for a partial rebind.
class T5FindChangesTest {
    private val contentsTheSame: (Card, Card) -> Boolean = { a, b -> a == b }
    private val payloadFor: (Card, Card) -> Any? = { a, b -> if (a.done != b.done) "done" else null }

    @Test fun `emits a Change with a payload when content differs`() {
        val old = listOf(Card(1, "Buy milk", done = false), Card(2, "Walk dog", done = false))
        val new = listOf(Card(1, "Buy milk", done = true), Card(2, "Walk dog", done = false))
        val matches = mapOf(0 to 0, 1 to 1)

        assertEquals(
            listOf(DiffOp.Change(oldIndex = 0, newIndex = 0, payload = "done")),
            findChanges(matches, old, new, contentsTheSame, payloadFor)
        )
    }

    @Test fun `no changes when matched content is identical`() {
        val old = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"))
        val new = listOf(Card(1, "Buy milk"), Card(2, "Walk dog"))
        val matches = mapOf(0 to 0, 1 to 1)

        assertEquals(emptyList<DiffOp.Change>(), findChanges(matches, old, new, contentsTheSame, payloadFor))
    }
}
