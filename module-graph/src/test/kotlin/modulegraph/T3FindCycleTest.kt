package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 3: detect a dependency cycle with a clear, deterministic error.
class T3FindCycleTest {
    @Test fun `an acyclic graph reports no cycle`() {
        val graph = mapOf(
            "a" to Module("a", Layer.APP, listOf(Dependency("b", DependencyType.IMPLEMENTATION))),
            "b" to Module("b", Layer.FEATURE, listOf(Dependency("c", DependencyType.API))),
            "c" to Module("c", Layer.CORE, emptyList())
        )
        assertNull(findCycle(graph))
    }

    @Test fun `a cyclic graph reports the cycle path`() {
        val graph = mapOf(
            "a" to Module("a", Layer.FEATURE, listOf(Dependency("b", DependencyType.IMPLEMENTATION))),
            "b" to Module("b", Layer.FEATURE, listOf(Dependency("c", DependencyType.IMPLEMENTATION))),
            "c" to Module("c", Layer.FEATURE, listOf(Dependency("a", DependencyType.IMPLEMENTATION)))
        )
        assertEquals(listOf("a", "b", "c", "a"), findCycle(graph))
    }
}
