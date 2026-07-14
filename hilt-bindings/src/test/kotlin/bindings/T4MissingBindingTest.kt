package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: an unsatisfied binding fails loudly, naming the exact key.
class T4MissingBindingTest {
    private val loggerKey = Key("Logger")
    private val repoKey = Key("Repo")
    private val serviceKey = Key("Service")

    @Test fun `a fully satisfied graph resolves normally`() {
        val graph: Graph = mapOf(
            loggerKey to Binding(loggerKey) { "logger" },
            repoKey to Binding(repoKey, listOf(loggerKey)) { args -> "repo(${args[0]})" },
            serviceKey to Binding(serviceKey, listOf(repoKey)) { args -> "service(${args[0]})" }
        )

        assertEquals("service(repo(logger))", resolveOrThrow(graph, serviceKey))
    }

    @Test fun `a missing leaf binding throws naming that key`() {
        val graph: Graph = mapOf(
            serviceKey to Binding(serviceKey, listOf(repoKey)) { args -> "service(${args[0]})" }
        )

        val ex = assertThrows(MissingBindingException::class.java) {
            resolveOrThrow(graph, serviceKey)
        }
        assertTrue(ex.message!!.contains("Repo"))
    }

    @Test fun `a binding missing several levels down is still named exactly`() {
        val graph: Graph = mapOf(
            serviceKey to Binding(serviceKey, listOf(repoKey)) { args -> "service(${args[0]})" },
            repoKey to Binding(repoKey, listOf(loggerKey)) { args -> "repo(${args[0]})" }
        )

        val ex = assertThrows(MissingBindingException::class.java) {
            resolveOrThrow(graph, serviceKey)
        }
        assertTrue(ex.message!!.contains("Logger"))
    }
}
