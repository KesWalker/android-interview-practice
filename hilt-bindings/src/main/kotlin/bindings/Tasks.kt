package bindings

/**
 * Hilt/Dagger binding graph practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :hilt-bindings:test
 */

// A binding is identified by its type name and an optional qualifier, just
// like Dagger/Hilt tell apart `ApiClient` from `@Named("prod") ApiClient`.
data class Key(val type: String, val qualifier: String? = null) {
    override fun toString(): String = if (qualifier != null) "$type@$qualifier" else type
}

// One node in the binding graph: how to build the instance for `key`, and
// which other keys must be resolved first and passed to `create`, in order.
class Binding(
    val key: Key,
    val dependencies: List<Key> = emptyList(),
    val create: (List<Any>) -> Any
)

typealias Graph = Map<Key, Binding>

class MissingBindingException(message: String) : RuntimeException(message)
class DependencyCycleException(message: String) : RuntimeException(message)

// TODO(t1): T1ConstructorInjectionTest
// Resolve `key` by constructor injection: recursively resolve every entry in
// its binding's `dependencies` (in the same order), then call `create` with
// the resolved instances. Every key referenced by `graph` is guaranteed to
// exist in it.
fun resolve(graph: Graph, key: Key): Any {
    TODO("t1: recursively resolve key's dependencies, then call its binding's create")
}

// TODO(t2): T2BindsMappingTest
// Model Hilt's `@Binds`: `binds` maps an interface key to the key of the
// implementation that satisfies it. Resolving `key` should use `graph`
// directly when it has a binding for `key`; otherwise follow `binds` to find
// the implementation key and resolve that instead (recursing the same way
// `resolve` does, including through its own dependencies).
fun resolveWithBinds(graph: Graph, binds: Map<Key, Key>, key: Key): Any {
    TODO("t2: if key isn't directly bound, follow binds to the implementation key and resolve that")
}

// TODO(t3): T3QualifierDisambiguationTest
// `graph` can hold two bindings for the same type name that differ only by
// qualifier (Dagger's `@Named`/custom qualifier annotations). Resolve the
// binding whose key matches `key` exactly, both type and qualifier. Never
// fall back to a binding for the same type under a different (or missing)
// qualifier; throw MissingBindingException naming the exact key requested
// when there's no exact match.
fun resolveQualified(graph: Graph, key: Key): Any {
    TODO("t3: resolve the binding whose key matches type AND qualifier exactly, else throw naming it")
}

// TODO(t4): T4MissingBindingTest
// Same idea as resolve, but the graph is untrusted: if `key`, or any key it
// transitively depends on, has no binding in `graph`, throw
// MissingBindingException with a message that names that exact key (its
// toString()), not just a generic failure.
fun resolveOrThrow(graph: Graph, key: Key): Any {
    TODO("t4: throw MissingBindingException naming the first unbound key found, at any depth")
}

// TODO(t5): T5CycleDetectionTest
// Same idea as resolveOrThrow, but also detect a dependency cycle: if
// resolving `key` requires (possibly transitively) resolving `key` again
// before it has finished building, throw DependencyCycleException whose
// message lists the cycle path with " -> ", e.g. "A -> B -> A".
fun resolveDetectingCycles(graph: Graph, key: Key): Any {
    TODO("t5: throw DependencyCycleException listing the cycle when key depends on itself")
}

// A lazy handle to a binding, Dagger/Hilt's Provider<T>. Calling get()
// resolves the target key on demand instead of eagerly during graph
// construction, which is how real code breaks a constructor-injection cycle.
class Provider(
    private val graph: Graph,
    private val key: Key,
    private val lazyDependencies: Set<Key>
) {
    fun get(): Any = resolveWithProviders(graph, key, lazyDependencies)
}

// TODO(t6): T6ProviderBreaksCycleTest
// Resolve `key` like resolveDetectingCycles, except any dependency key that
// appears in `lazyDependencies` must NOT be resolved eagerly: pass a
// Provider for it into `create` instead of the resolved instance. This
// mirrors injecting `Provider<Foo>` in a Dagger constructor to defer the
// actual resolution and dodge a cycle between two bindings that would
// otherwise need each other to finish building.
fun resolveWithProviders(graph: Graph, key: Key, lazyDependencies: Set<Key>): Any {
    TODO("t6: pass a Provider instead of a resolved instance for any dependency key in lazyDependencies")
}
