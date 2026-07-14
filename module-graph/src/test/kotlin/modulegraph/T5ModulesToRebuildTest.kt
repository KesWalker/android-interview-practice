package modulegraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: rebuild blast radius, and how implementation shields consumers.
class T5ModulesToRebuildTest {
    // core (no deps)
    // util       -> core (api)
    // featureA   -> util (implementation)
    // featureB   -> util (api)
    // app        -> featureA (implementation)
    private val graph = mapOf(
        "core" to Module("core", Layer.CORE, emptyList()),
        "util" to Module("util", Layer.CORE, listOf(Dependency("core", DependencyType.API))),
        "featureA" to Module("featureA", Layer.FEATURE, listOf(Dependency("util", DependencyType.IMPLEMENTATION))),
        "featureB" to Module("featureB", Layer.FEATURE, listOf(Dependency("util", DependencyType.API))),
        "app" to Module("app", Layer.APP, listOf(Dependency("featureA", DependencyType.IMPLEMENTATION)))
    )

    @Test fun `changing core ripples through api edges but stops at an implementation edge`() {
        assertEquals(setOf("util", "featureA", "featureB"), modulesToRebuild(graph, "core"))
    }

    @Test fun `changing util reaches its direct consumers but not app, which is shielded`() {
        assertEquals(setOf("featureA", "featureB"), modulesToRebuild(graph, "util"))
    }
}
