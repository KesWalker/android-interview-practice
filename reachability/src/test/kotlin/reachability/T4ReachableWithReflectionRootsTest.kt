package reachability

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: the reflection problem, and the keep-rule fix.
class T4ReachableWithReflectionRootsTest {
    private val classes = mapOf(
        "MainActivity" to setOf("Presenter"),
        "Presenter" to emptySet(),
        "PluginLoadedByReflection" to setOf("PluginHelper"),
        "PluginHelper" to emptySet()
    )

    @Test fun `plain reachability wrongly reports the reflection-only class unreachable`() {
        val naive = reachableClasses(classes, setOf("MainActivity"))
        assertFalse(naive.contains("PluginLoadedByReflection"))
    }

    @Test fun `treating reflectively accessed classes as roots keeps them and their references`() {
        val fixed = reachableWithReflectionRoots(
            classes,
            roots = setOf("MainActivity"),
            reflectivelyAccessedClasses = setOf("PluginLoadedByReflection")
        )
        assertTrue(fixed.containsAll(setOf("MainActivity", "Presenter", "PluginLoadedByReflection", "PluginHelper")))
    }
}
