package modulegraph

/**
 * Module Graph practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :module-graph:test
 */

/** How one module depends on another. */
enum class DependencyType { API, IMPLEMENTATION }

/** An edge in the module graph: this module needs `target`, exposed as `type`. */
data class Dependency(val target: String, val type: DependencyType)

/** Where a module sits in the app's architecture, foundational to top. */
enum class Layer { CORE, FEATURE, APP }

/** One node in the module graph. */
data class Module(
    val name: String,
    val layer: Layer,
    val dependencies: List<Dependency> = emptyList()
)

// TODO(t1): T1DirectDependenciesTest
// Return the target names of `module`'s dependencies that are declared with
// the given `type`, in the order they appear in `module.dependencies`.
fun directDependenciesOfType(module: Module, type: DependencyType): Set<String> {
    TODO("t1: filter dependencies by type and collect their target names")
}

// TODO(t2): T2CompileClasspathTest
// Compute the set of module names visible on `moduleName`'s compile
// classpath. Its own direct dependencies are always visible, regardless of
// whether they're declared `api` or `implementation`, because it compiles
// directly against them. Beyond that first hop, only `api` dependencies keep
// leaking outward: if A depends on B, and B depends on C via `api`, then C is
// also on A's classpath, because C's types appear in B's public surface. If
// B depends on C via `implementation` instead, C is invisible to A: B needs
// C to compile itself, but never exposes it further.
fun compileClasspath(graph: Map<String, Module>, moduleName: String): Set<String> {
    TODO("t2: direct deps always count, but only api deps propagate past the first hop")
}

// TODO(t3): T3FindCycleTest
// Search the graph for a dependency cycle (following dependency edges of any
// type). If one exists, return the cycle as a list of module names starting
// and ending on the same module, e.g. ["a", "b", "c", "a"]. If the graph is
// acyclic, return null. Traverse modules in `graph`'s key order and each
// module's dependencies in their declared order, so the result is
// deterministic.
fun findCycle(graph: Map<String, Module>): List<String>? {
    TODO("t3: DFS tracking the current stack, return the cycle on a back edge or null")
}

// TODO(t4): T4BuildOrderTest
// Return module names in a valid build order: every module appears after
// every module it (directly or transitively) depends on. Traverse modules
// in `graph`'s key order and each module's dependencies in their declared
// order, so the result is deterministic. Assume the graph has no cycles.
fun buildOrder(graph: Map<String, Module>): List<String> {
    TODO("t4: DFS postorder, so a module is appended only after its dependencies are")
}

// TODO(t5): T5ModulesToRebuildTest
// Given that `changed` was just edited, return the names of every other
// module that must be rebuilt, i.e. every module that has `changed` on its
// compile classpath. This is where the implementation-vs-api split changes
// the blast radius: a module reached only through an `implementation` edge
// beyond the first hop is shielded, its consumers don't need to rebuild.
fun modulesToRebuild(graph: Map<String, Module>, changed: String): Set<String> {
    TODO("t5: a module rebuilds iff changed is on its compile classpath")
}

// TODO(t6): T6LayeringViolationsTest
// Flag every dependency that points from a lower layer to a strictly higher
// one (CORE may not depend on FEATURE or APP, FEATURE may not depend on
// APP). Return each violation as (fromModule, toModule), in the order found
// by scanning `graph` in key order and each module's dependencies in their
// declared order.
fun findLayeringViolations(graph: Map<String, Module>): List<Pair<String, String>> {
    TODO("t6: flag any dependency whose target's layer outranks its own")
}
