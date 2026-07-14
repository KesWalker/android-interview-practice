package taskgraph

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: cycle detection with a clear, named error.
class T2RequireAcyclicTest {
    @Test fun `a graph with no cycle passes without throwing`() {
        val compile = TaskDef(name = "compile")
        val test = TaskDef(name = "test", dependsOn = listOf("compile"))

        assertDoesNotThrow { requireAcyclic(listOf(test, compile)) }
    }

    @Test fun `a cycle throws naming the exact path`() {
        val a = TaskDef(name = "A", dependsOn = listOf("B"))
        val b = TaskDef(name = "B", dependsOn = listOf("C"))
        val c = TaskDef(name = "C", dependsOn = listOf("A"))

        val ex = assertThrows(IllegalStateException::class.java) {
            requireAcyclic(listOf(a, b, c))
        }

        assertEquals("cycle detected: A -> B -> C -> A", ex.message)
    }
}
