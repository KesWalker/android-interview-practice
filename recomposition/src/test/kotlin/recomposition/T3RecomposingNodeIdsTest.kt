package recomposition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: collect the ids of every node in the tree that recomposes.
class T3RecomposingNodeIdsTest {
    @Test fun `only nodes whose own reads overlap the changed keys are included`() {
        val tree = Node(
            id = "root",
            readsKeys = setOf("count"),
            children = listOf(
                Node("colorChild", setOf("color")),
                Node("countChild", setOf("count"))
            )
        )
        assertEquals(setOf("root", "countChild"), recomposingNodeIds(tree, setOf("count")))
    }

    @Test fun `a matching grandchild deep in the tree is still found`() {
        val tree = Node(
            id = "root",
            readsKeys = setOf("theme"),
            children = listOf(
                Node(
                    id = "mid",
                    readsKeys = setOf("theme"),
                    children = listOf(Node("leaf", setOf("count")))
                )
            )
        )
        assertEquals(setOf("leaf"), recomposingNodeIds(tree, setOf("count")))
    }

    @Test fun `no matching reads anywhere returns an empty set`() {
        val tree = Node("root", setOf("a"), listOf(Node("child", setOf("b"))))
        assertEquals(emptySet<String>(), recomposingNodeIds(tree, setOf("z")))
    }
}
