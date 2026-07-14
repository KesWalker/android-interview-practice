package semantics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 3: merged vs unmerged semantics.
class T3MergedTextTest {
    @Test fun `unmerged node reports only its own text`() {
        val node = SemanticsNode(
            text = "Solo",
            mergeDescendants = false,
            children = listOf(SemanticsNode(text = "ignored"))
        )

        assertEquals("Solo", mergedText(node))
    }

    @Test fun `unmerged node with no text reports null`() {
        val node = SemanticsNode(mergeDescendants = false)

        assertNull(mergedText(node))
    }

    @Test fun `merging node joins its own and its descendants' text`() {
        val node = SemanticsNode(
            text = "Header",
            mergeDescendants = true,
            children = listOf(
                SemanticsNode(text = "Sub", children = listOf(SemanticsNode(contentDescription = "icon"))),
                SemanticsNode(text = "Trailing")
            )
        )

        assertEquals("Header, Sub, Trailing", mergedText(node))
    }

    @Test fun `merging node with no text anywhere in its subtree reports null`() {
        val node = SemanticsNode(
            mergeDescendants = true,
            children = listOf(SemanticsNode(contentDescription = "icon"))
        )

        assertNull(mergedText(node))
    }
}
