package taskgraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: configuration phase runs for every task, execution phase doesn't.
class T5RunBuildTest {
    @Test fun `every task configures, only requested tasks execute`() {
        val log = mutableListOf<String>()
        val scripts = listOf(
            TaskScript(
                name = "compile",
                onConfigure = { log.add("configure:compile") },
                onExecute = { log.add("execute:compile") }
            ),
            TaskScript(
                name = "lint",
                onConfigure = { log.add("configure:lint") },
                onExecute = { log.add("execute:lint") }
            )
        )

        runBuild(scripts, setOf("compile"))

        assertEquals(
            listOf("configure:compile", "configure:lint", "execute:compile"),
            log
        )
    }

    @Test fun `an empty request still configures every script but executes nothing`() {
        val log = mutableListOf<String>()
        val scripts = listOf(
            TaskScript(name = "compile", onConfigure = { log.add("configure:compile") }, onExecute = { log.add("execute:compile") })
        )

        runBuild(scripts, emptySet())

        assertEquals(listOf("configure:compile"), log)
    }
}
