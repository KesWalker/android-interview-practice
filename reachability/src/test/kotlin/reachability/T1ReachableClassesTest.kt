package reachability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: mark phase, transitive closure from the roots.
class T1ReachableClassesTest {
    private val classes = mapOf(
        "MainActivity" to setOf("Presenter"),
        "Presenter" to setOf("Repository"),
        "Repository" to setOf("NetworkClient"),
        "NetworkClient" to emptySet(),
        "DeadCode" to setOf("MoreDeadCode"),
        "MoreDeadCode" to emptySet()
    )

    @Test fun `transitive closure from a single root, dead code excluded`() {
        assertEquals(
            setOf("MainActivity", "Presenter", "Repository", "NetworkClient"),
            reachableClasses(classes, setOf("MainActivity"))
        )
    }

    @Test fun `class with no references is reachable alone`() {
        assertEquals(setOf("NetworkClient"), reachableClasses(classes, setOf("NetworkClient")))
    }

    @Test fun `multiple roots union their closures`() {
        assertEquals(
            setOf("DeadCode", "MoreDeadCode", "NetworkClient"),
            reachableClasses(classes, setOf("DeadCode", "NetworkClient"))
        )
    }
}
