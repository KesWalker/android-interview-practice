package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: api leaks past the first hop, implementation does not.
class T2CompileClasspathTest {
    // a -> b (api), a -> e (implementation)
    // b -> c (api), b -> d (implementation)
    private val graph = mapOf(
        "a" to Module(
            "a", Layer.FEATURE, listOf(
                Dependency("b", DependencyType.API),
                Dependency("e", DependencyType.IMPLEMENTATION)
            )
        ),
        "b" to Module(
            "b", Layer.FEATURE, listOf(
                Dependency("c", DependencyType.API),
                Dependency("d", DependencyType.IMPLEMENTATION)
            )
        ),
        "c" to Module("c", Layer.CORE, emptyList()),
        "d" to Module("d", Layer.CORE, emptyList()),
        "e" to Module("e", Layer.CORE, emptyList())
    )

    @Test fun `direct deps are always visible, api leaks past the first hop, implementation does not`() {
        assertEquals(setOf("b", "e", "c"), compileClasspath(graph, "a"))
    }

    @Test fun `a leaf module with no deps has an empty classpath`() {
        assertEquals(emptySet<String>(), compileClasspath(graph, "c"))
    }

    @Test fun `b's own classpath includes its direct implementation dep`() {
        assertEquals(setOf("c", "d"), compileClasspath(graph, "b"))
    }
}
