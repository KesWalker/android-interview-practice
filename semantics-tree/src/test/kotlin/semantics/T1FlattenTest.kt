package semantics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: pre-order depth-first traversal of the whole tree.
class T1FlattenTest {
    @Test fun `flattens a tree in pre-order, root first`() {
        val tree = SemanticsNode(
            testTag = "root",
            children = listOf(
                SemanticsNode(testTag = "a", children = listOf(SemanticsNode(testTag = "a1"))),
                SemanticsNode(testTag = "b")
            )
        )

        val tags = flatten(tree).map { it.testTag }

        assertEquals(listOf("root", "a", "a1", "b"), tags)
    }

    @Test fun `a leaf node flattens to just itself`() {
        val leaf = SemanticsNode(testTag = "solo")

        assertEquals(listOf(leaf), flatten(leaf))
    }
}
