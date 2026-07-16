package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: crossinline lets a lambda run inside a separate Runnable object
// while still being inlined at timedRun's own call site. You declare timedRun
// from scratch in Tasks.kt; the test requires it to actually be inline, and
// the compiler is what walks you into needing crossinline.
class T5TimedRunTest {
    private fun timedRun(label: String, log: MutableList<String>, action: () -> Unit) {
        val m = findDeclaration("timedRun", 3)
            ?: notDeclaredYet(
                "t5: timedRun",
                "Declare an inline function timedRun(label: String, log: MutableList<String>, " +
                    "action: () -> Unit) that logs start:<label>, runs the action from inside " +
                    "a Runnable it creates and runs, then logs end:<label>.",
            )
        m.requireInline("t5: timedRun")
        m.call(label, log, action)
    }

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
