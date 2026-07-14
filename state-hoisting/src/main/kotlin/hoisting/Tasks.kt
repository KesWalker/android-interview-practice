package hoisting

import kotlin.reflect.KProperty

/**
 * StateHoisting practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :state-hoisting:test
 */

/** A single mutable cell that supports Kotlin property delegation. */
class MutableStateLike<T>(initial: T) {
    private var current: T = initial

    // TODO(t1): T1DelegateTest
    // Implement property delegation: getValue returns the held value and
    // setValue stores a new one, so an instance can be used with `by`, e.g.
    // `var count by MutableStateLike(0)`.
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        TODO("t1: return the current value")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        TODO("t1: store newValue as the current value")
    }
}

/** A state cell that notifies listeners, but only on an actual change. */
class ObservableState<T>(initial: T) {
    var value: T = initial
        private set

    private val listeners = mutableListOf<(T) -> Unit>()

    fun observe(listener: (T) -> Unit) {
        listeners += listener
    }

    // TODO(t2): T2NotifyOnChangeTest
    // Update `value` to newValue and notify every observer with the new
    // value, but only when newValue is structurally different (by equals)
    // from the current value. This mirrors how Compose skips recomposition
    // when a State's value doesn't actually change.
    fun update(newValue: T) {
        TODO("t2: set value and notify listeners only if it actually changed")
    }
}

/** A remember-alike cache: recomputes only when its keys change. */
class RememberCache {
    private var lastKeys: List<Any?>? = null
    private var lastValue: Any? = null

    // TODO(t3): T3RememberTest
    // Return a cached value for these keys. If `keys` equals the keys used
    // on the previous call, return the cached value without calling
    // `compute` again. Otherwise call `compute`, cache its result alongside
    // `keys`, and return it. The very first call always computes.
    @Suppress("UNCHECKED_CAST")
    fun <T> getOrCompute(keys: List<Any?>, compute: () -> T): T {
        TODO("t3: recompute only when keys differ from the previous call")
    }
}

/** Types a Bundle can actually hold, mirroring rememberSaveable's Saver. */
private val saveableTypes = setOf(
    String::class, Int::class, Long::class, Double::class, Float::class, Boolean::class
)

private fun isSaveableSafe(value: Any?): Boolean =
    value == null || saveableTypes.contains(value::class)

// TODO(t4): T4RememberSaveableTest
// Model rememberSaveable for a single slot. If `saved` contains an entry
// for `key` and that entry's value is a saveable-safe type (String, Int,
// Long, Double, Float, Boolean, or null), restore it. Otherwise ignore
// `saved` and fall back to calling `initial()`.
@Suppress("UNCHECKED_CAST")
fun <T> rememberSaveable(key: String, saved: Map<String, Any?>, initial: () -> T): T {
    TODO("t4: restore saved[key] when present and saveable-safe, else call initial()")
}

/** A node in a UI tree, linked to its parent (null for the root). */
data class Node(val id: String, val parent: Node?)

private fun ancestorsInclusive(node: Node): List<Node> =
    generateSequence(node) { it.parent }.toList()

// TODO(t5): T5LowestCommonAncestorTest
// Return the lowest common ancestor of `nodes`: the deepest node that is an
// ancestor of (or equal to) every node in the list, walking each node's
// `parent` chain toward the root. `nodes` is never empty.
fun lowestCommonAncestor(nodes: List<Node>): Node {
    TODO("t5: walk each node's ancestor chain and find the deepest shared one")
}

// TODO(t6): T6ResolveOwnerTest
// Compute where a piece of state should be hoisted to. The owner must be an
// ancestor of (or equal to) every reader AND every writer, because both
// need to reach the state through props and callbacks passed down the
// tree, and it must be the lowest (deepest) node with that property.
fun resolveStateOwner(readers: List<Node>, writers: List<Node>): Node {
    TODO("t6: the owner is the lowest common ancestor of readers and writers combined")
}
