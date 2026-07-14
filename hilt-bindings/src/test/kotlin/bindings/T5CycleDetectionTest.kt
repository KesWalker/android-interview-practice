package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: a binding that (transitively) depends on itself is a build error.
class T5CycleDetectionTest {
    @Test fun `an acyclic graph still resolves normally`() {
        val loggerKey = Key("Logger")
        val repoKey = Key("Repo")
        val graph: Graph = mapOf(
            loggerKey to Binding(loggerKey) { "logger" },
            repoKey to Binding(repoKey, listOf(loggerKey)) { args -> "repo(${args[0]})" }
        )

        assertEquals("repo(logger)", resolveDetectingCycles(graph, repoKey))
    }

    @Test fun `a direct two-node cycle is detected`() {
        val aKey = Key("A")
        val bKey = Key("B")
        val graph: Graph = mapOf(
            aKey to Binding(aKey, listOf(bKey)) { args -> "a(${args[0]})" },
            bKey to Binding(bKey, listOf(aKey)) { args -> "b(${args[0]})" }
        )

        val ex = assertThrows(DependencyCycleException::class.java) {
            resolveDetectingCycles(graph, aKey)
        }
        assertEquals("A -> B -> A", ex.message)
    }

    @Test fun `a three-node cycle is detected`() {
        val aKey = Key("A")
        val bKey = Key("B")
        val cKey = Key("C")
        val graph: Graph = mapOf(
            aKey to Binding(aKey, listOf(bKey)) { args -> "a(${args[0]})" },
            bKey to Binding(bKey, listOf(cKey)) { args -> "b(${args[0]})" },
            cKey to Binding(cKey, listOf(aKey)) { args -> "c(${args[0]})" }
        )

        val ex = assertThrows(DependencyCycleException::class.java) {
            resolveDetectingCycles(graph, aKey)
        }
        assertEquals("A -> B -> C -> A", ex.message)
    }
}
