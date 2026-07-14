package taskgraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: the full incremental-build plan, everything else composed together.
class T6IncrementalBuildPlanTest {
    private val compile = TaskDef(name = "compile", inputs = listOf("Main.kt"), outputs = listOf("Main.class"))
    private val test = TaskDef(name = "test", dependsOn = listOf("compile"), inputs = listOf("Main.class"), outputs = listOf("test-report"))
    private val lint = TaskDef(name = "lint", dependsOn = listOf("compile"), inputs = listOf("Main.class"), outputs = listOf("lint-report"))
    private val assemble = TaskDef(
        name = "assemble",
        dependsOn = listOf("test", "lint"),
        inputs = listOf("test-report", "lint-report"),
        outputs = listOf("app.apk")
    )
    private val tasks = listOf(compile, test, lint, assemble)

    private val stableFingerprints = mapOf(
        "Main.kt" to "h-main",
        "Main.class" to "h-class",
        "test-report" to "h-test",
        "lint-report" to "h-lint",
        "app.apk" to "h-apk"
    )

    @Test fun `nothing changed means nothing to run`() {
        val plan = incrementalBuildPlan(tasks, stableFingerprints, stableFingerprints)

        assertEquals(emptyList<String>(), plan)
    }

    @Test fun `a changed source file reruns everything downstream, in order`() {
        val current = stableFingerprints + ("Main.kt" to "h-main-edited")

        val plan = incrementalBuildPlan(tasks, stableFingerprints, current)

        assertEquals(listOf("compile", "test", "lint", "assemble"), plan)
    }

    @Test fun `a tampered leaf output only reruns its own branch, in order`() {
        val current = stableFingerprints + ("lint-report" to "h-lint-tampered")

        val plan = incrementalBuildPlan(tasks, stableFingerprints, current)

        assertEquals(listOf("lint", "assemble"), plan)
    }
}
