package taskgraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: transitive invalidation, a dirty task's dependents are dirty too.
class T4TasksToRunTest {
    private val compile = TaskDef(name = "compile")
    private val test = TaskDef(name = "test", dependsOn = listOf("compile"))
    private val lint = TaskDef(name = "lint", dependsOn = listOf("compile"))
    private val assemble = TaskDef(name = "assemble", dependsOn = listOf("test", "lint"))
    private val tasks = listOf(compile, test, lint, assemble)

    @Test fun `a dirty root invalidates every task downstream of it`() {
        val result = tasksToRun(tasks, setOf("compile"))

        assertEquals(setOf("compile", "test", "lint", "assemble"), result)
    }

    @Test fun `a dirty leaf only invalidates its own downstream branch`() {
        val result = tasksToRun(tasks, setOf("lint"))

        assertEquals(setOf("lint", "assemble"), result)
    }

    @Test fun `nothing dirty means nothing to run`() {
        assertEquals(emptySet<String>(), tasksToRun(tasks, emptySet()))
    }
}
