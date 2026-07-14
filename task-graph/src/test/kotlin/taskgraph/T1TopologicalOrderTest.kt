package taskgraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: dependencies must be ordered before whatever depends on them.
class T1TopologicalOrderTest {
    @Test fun `orders every task after everything it dependsOn`() {
        val compile = TaskDef(name = "compile")
        val test = TaskDef(name = "test", dependsOn = listOf("compile"))
        val lint = TaskDef(name = "lint", dependsOn = listOf("compile"))
        val assemble = TaskDef(name = "assemble", dependsOn = listOf("test", "lint"))

        val order = topologicalOrder(listOf(assemble, test, lint, compile))

        assertEquals(listOf("compile", "test", "lint", "assemble"), order)
    }

    @Test fun `independent tasks keep the input order`() {
        val a = TaskDef(name = "a")
        val b = TaskDef(name = "b")
        val c = TaskDef(name = "c")

        assertEquals(listOf("a", "b", "c"), topologicalOrder(listOf(a, b, c)))
    }
}
