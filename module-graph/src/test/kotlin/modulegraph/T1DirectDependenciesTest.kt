package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: filter a module's dependencies down to one type.
class T1DirectDependenciesTest {
    private val module = Module(
        name = "feature",
        layer = Layer.FEATURE,
        dependencies = listOf(
            Dependency("core", DependencyType.API),
            Dependency("util", DependencyType.API),
            Dependency("logging", DependencyType.IMPLEMENTATION)
        )
    )

    @Test fun `returns only the api dependencies`() {
        assertEquals(setOf("core", "util"), directDependenciesOfType(module, DependencyType.API))
    }

    @Test fun `returns only the implementation dependencies`() {
        assertEquals(setOf("logging"), directDependenciesOfType(module, DependencyType.IMPLEMENTATION))
    }

    @Test fun `a module with no dependencies of that type returns empty`() {
        val leaf = Module("core", Layer.CORE, emptyList())
        assertEquals(emptySet<String>(), directDependenciesOfType(leaf, DependencyType.IMPLEMENTATION))
    }
}
