package anr

/**
 * ANR & Memory practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :anr-detector:test
 */

/** A rough bucket for what kind of work a main-thread item was doing. */
enum class Category { LAYOUT, NETWORK, DISK_IO, ANIMATION, MISC }

/** One piece of work that ran on the main thread. */
data class WorkItem(
    val name: String,
    val category: Category,
    val startMs: Long,
    val durationMs: Long
) {
    val endMs: Long get() = startMs + durationMs
}

/** The outcome of checking one input event against the main-thread trace. */
enum class AnrStatus { OK, CLOSE_CALL, ANR }

data class AnrResult(val blockedMs: Long, val status: AnrStatus)

// TODO(t1): T1WorstBlockingCallTest
// Return the item with the greatest durationMs. If several share the max
// duration, return the first one in `items`. Throw IllegalArgumentException
// if `items` is empty.
fun worstBlockingCall(items: List<WorkItem>): WorkItem {
    TODO("t1: the single longest-running item, first one wins a tie")
}

// TODO(t2): T2CategoryTotalsTest
// Sum durationMs per category. A category with no items in `items` is
// absent from the result, not present with a zero.
fun totalTimeByCategory(items: List<WorkItem>): Map<Category, Long> {
    TODO("t2: group by category and sum durationMs")
}

// TODO(t3): T3DetectAnrTest
// `items` is a chronological, non-overlapping trace of main-thread work
// (gaps between items are idle time). An input event arrives at
// `inputDispatchAtMs`. Walk forward from that point: while the current time
// falls inside some item's [startMs, endMs), the thread is busy, so jump to
// that item's endMs and keep checking, chaining through back-to-back items.
// Once the current time lands in a gap, the thread is free and that's when
// the input gets handled. blockedMs is how long that took. Android's real
// ANR threshold is 5000ms:
//   - blockedMs >= 5000  -> ANR
//   - blockedMs >= 4000  -> CLOSE_CALL (a warning band before the cliff)
//   - otherwise          -> OK
fun detectAnr(items: List<WorkItem>, inputDispatchAtMs: Long): AnrResult {
    TODO("t3: chain forward through busy items to find how long input was blocked")
}

/** One object on the heap and what it directly references. */
data class HeapObject(val id: String, val references: List<String> = emptyList())

// TODO(t4): T4ReachableFromTest
// Return every object id reachable from `rootId` by following `references`
// transitively, including `rootId` itself. Objects with no path from the
// root are not garbage, they're just not part of this answer, they simply
// aren't included. Must not loop forever on a reference cycle.
fun reachableFrom(heap: Map<String, HeapObject>, rootId: String): Set<String> {
    TODO("t4: traverse references from rootId, guarding against cycles")
}

// TODO(t5): T5FindLeakedScreensTest
// A destroyed screen should have nothing left pointing at it, so the
// garbage collector can reclaim it. Given the heap, the set of GC roots
// (long-lived objects like statics and singletons), and the ids of screens
// that have been destroyed, return the destroyed screen ids that are still
// reachable from at least one GC root, i.e. actually leaked.
fun findLeakedScreens(
    heap: Map<String, HeapObject>,
    gcRoots: Set<String>,
    destroyedScreenIds: Set<String>
): Set<String> {
    TODO("t5: union what every gc root reaches, then intersect with the destroyed screens")
}
