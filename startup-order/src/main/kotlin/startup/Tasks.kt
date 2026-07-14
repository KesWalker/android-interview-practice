package startup

/**
 * App Startup practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :startup-order:test
 */

/** One node in the startup dependency graph. */
data class Initializer(
    val id: String,
    val dependencies: List<String> = emptyList(),
    val costMs: Long = 0,
    val lazy: Boolean = false
)

/** How the app came back: what already existed before this launch. */
enum class StartupState { COLD, WARM, HOT }

/** A single timestamped marker pulled from a startup trace. */
data class TraceEvent(val name: String, val timestampMs: Long)

/** The eager/deferred split App Startup picked for one cold start. */
data class StartupPlan(
    val eager: List<String>,
    val deferred: List<String>,
    val costSavedMs: Long
)

// TODO(t1): T1ClassifyStartupTest
// Classify a launch from what already exists. HOT: the process is alive and
// the activity already exists (just brought back to the foreground). WARM:
// the process is alive but the activity was destroyed (must be recreated).
// COLD: the process isn't alive at all (must create the process, the
// Application and the activity from scratch).
fun classifyStartup(processAlive: Boolean, activityExists: Boolean): StartupState {
    TODO("t1: COLD if no process, HOT if process+activity, WARM if process but no activity")
}

// TODO(t2): T2TimeToInitialDisplayTest
// Given an unordered list of trace events, compute time-to-initial-display:
// the gap in milliseconds between the "processStart" event and the
// "firstFrame" event. Ignore every other event name in the list.
fun timeToInitialDisplay(events: List<TraceEvent>): Long {
    TODO("t2: firstFrame timestamp minus processStart timestamp")
}

// TODO(t3): T3TopologicalOrderTest
// Return the initializers' ids in dependency order: walk `initializers` in
// the order given, and for each one, first walk its own dependencies (in
// the order listed on that initializer), recursively, before adding the
// initializer's own id to the result. Adding an id that's already in the
// result is a no-op, so an id several others depend on still appears
// exactly once, at the earliest position some path to it reaches.
fun topologicalOrder(initializers: List<Initializer>): List<String> {
    TODO("t3: dependency-first order, each id exactly once, ties by visiting order")
}

// TODO(t4): T4FindCycleTest
// Look for a cycle in the dependency graph. If the graph is acyclic, return
// an empty list. If there's a cycle, return its ids in dependency-edge
// order (id, the thing it depends on, the thing that depends on, ...),
// rotated so the list starts at whichever member of the cycle sorts first
// alphabetically. Ids outside the cycle are never included.
fun findCycle(initializers: List<Initializer>): List<String> {
    TODO("t4: DFS for a back-edge, then rotate the cycle to start at its smallest id")
}

// TODO(t5): T5InitializeAllTest
// Simulate initializing every initializer in `initializers`, where
// initializing one first initializes all of its dependencies. Several
// initializers can share the same dependency; that shared one must still
// only be initialized once. Return a map from id to how many times its
// initialization actually ran.
fun initializeAll(initializers: List<Initializer>): Map<String, Int> {
    TODO("t5: memoize so a shared dependency's count stays at 1")
}

// TODO(t6): T6PlanStartupTest
// Split initializers into what runs eagerly at cold start and what's
// deferred. A `lazy` initializer never runs at startup, only later on
// demand, so it's excluded from `eager` and listed in `deferred` instead
// (sorted alphabetically). `eager` holds the remaining (non-lazy) ids in
// dependency order, same rule as topologicalOrder (the exercise never hands
// you an eager initializer that depends on a lazy one). `costSavedMs` is
// the sum of costMs across every deferred initializer: the cold-start time
// this plan avoided paying up front.
fun planStartup(initializers: List<Initializer>): StartupPlan {
    TODO("t6: eager = non-lazy in dependency order, deferred = lazy ids sorted, costSavedMs = their costMs sum")
}
