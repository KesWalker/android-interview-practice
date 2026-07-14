package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: resolve a multi-level graph by constructor injection.
class T1ConstructorInjectionTest {
    @Test fun `a binding with no dependencies is built directly`() {
        val loggerKey = Key("Logger")
        val graph: Graph = mapOf(loggerKey to Binding(loggerKey) { "logger" })

        assertEquals("logger", resolve(graph, loggerKey))
    }

    @Test fun `dependencies are resolved recursively and passed in order`() {
        val loggerKey = Key("Logger")
        val repoKey = Key("Repo")
        val serviceKey = Key("Service")
        val graph: Graph = mapOf(
            loggerKey to Binding(loggerKey) { "logger" },
            repoKey to Binding(repoKey, listOf(loggerKey)) { args -> "repo(${args[0]})" },
            serviceKey to Binding(serviceKey, listOf(repoKey, loggerKey)) { args ->
                "service(${args[0]}, ${args[1]})"
            }
        )

        assertEquals("service(repo(logger), logger)", resolve(graph, serviceKey))
    }
}
