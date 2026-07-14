package broadcast

/**
 * Broadcast/provider routing practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :broadcast-router:test
 */

// ---- Content-URI matching -------------------------------------------------

/**
 * One entry a ContentProvider would register with addURI(authority, path, code).
 * `path` is segments joined by '/', with no leading or trailing slash, e.g.
 * the segments "users", "#", "posts", "star" joined together. A "#" segment
 * matches one all-digit segment, a "star" segment matches any single
 * segment, anything else must match literally.
 */
data class UriPattern(val authority: String, val path: String, val code: Int)

/** A URI being matched against the registered patterns, same path format as above. */
data class SimpleUri(val authority: String, val path: String)

/** Returned by matchUri when nothing matches. */
const val NO_MATCH = -1

// TODO(t1): T1SegmentMatchesTest
// Decide whether one path segment from a pattern matches one path segment
// from an actual URI. "#" matches a segment that is non-empty and every
// character is a digit. "*" matches any non-empty segment, digits or not.
// Anything else must match `actualSegment` exactly.
fun segmentMatches(patternSegment: String, actualSegment: String): Boolean {
    TODO("t1: '#' matches all-digit segments, '*' matches anything, else exact match")
}

// TODO(t2): T2MatchUriTest
// Find the first pattern (in list order) whose authority equals the URI's
// authority and whose path has the same number of segments as the URI's
// path, with every segment matching per segmentMatches. Return its code, or
// NO_MATCH if nothing matches.
fun matchUri(patterns: List<UriPattern>, uri: SimpleUri): Int {
    TODO("t2: return the code of the first pattern whose authority and path segments match")
}

// ---- Broadcast intent-filter matching -------------------------------------

/** An intent-filter as a receiver would declare it in code or the manifest. */
data class IntentFilterSpec(
    val actions: Set<String>,
    val categories: Set<String> = emptySet(),
    val dataScheme: String? = null
)

/** A broadcast being sent. */
data class BroadcastIntent(
    val action: String,
    val categories: Set<String> = emptySet(),
    val dataScheme: String? = null
)

/** Where a receiver was registered: statically in the manifest, or at runtime. */
enum class Registration { MANIFEST, DYNAMIC }

/** A registered broadcast receiver. Higher priority is delivered first in an ordered broadcast. */
data class Receiver(
    val id: String,
    val filter: IntentFilterSpec,
    val registration: Registration = Registration.DYNAMIC,
    val priority: Int = 0
)

/**
 * The small set of actions this exercise treats as still allowed to reach a
 * manifest-registered receiver via an implicit broadcast. Everything else
 * needs a dynamically registered receiver, which is why long-lived
 * background work that waits on an implicit broadcast has to register its
 * receiver in code, not the manifest.
 */
val EXEMPT_IMPLICIT_ACTIONS = setOf(
    "android.intent.action.BOOT_COMPLETED",
    "android.intent.action.LOCALE_CHANGED"
)

// TODO(t3): T3FilterMatchesTest
// Decide whether `intent` matches `filter`. The intent's action must be one
// of filter.actions. Every category on the intent must also be present in
// filter.categories (the filter may declare extra categories the intent
// doesn't ask for, that's fine). If filter.dataScheme is non-null, the
// intent's dataScheme must equal it; if filter.dataScheme is null, the
// intent's dataScheme must be null too.
fun filterMatches(filter: IntentFilterSpec, intent: BroadcastIntent): Boolean {
    TODO("t3: action must be declared, intent categories must be a subset, data scheme must line up")
}

// TODO(t4): T4ImplicitBroadcastAllowedTest
// Decide whether `receiver` is even eligible to receive `intent`, before
// filter matching is considered. A DYNAMIC (context-registered) receiver is
// always eligible. A MANIFEST receiver is only eligible when the intent's
// action is in EXEMPT_IMPLICIT_ACTIONS, this is the "implicit broadcasts to
// manifest-declared receivers are restricted" rule.
fun implicitBroadcastAllowed(receiver: Receiver, intent: BroadcastIntent): Boolean {
    TODO("t4: DYNAMIC is always eligible, MANIFEST only for EXEMPT_IMPLICIT_ACTIONS")
}

// TODO(t5): T5MatchingReceiversTest
// Return the ids, in the original list order, of every receiver that both
// passes implicitBroadcastAllowed and whose filter matches the intent per
// filterMatches.
fun matchingReceivers(receivers: List<Receiver>, intent: BroadcastIntent): List<String> {
    TODO("t5: filter receivers by implicitBroadcastAllowed and filterMatches, keep list order")
}

// TODO(t6): T6OrderedDispatchTest
// Same filtering as matchingReceivers, but for an ordered broadcast: return
// the ids sorted by priority, highest first. Receivers with equal priority
// keep their original relative order.
fun orderedDispatch(receivers: List<Receiver>, intent: BroadcastIntent): List<String> {
    TODO("t6: filter like matchingReceivers, then sort by priority descending, stable")
}

// TODO(t7): T7DispatchWithAbortTest
// Same priority ordering as orderedDispatch, but simulating abortBroadcast():
// walk the ordered, matching receivers in priority order, and stop after
// (and including) the first one whose id is in `abortAt`. If no matching
// receiver's id is in `abortAt`, every matching receiver gets it, same as
// orderedDispatch.
fun dispatchWithAbort(receivers: List<Receiver>, intent: BroadcastIntent, abortAt: Set<String>): List<String> {
    TODO("t7: deliver in priority order, stop right after a receiver whose id is in abortAt")
}
