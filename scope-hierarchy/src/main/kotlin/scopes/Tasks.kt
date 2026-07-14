package scopes

/**
 * Hilt/Dagger component scoping practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :scope-hierarchy:test
 */

// The fixed Hilt component hierarchy: Singleton is the root, ActivityRetained
// survives config changes, ViewModel and Activity are both its children, and
// Fragment nests under Activity.
enum class ComponentType { SINGLETON, ACTIVITY_RETAINED, ACTIVITY, VIEW_MODEL, FRAGMENT }

// A concrete instance of a component in the running app, e.g. "the
// ActivityRetainedComponent for this particular Activity instance".
class Component(val type: ComponentType, val id: String, val parent: Component?)

class IllegalComponentNestingException(message: String) : RuntimeException(message)

private val allowedChildren: Map<ComponentType, Set<ComponentType>> = mapOf(
    ComponentType.SINGLETON to setOf(ComponentType.ACTIVITY_RETAINED),
    ComponentType.ACTIVITY_RETAINED to setOf(ComponentType.ACTIVITY, ComponentType.VIEW_MODEL),
    ComponentType.ACTIVITY to setOf(ComponentType.FRAGMENT),
    ComponentType.VIEW_MODEL to emptySet(),
    ComponentType.FRAGMENT to emptySet()
)

// TODO(t1): T1ComponentTreeTest
// Create a child Component of `type` under `parent`. Only nestings present
// in the real Hilt hierarchy are legal: Singleton -> ActivityRetained,
// ActivityRetained -> Activity, ActivityRetained -> ViewModel, and
// Activity -> Fragment. Anything else (e.g. Singleton -> Fragment directly,
// or Activity -> ViewModel) throws IllegalComponentNestingException naming
// both the parent's type and the requested child type.
fun createChild(parent: Component, type: ComponentType, id: String): Component {
    TODO("t1: return a legal child Component under parent, else throw IllegalComponentNestingException")
}

// TODO(t2): T2ScopedCachingTest
// An unscoped binding runs `factory` fresh on every call to get(). A scoped
// binding runs `factory` once, the first time get() is called, and returns
// that same cached instance on every later call.
class BindingProvider(private val scoped: Boolean, private val factory: () -> Any) {
    private var cached: Any? = null

    fun get(): Any {
        TODO("t2: when scoped cache the first instance and reuse it, when unscoped call factory every time")
    }
}

// TODO(t3): T3VisibilityTest
// A binding installed at `installedAt` is visible from `requester` only if
// requester IS installedAt, or requester is a descendant of installedAt
// (reachable by following `parent` links upward from requester until it
// either hits installedAt or runs out of ancestors). It is never visible
// from installedAt's ancestor or from a sibling component.
fun isVisible(installedAt: Component, requester: Component): Boolean {
    TODO("t3: true only if requester is installedAt or a descendant of it")
}

class IllegalScopeException(message: String) : RuntimeException(message)

// TODO(t4): T4ScopeValidationTest
// A binding's scope annotation (null means unscoped) must match the type of
// the component it's installed in exactly: @Singleton can only be used on a
// binding installed in the Singleton component, @ActivityScoped only in the
// Activity component, and so on. An unscoped binding (scope == null) is
// legal in any component. Throw IllegalScopeException naming both the scope
// and the component when they don't match.
fun validateScope(scope: ComponentType?, installedIn: ComponentType) {
    TODO("t4: throw IllegalScopeException unless scope is null or equal to installedIn")
}

private val parentType: Map<ComponentType, ComponentType?> = mapOf(
    ComponentType.SINGLETON to null,
    ComponentType.ACTIVITY_RETAINED to ComponentType.SINGLETON,
    ComponentType.ACTIVITY to ComponentType.ACTIVITY_RETAINED,
    ComponentType.VIEW_MODEL to ComponentType.ACTIVITY_RETAINED,
    ComponentType.FRAGMENT to ComponentType.ACTIVITY
)

class ScopeLeakException(message: String) : RuntimeException(message)

// TODO(t5): T5ScopeLeakTest
// `consumerScope` is the scope of the object that wants to hold a reference
// to something scoped at `dependencyScope`. That's only safe when
// dependencyScope lives at least as long as consumerScope, i.e. dependencyScope
// is consumerScope itself or an ancestor of it in the component hierarchy
// (found by following parentType upward from consumerScope). If
// dependencyScope is shorter-lived than consumerScope, consumerScope will
// outlive it and keep a dangling reference, the classic "Activity leaked
// into a singleton" bug: throw ScopeLeakException describing the leak.
fun checkForScopeLeak(consumerScope: ComponentType, dependencyScope: ComponentType) {
    TODO("t5: throw ScopeLeakException when dependencyScope is shorter-lived than consumerScope")
}

// A single binding site: `key` built by `factory` is installed at `installedAt`.
class BindingSite(val installedAt: Component, val key: String, val factory: () -> Any)

class MissingComponentBindingException(message: String) : RuntimeException(message)

// TODO(t6): T6NearestBindingTest
// `sites` may bind the same key at more than one component in the tree, for
// example a debug override installed closer to the leaf than the app's
// default. Resolve `key` for `requester` by walking upward from requester
// itself through its ancestors (requester first, then its parent, then its
// parent's parent, ...) and returning the value built by the first site
// whose installedAt matches a component on that walk. Throw
// MissingComponentBindingException naming key if the walk reaches the root
// without a match.
fun resolveNearest(sites: List<BindingSite>, requester: Component, key: String): Any {
    TODO("t6: walk requester's ancestor chain and return the first matching site's built value")
}
