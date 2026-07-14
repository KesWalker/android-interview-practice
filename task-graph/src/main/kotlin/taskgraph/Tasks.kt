package taskgraph

/**
 * Gradle Task-Graph Engine practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :task-graph:test
 *
 * TaskDef and TaskScript are already complete, you're only filling in the
 * functions that turn a set of task declarations into an actual build.
 */

/** One task as Gradle would declare it: what it needs before it and what it touches. */
data class TaskDef(
    val name: String,
    val dependsOn: List<String> = emptyList(),
    val inputs: List<String> = emptyList(),
    val outputs: List<String> = emptyList()
)

// TODO(t1): T1TopologicalOrderTest
// Order `tasks` so every task comes after everything it dependsOn (assume
// the graph has no cycles, that's a separate concern). When more than one
// task is ready at the same point, prefer whichever is reached first by
// visiting `tasks` in order and following each task's dependsOn in order,
// depth-first.
fun topologicalOrder(tasks: List<TaskDef>): List<String> {
    TODO("t1: depth-first post-order visit of tasks and their dependsOn")
}

// TODO(t2): T2RequireAcyclicTest
// Walk the same dependsOn graph, but this time watch for a task that depends
// on itself transitively. If task visiting hits a name that's already on the
// current path, throw IllegalStateException("cycle detected: A -> B -> C ->
// A"), listing the path from where the cycle starts back around to the
// repeated task, joined with " -> ". Return normally when the graph has no
// cycle.
fun requireAcyclic(tasks: List<TaskDef>) {
    TODO("t2: throw IllegalStateException naming the cycle, or return normally")
}

// TODO(t3): T3IsUpToDateTest
// Decide whether `task` can be skipped. Look at every path in its inputs
// plus its outputs: the task is UP-TO-DATE (return true) only when every one
// of those paths was recorded in `lastRunFingerprints` and its recorded
// fingerprint equals the one in `currentFingerprints`. A path missing from
// either map, or a fingerprint that differs, means the task must re-run.
fun isUpToDate(
    task: TaskDef,
    lastRunFingerprints: Map<String, String>,
    currentFingerprints: Map<String, String>
): Boolean {
    TODO("t3: true only when every input/output fingerprint is unchanged since last run")
}

// TODO(t4): T4TasksToRunTest
// Propagate invalidation transitively. `directlyDirty` names tasks that must
// re-run because their own fingerprints changed. Any task that (transitively)
// dependsOn a dirty task consumes output that's about to change, so it must
// re-run too, even if its own fingerprints still look unchanged. Return the
// full set of task names that must run: `directlyDirty` plus every task that
// depends on one of them, directly or through another task.
fun tasksToRun(tasks: List<TaskDef>, directlyDirty: Set<String>): Set<String> {
    TODO("t4: directlyDirty plus every task that transitively depends on one of them")
}

/** A build script's two phases, split apart so the perf sin is visible. */
data class TaskScript(
    val name: String,
    val onConfigure: () -> Unit,
    val onExecute: () -> Unit
)

// TODO(t5): T5RunBuildTest
// Model Gradle's two-phase build. Configuration phase: run every script's
// onConfigure, unconditionally, for every script, regardless of which tasks
// were requested, this is why work in a configuration block is the classic
// performance sin, it runs even for a build that never touches that task.
// Execution phase: after all configuration has run, run onExecute only for
// the scripts whose name is in `requestedTasks`.
fun runBuild(scripts: List<TaskScript>, requestedTasks: Set<String>) {
    TODO("t5: run every onConfigure, then only the requested tasks' onExecute")
}

// TODO(t6): T6IncrementalBuildPlanTest
// Put it all together into the plan a real incremental build would execute.
// Find every task that's directly dirty (isUpToDate returns false against
// its own inputs and outputs), propagate that transitively with tasksToRun,
// then return topologicalOrder filtered down to just the tasks that ended up
// in that dirty set, in dependency order. A build where nothing changed
// returns an empty list.
fun incrementalBuildPlan(
    tasks: List<TaskDef>,
    lastRunFingerprints: Map<String, String>,
    currentFingerprints: Map<String, String>
): List<String> {
    TODO("t6: dirty set via isUpToDate + tasksToRun, ordered by topologicalOrder")
}
