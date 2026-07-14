package diffutil

/**
 * DiffUtil practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :diff-util:test
 */

/** A single change RecyclerView would apply to turn the old list into the new one. */
sealed interface DiffOp {
    data class Insert(val newIndex: Int) : DiffOp
    data class Remove(val oldIndex: Int) : DiffOp
    data class Move(val oldIndex: Int, val newIndex: Int) : DiffOp
    data class Change(val oldIndex: Int, val newIndex: Int, val payload: Any?) : DiffOp
}

/** How many of each op kind a diff produced; used to compare a diff's shape. */
data class DiffSummary(val inserts: Int, val removes: Int, val moves: Int, val changes: Int)

/** A todo-list card: the test data every task below diffs. */
data class Card(val id: Int, val label: String, val done: Boolean = false)

// TODO(t1): T1FindMatchesTest
// This is areItemsTheSame: two entries represent the same underlying item
// when idOf returns the same key for both, regardless of whether their
// content changed. For every item in `new`, if an item with the same id
// exists somewhere in `old`, record it as newIndex -> oldIndex. Items whose
// id has no counterpart in the other list are left out entirely; those are
// handled by findRemovals/findInsertions instead.
fun <T> findMatches(old: List<T>, new: List<T>, idOf: (T) -> Any): Map<Int, Int> {
    TODO("t1: map each new item's index to its matching old item's index by id")
}

// TODO(t2): T2FindRemovalsTest
// An old item is removed when its id doesn't appear anywhere in `new`.
// Return one Remove per such item, in ascending oldIndex order.
fun <T> findRemovals(old: List<T>, new: List<T>, idOf: (T) -> Any): List<DiffOp.Remove> {
    TODO("t2: an old item with no id match in new is a Remove")
}

// TODO(t3): T3FindInsertionsTest
// A new item is inserted when its id doesn't appear anywhere in `old`.
// Return one Insert per such item, in ascending newIndex order.
fun <T> findInsertions(old: List<T>, new: List<T>, idOf: (T) -> Any): List<DiffOp.Insert> {
    TODO("t3: a new item with no id match in old is an Insert")
}

// TODO(t4): T4FindMovesTest
// `matches` pairs up newIndex -> oldIndex for every item present in both
// lists (as findMatches produces). Walk the matches in ascending newIndex
// order, tracking the highest oldIndex seen so far among items you've
// decided "kept their relative order". An item keeps its relative order (no
// Move) when its oldIndex is greater than that running highest, in which
// case it becomes the new highest. Otherwise its oldIndex fell backwards
// relative to what came before it, so it's a Move.
fun findMoves(matches: Map<Int, Int>): List<DiffOp.Move> {
    TODO("t4: flag matches whose oldIndex breaks the increasing run as Move")
}

// TODO(t5): T5FindChangesTest
// This is areContentsTheSame, plus the payload for a partial rebind. For
// every matched pair, when contentsTheSame(old item, new item) is false,
// emit a Change carrying payloadFor(old item, new item) so the adapter can
// do a partial rebind instead of a full one. Matched pairs whose content is
// unchanged produce nothing. Order results by ascending newIndex.
fun <T> findChanges(
    matches: Map<Int, Int>,
    old: List<T>,
    new: List<T>,
    contentsTheSame: (T, T) -> Boolean,
    payloadFor: (T, T) -> Any? = { _, _ -> null }
): List<DiffOp.Change> {
    TODO("t5: emit a Change with a payload for every matched pair whose content differs")
}

// TODO(t6): T6ComputeDiffTest
// Combine everything above into the full ordered diff: findRemovals, then
// findMoves (over findMatches' result), then findInsertions, then
// findChanges. Concatenate them in exactly that order; each is already
// sorted the way its own function guarantees.
fun <T> computeDiff(
    old: List<T>,
    new: List<T>,
    idOf: (T) -> Any,
    contentsTheSame: (T, T) -> Boolean,
    payloadFor: (T, T) -> Any? = { _, _ -> null }
): List<DiffOp> {
    TODO("t6: combine findRemovals + findMoves + findInsertions + findChanges in that order")
}

// TODO(t7): T7SummarizeTest
// Count how many of each DiffOp subtype appear in `ops`.
fun summarize(ops: List<DiffOp>): DiffSummary {
    TODO("t7: tally inserts, removes, moves and changes")
}
