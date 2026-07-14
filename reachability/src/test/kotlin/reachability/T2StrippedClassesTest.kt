package reachability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: sweep phase, everything declared but never reached.
class T2StrippedClassesTest {
    private val classes = mapOf(
        "MainActivity" to setOf("Presenter"),
        "Presenter" to emptySet(),
        "DeadCode" to setOf("MoreDeadCode"),
        "MoreDeadCode" to emptySet()
    )

    @Test fun `unreferenced classes are stripped`() {
        assertEquals(
            setOf("DeadCode", "MoreDeadCode"),
            strippedClasses(classes, setOf("MainActivity"))
        )
    }

    @Test fun `nothing is stripped when every class is reachable`() {
        assertEquals(
            emptySet<String>(),
            strippedClasses(classes, setOf("MainActivity", "DeadCode"))
        )
    }
}
