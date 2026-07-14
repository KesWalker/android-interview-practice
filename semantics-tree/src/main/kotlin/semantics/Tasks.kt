package semantics

/**
 * Semantics Tree & UI Test Matchers practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :semantics-tree:test
 *
 * SemanticsNode is already complete, you're only filling in the functions
 * that walk and query it, exactly what a UI test framework like Compose UI
 * testing or Espresso builds on top of the accessibility/semantics tree.
 */

/** One node of a UI's semantics tree: what a test (or a screen reader) can see. */
data class SemanticsNode(
    val text: String? = null,
    val contentDescription: String? = null,
    val testTag: String? = null,
    val role: String? = null,
    val enabled: Boolean = true,
    val selected: Boolean? = null,
    val mergeDescendants: Boolean = false,
    val children: List<SemanticsNode> = emptyList()
)

/** A predicate over a single node, the building block every finder is made of. */
typealias Matcher = (SemanticsNode) -> Boolean

/** Matches a node whose own, unmerged text equals [text] exactly. */
fun hasText(text: String): Matcher = { node -> node.text == text }

/** Matches a node whose testTag equals [tag] exactly. */
fun hasTestTag(tag: String): Matcher = { node -> node.testTag == tag }

// TODO(t1): T1FlattenTest
// Collect every node in the tree rooted at `root`, including root itself, in
// depth-first pre-order: a node comes before its children, and children are
// visited left to right.
fun flatten(root: SemanticsNode): List<SemanticsNode> {
    TODO("t1: pre-order depth-first collect of root and all its descendants")
}

// TODO(t2): T2MatcherCombinatorsTest
// Implement the three matcher combinators every finder is assembled from.
// `and` accepts a node only when both `a` and `b` accept it. `or` accepts a
// node when at least one of `a` or `b` accepts it. `not` accepts a node when
// `a` does not.
fun and(a: Matcher, b: Matcher): Matcher {
    TODO("t2: node matches only when both a and b match it")
}

fun or(a: Matcher, b: Matcher): Matcher {
    TODO("t2: node matches when either a or b matches it")
}

fun not(a: Matcher): Matcher {
    TODO("t2: node matches when a does not match it")
}

// TODO(t3): T3MergedTextTest
// Compute the text a UI test framework reports for `node`. When
// node.mergeDescendants is false, that is just node.text on its own, a
// screen reader (or a test) would visit descendants separately. When it is
// true, the node's subtree is reported as one unit: collect node.text
// followed by every descendant's text in pre-order, skip nulls, and join
// what's left with ", ". If nothing in the subtree has text, return null.
fun mergedText(node: SemanticsNode): String? {
    TODO("t3: return node.text unmerged, or node + descendant text joined when merging")
}

// TODO(t4): T4FindNodeTest
// The core finder every onNodeWith*() is built on. Search the tree rooted at
// `root` for nodes matching `matcher`. Return the single match. This is the
// classic ambiguity failure a UI test framework must catch: when zero nodes
// match, throw AssertionError("no node found matching: $description"). When
// more than one node matches, throw AssertionError("$n nodes found matching:
// $description, expected exactly one"), where $n is the match count.
fun findNode(root: SemanticsNode, description: String, matcher: Matcher): SemanticsNode {
    TODO("t4: return the single matching node, or throw a clear zero/many error")
}

// TODO(t5): T5FindersTest
// Build the two convenience finders tests actually call. onNodeWithText
// locates the node whose own text equals `text`, using findNode with the
// description `text "$text"`. onNodeWithTag locates the node whose testTag
// equals `tag`, using findNode with the description `testTag "$tag"`.
fun onNodeWithText(root: SemanticsNode, text: String): SemanticsNode {
    TODO("t5: call findNode with hasText(text) and description: text \"<text>\"")
}

fun onNodeWithTag(root: SemanticsNode, tag: String): SemanticsNode {
    TODO("t5: call findNode with hasTestTag(tag) and description: testTag \"<tag>\"")
}

// TODO(t6): T6AssertionsTest
// Implement two assertion helpers a test calls on a node it already found.
// assertHasText throws AssertionError("expected text \"$expected\" but was
// \"${mergedText(node)}\"") when mergedText(node) doesn't equal `expected`,
// and does nothing when it does. assertIsEnabled throws AssertionError(
// "expected node to be enabled but it was disabled") when node.enabled is
// false, and does nothing when it's true.
fun assertHasText(node: SemanticsNode, expected: String) {
    TODO("t6: throw AssertionError when mergedText(node) doesn't equal expected")
}

fun assertIsEnabled(node: SemanticsNode) {
    TODO("t6: throw AssertionError when node.enabled is false")
}

// TODO(t7): T7WaitForIdleTest
// Model the idling rule a UI test must follow: wait for real quiescence
// instead of sleeping. `queue` holds pending actions (e.g. a pending
// recomposition); running one may enqueue more, simulating one recomposition
// triggering another. Drain `queue` by repeatedly removing and running its
// first action until it's empty, returning how many actions ran. If `queue`
// is still non-empty after `maxSteps` actions have run, throw
// IllegalStateException("did not reach idle after $maxSteps steps") rather
// than looping forever or sleeping and hoping.
fun waitForIdle(queue: MutableList<() -> Unit>, maxSteps: Int): Int {
    TODO("t7: drain queue (actions may enqueue more), or throw after maxSteps")
}
