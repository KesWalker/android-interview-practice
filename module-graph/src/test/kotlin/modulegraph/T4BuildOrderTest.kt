package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a valid, deterministic topological build order.
class T4BuildOrderTest {
    @Test fun `a straight chain builds dependencies before dependents`() {
        val graph = mapOf(
            "a" to Module("a", Layer.APP, listOf(Dependency("b", DependencyType.IMPLEMENTATION))),
            "b" to Module("b", Layer.FEATURE, listOf(Dependency("c", DependencyType.API))),
            "c" to Module("c", Layer.CORE, emptyList())
        )
        assertEquals(listOf("c", "b", "a"), buildOrder(graph))
    }

    @Test fun `a diamond dependency is built once, before both paths that need it`() {
        val graph = mapOf(
            "a" to Module(
                "a", Layer.APP, listOf(
                    Dependency("b", DependencyType.IMPLEMENTATION),
                    Dependency("c", DependencyType.IMPLEMENTATION)
                )
            ),
            "b" to Module("b", Layer.FEATURE, listOf(Dependency("d", DependencyType.API))),
            "c" to Module("c", Layer.FEATURE, listOf(Dependency("d", DependencyType.API))),
            "d" to Module("d", Layer.CORE, emptyList())
        )
        assertEquals(listOf("d", "b", "c", "a"), buildOrder(graph))
    }
}
