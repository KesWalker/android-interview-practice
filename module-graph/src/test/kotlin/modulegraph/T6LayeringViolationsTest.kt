package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: a core module must never reach up into a feature or app module.
class T6LayeringViolationsTest {
    @Test fun `a core module depending on a feature module is flagged`() {
        val graph = mapOf(
            "core" to Module("core", Layer.CORE, emptyList()),
            "feature" to Module("feature", Layer.FEATURE, listOf(Dependency("core", DependencyType.API))),
            "coreThatCheats" to Module(
                "coreThatCheats", Layer.CORE, listOf(Dependency("feature", DependencyType.IMPLEMENTATION))
            ),
            "app" to Module("app", Layer.APP, listOf(Dependency("feature", DependencyType.IMPLEMENTATION)))
        )
        assertEquals(listOf("coreThatCheats" to "feature"), findLayeringViolations(graph))
    }

    @Test fun `a graph where every dependency points downward has no violations`() {
        val graph = mapOf(
            "core" to Module("core", Layer.CORE, emptyList()),
            "feature" to Module("feature", Layer.FEATURE, listOf(Dependency("core", DependencyType.API))),
            "app" to Module("app", Layer.APP, listOf(Dependency("feature", DependencyType.IMPLEMENTATION)))
        )
        assertEquals(emptyList<Pair<String, String>>(), findLayeringViolations(graph))
    }
}
