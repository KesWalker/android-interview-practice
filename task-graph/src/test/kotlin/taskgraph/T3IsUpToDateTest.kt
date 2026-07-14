package taskgraph

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: UP-TO-DATE checking against recorded fingerprints.
class T3IsUpToDateTest {
    private val compile = TaskDef(
        name = "compile",
        inputs = listOf("Main.kt"),
        outputs = listOf("Main.class")
    )

    @Test fun `up to date when every fingerprint is unchanged`() {
        val fingerprints = mapOf("Main.kt" to "h1", "Main.class" to "h2")

        assertTrue(isUpToDate(compile, fingerprints, fingerprints))
    }

    @Test fun `not up to date when an input's fingerprint changed`() {
        val last = mapOf("Main.kt" to "h1", "Main.class" to "h2")
        val current = mapOf("Main.kt" to "h1-edited", "Main.class" to "h2")

        assertFalse(isUpToDate(compile, last, current))
    }

    @Test fun `not up to date the first time, before any run was recorded`() {
        val current = mapOf("Main.kt" to "h1", "Main.class" to "h2")

        assertFalse(isUpToDate(compile, emptyMap(), current))
    }

    @Test fun `not up to date when a recorded output is missing now`() {
        val last = mapOf("Main.kt" to "h1", "Main.class" to "h2")
        val current = mapOf("Main.kt" to "h1")

        assertFalse(isUpToDate(compile, last, current))
    }
}
