package reachability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

// Task 3: keepclassmembers only protects members of a class that's
// otherwise reachable, it never makes the class itself a root.
class T3ReachableWithKeepRulesTest {
    private val mainAndLogger = mapOf(
        "MainActivity" to ProgramClass("MainActivity", listOf("onCreate"), references = setOf("Logger")),
        "Logger" to ProgramClass("Logger", listOf("log", "format"), references = emptySet())
    )

    @Test fun `keepclassmembers on an unreferenced class saves nothing`() {
        val classes = mainAndLogger + ("OrphanUtil" to ProgramClass("OrphanUtil", listOf("helper")))
        val result = reachableWithKeepRules(
            classes,
            keepClassRoots = setOf("MainActivity"),
            keepClassMembers = mapOf("OrphanUtil" to setOf("helper"))
        )
        assertEquals(setOf("MainActivity", "Logger"), result)
        assertFalse(result.contains("OrphanUtil"))
        assertFalse(result.contains("OrphanUtil#helper"))
    }

    @Test fun `wildcard keepclassmembers protects every declared member once the class is reached`() {
        val result = reachableWithKeepRules(
            mainAndLogger,
            keepClassRoots = setOf("MainActivity"),
            keepClassMembers = mapOf("Logger" to setOf("*"))
        )
        assertEquals(setOf("MainActivity", "Logger", "Logger#log", "Logger#format"), result)
    }

    @Test fun `named keepclassmembers protects only the named member`() {
        val result = reachableWithKeepRules(
            mainAndLogger,
            keepClassRoots = setOf("MainActivity"),
            keepClassMembers = mapOf("Logger" to setOf("log"))
        )
        assertEquals(setOf("MainActivity", "Logger", "Logger#log"), result)
        assertFalse(result.contains("Logger#format"))
    }
}
