package recomposition

/**
 * Recomposition mental-model practice.
 *
 * Compose isn't a dependency here, it's a small model you build by hand: a
 * node tree where each node declares the state keys it reads, a phase
 * model for composition/layout/draw, and the positional-memoization rule
 * that key() exists to fix. Each function below is broken or unwritten and
 * has a matching test in src/test that is currently RED. Make each one
 * GREEN, one task at a time, with:
 *
 *     ./gradlew :recomposition:test
 */

// --- Skip vs recompose ---------------------------------------------------

/** One composable: the state keys it reads, and its children. */
data class Node(val id: String, val readsKeys: Set<String>, val children: List<Node> = emptyList())

// TODO(t1): T1NodeRecomposesTest
// A composable recomposes exactly when at least one of the state keys it
// reads is in the changed set. If its reads don't overlap changedKeys at
// all, Compose skips it, even if a sibling or parent is recomposing.
fun nodeRecomposes(node: Node, changedKeys: Set<String>): Boolean {
    TODO("t1: true iff node.readsKeys intersects changedKeys")
}

// --- Phases ---------------------------------------------------------------

/** The three passes Compose runs on every frame. */
enum class Phase { COMPOSITION, LAYOUT, DRAW }

/** A kind of state a composable might read, each tied to one phase. */
enum class StateRead { TEXT_CONTENT, PADDING_DP, SIZE_PX, COLOR, OFFSET_PX }

// TODO(t2): T2InvalidatedPhaseTest
// Decide which phase a given state read invalidates. Reading text content
// changes what gets emitted, so it invalidates COMPOSITION. Reading
// padding or a measured size changes how things are sized and placed, so
// both invalidate LAYOUT. Reading a color only changes how existing
// pixels are painted, and reading an offset implemented as a
// graphicsLayer translation moves pixels without re-measuring anything,
// so both of those invalidate only DRAW.
fun invalidatedPhase(read: StateRead): Phase {
    TODO("t2: TEXT_CONTENT->COMPOSITION, PADDING_DP/SIZE_PX->LAYOUT, COLOR/OFFSET_PX->DRAW")
}

// TODO(t3): T3RecomposingNodeIdsTest
// Walk the whole tree rooted at `root` (the node itself, then every
// descendant) and return the ids of the nodes that recompose per
// nodeRecomposes' rule, i.e. the ones whose own reads overlap
// changedKeys. A parent recomposing does not drag its children along,
// each node is checked independently.
fun recomposingNodeIds(root: Node, changedKeys: Set<String>): Set<String> {
    TODO("t3: ids of every node in the tree whose reads overlap changedKeys")
}

// --- Positional memoization / key() ---------------------------------------

/** A remembered piece of state living in one slot of a list. */
data class Slot(val itemKey: String, val rememberedState: Int)

// TODO(t4): T4RecomposeWithoutKeyTest
// Model what happens when a list is recomposed WITHOUT key(): remembered
// state is tied to the slot's position, not to the item in it. Build the
// new slot list by taking newOrder's item keys in order, but pairing each
// position with whatever rememberedState was already sitting at that same
// index in `before` (falling back to 0 for a position past the end of
// `before`). The state visibly does not follow its item across a
// reorder.
fun recomposeWithoutKey(before: List<Slot>, newOrder: List<String>): List<Slot> {
    TODO("t4: keep before[index]'s rememberedState, swap in newOrder[index]'s key")
}

// TODO(t5): T5RecomposeWithKeyTest
// Model the same reorder, but WITH key(): remembered state now follows
// the item's key instead of its position. For each key in newOrder, look
// up its rememberedState from `before` (matched by itemKey); a key that
// wasn't present in `before` is a newly-added item and starts fresh at 0.
fun recomposeWithKey(before: List<Slot>, newOrder: List<String>): List<Slot> {
    TODO("t5: look up each newOrder key's old state by itemKey, default 0 if new")
}

// --- Putting it together ----------------------------------------------

/** One composable, described by which kinds of state it reads. */
data class RenderNode(val id: String, val readTypes: Set<StateRead>)

/** The invalidation a changed-state set causes a node, or NONE. */
enum class Invalidation { NONE, DRAW, LAYOUT, COMPOSITION }

// TODO(t6): T6InvalidationForTest
// Combine invalidatedPhase with a node: intersect node.readTypes with
// changed. If that intersection is empty, nothing about this node is
// invalidated at all, return NONE. Otherwise map every read in the
// intersection through invalidatedPhase and return the most expensive
// one: COMPOSITION beats LAYOUT beats DRAW, since a phase always reruns
// every cheaper phase after it too.
fun invalidationFor(node: RenderNode, changed: Set<StateRead>): Invalidation {
    TODO("t6: NONE if no overlap, else the priciest phase among the overlapping reads")
}
