package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: crossinline lets a lambda run inside a separate Runnable object
// while still being inlined at timedRun's own call site.
class TimedRunTest {
    @Test fun `logs start and end around the action`() {
        val log = mutableListOf<String>()
        timedRun("load", log) {}
        assertEquals(listOf("start:load", "end:load"), log)
    }

    @Test fun `action actually runs, wrapped inside the Runnable`() {
        val log = mutableListOf<String>()
        var ran = false
        timedRun("load", log) { ran = true }
        assertTrue(ran)
    }
}
