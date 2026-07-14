package manifestmerge

/**
 * Context choice & manifest merging practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :manifest-merge:test
 */

// --- Context choice ---------------------------------------------------

/** A place in the app that needs a Context handed to it. */
enum class ContextUseSite {
    LONG_LIVED_SINGLETON,
    VIEW_INFLATION,
    START_ACTIVITY,
    REGISTER_BROADCAST_RECEIVER,
    SHOW_TOAST
}

/** Which flavor of Context a use site should be handed. */
enum class ContextKind { APPLICATION, ACTIVITY }

// TODO(t1): T1PickContextTest
// Pick the right Context flavor for each use site. A field on a long-lived
// singleton and a broadcast receiver registration both outlive any single
// screen, so they get APPLICATION, and so does showing a toast, which
// needs no theming and shouldn't hold a screen alive either. Inflating
// themed views and starting an activity both need ACTIVITY: one for the
// theme attributes, the other so the launch behaves like a normal
// same-task transition.
fun pickContext(useSite: ContextUseSite): ContextKind {
    TODO("t1: map each use site to APPLICATION or ACTIVITY")
}

/** How long a holder of a Context reference is expected to live. */
enum class HolderLifetime { APPLICATION_SCOPED, ACTIVITY_SCOPED, VIEW_SCOPED }

/** Something (a field, a singleton) that keeps a reference to a Context. */
data class ContextHolder(val lifetime: HolderLifetime, val heldContext: ContextKind)

// TODO(t2): T2WouldLeakTest
// A context reference leaks a screen when the thing holding it outlives
// that screen. That happens in exactly one combination here: a holder
// that is APPLICATION_SCOPED (survives every screen) while the Context it
// holds is an ACTIVITY context (tied to one screen). Every other
// combination is safe: an activity- or view-scoped holder dies with the
// screen it references, and an application-scoped holder holding the
// application Context never outlives what it holds.
fun wouldLeak(holder: ContextHolder): Boolean {
    TODO("t2: true only for APPLICATION_SCOPED holder + ACTIVITY context")
}

/** A named field, so a batch of holders can report back which ones leak. */
data class NamedContextHolder(val fieldName: String, val holder: ContextHolder)

// TODO(t3): T3LeakingFieldsTest
// Given a list of named holders (think: every static or companion-object
// field in a class that stores a Context), return the field names of the
// ones that leak, using the same rule as wouldLeak. Preserve input order.
fun leakingFields(holders: List<NamedContextHolder>): List<String> {
    TODO("t3: names of holders where lifetime is APPLICATION_SCOPED and the held context is ACTIVITY")
}

// --- Manifest merging ---------------------------------------------------

/** Where a manifest node came from; app declarations always outrank library ones. */
enum class ManifestSource { APP, LIBRARY }

/** A `tools:node` marker controlling how a same-keyed node gets merged. */
enum class NodeMarker { MERGE, REPLACE, REMOVE }

/**
 * One manifest node (an activity, a permission, etc), identified by [key]
 * (e.g. "activity:.MainActivity"), with its XML attributes and where it
 * came from.
 */
data class ManifestNode(
    val key: String,
    val attributes: Map<String, String>,
    val source: ManifestSource,
    val marker: NodeMarker = NodeMarker.MERGE
)

// TODO(t4): T4MergeAttributesTest
// Merge two attribute maps for the same node: start from libraryAttrs,
// then overlay appAttrs on top, so a key present in both takes the app's
// value, and keys unique to either side both survive in the result.
fun mergeAttributes(libraryAttrs: Map<String, String>, appAttrs: Map<String, String>): Map<String, String> {
    TODO("t4: union of both maps, app's value wins on shared keys")
}

// TODO(t5): T5ApplyNodeMarkerTest
// Resolve one same-keyed pair of nodes using the marker carried by
// appNode. REMOVE drops the node entirely (return null). REPLACE keeps
// appNode's own attributes untouched and discards libraryNode's. MERGE
// (the default) combines the two attribute maps the way mergeAttributes
// does, with the app's values winning on shared keys. A replaced or
// merged result's source is APP.
fun applyNodeMarker(libraryNode: ManifestNode, appNode: ManifestNode): ManifestNode? {
    TODO("t5: apply appNode.marker to combine libraryNode and appNode")
}

// TODO(t6): T6MergeManifestsTest
// Merge a full library manifest into a full app manifest. Walk
// libraryNodes in order: for each one, if appNodes has no node with the
// same key, keep the library node as-is; if it does, resolve the pair the
// way applyNodeMarker does (dropping it entirely on REMOVE). After that,
// append any app nodes whose key never appeared in libraryNodes, in their
// given order.
fun mergeManifests(libraryNodes: List<ManifestNode>, appNodes: List<ManifestNode>): List<ManifestNode> {
    TODO("t6: combine both manifests, app markers win, unmatched nodes pass through")
}

// TODO(t7): T7FindMergeConflictsTest
// Two libraries of equal priority can both contribute a node for the same
// key with no app node around to arbitrate between them. That's a
// genuine, unresolved merge conflict exactly when two LIBRARY nodes share
// the same key and the same attribute name but disagree on its value. A
// key is safe (not a conflict) when only one library node declares it,
// when every library node agrees on every shared attribute, or when an
// APP node is also present for that key (the app's value always wins, so
// there's nothing left to fight over). Return the conflicting keys, each
// once, in the order they first appear in nodes.
fun findMergeConflicts(nodes: List<ManifestNode>): List<String> {
    TODO("t7: keys where 2+ library nodes disagree on a shared attribute and no app node arbitrates")
}
