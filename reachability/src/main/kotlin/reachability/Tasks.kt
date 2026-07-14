package reachability

/**
 * R8 code shrinking practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :reachability:test
 */

/** A class in the program graph: its own members and the node ids it references. */
data class ProgramClass(
    val name: String,
    val members: List<String>,
    val references: Set<String> = emptySet()
)

// TODO(t1): T1ReachableClassesTest
// Mark phase: starting from `roots`, follow `classes`' reference edges to
// their transitive closure. `classes` maps a class name to the set of
// class names its code directly references. A referenced name that isn't
// a key in `classes` is still included in the result (it exists, we just
// have no further edges to follow from it), but it contributes no further
// expansion. Return every reachable class name, including the roots.
fun reachableClasses(classes: Map<String, Set<String>>, roots: Set<String>): Set<String> {
    TODO("t1: BFS/DFS from roots over the reference graph, return the transitive closure")
}

// TODO(t2): T2StrippedClassesTest
// Sweep phase: given the same graph and roots, return the class names
// that are declared in `classes` but did NOT come back reachable from
// reachableClasses. These are the ones R8 deletes.
fun strippedClasses(classes: Map<String, Set<String>>, roots: Set<String>): Set<String> {
    TODO("t2: classes.keys minus reachableClasses(classes, roots)")
}

// TODO(t3): T3ReachableWithKeepRulesTest
// Member-aware mark phase with two kinds of keep rules:
//   - `keepClassRoots`: like "-keep class X", X itself is a root.
//   - `keepClassMembers`: like "-keepclassmembers class X { members }". A
//     member name (or the wildcard "*" for all of X's declared members) is
//     ONLY protected once X is reached some other way, it does NOT by
//     itself make X reachable. An X that nothing ever references stays
//     unreachable, keepclassmembers rule or not, and gets stripped along
//     with its "protected" members.
// Traverse from `keepClassRoots`. For every class name that becomes
// reachable (via a root or via another reachable class's `references`),
// also add member ids ("ClassName#member") for that class's entry (if
// any) in `keepClassMembers`, expanding "*" to all of that class's
// declared members. A member id has no further outgoing edges of its own,
// but reaching one always keeps its owner class name in the result too.
fun reachableWithKeepRules(
    classes: Map<String, ProgramClass>,
    keepClassRoots: Set<String>,
    keepClassMembers: Map<String, Set<String>>
): Set<String> {
    TODO("t3: mark from keepClassRoots, expand references, activate keepClassMembers only once the class is reached")
}

// TODO(t4): T4ReachableWithReflectionRootsTest
// The reflection problem: a class only ever loaded via Class.forName (or
// similar) has no static reference edge pointing at it, so plain
// reachableClasses reports it unreachable and R8 would strip it, even
// though it's genuinely used at runtime. The fix is a keep rule that adds
// it as an explicit root. Compute reachability the same way as
// reachableClasses, but seed the mark phase with `roots` UNION
// `reflectivelyAccessedClasses` instead of `roots` alone.
fun reachableWithReflectionRoots(
    classes: Map<String, Set<String>>,
    roots: Set<String>,
    reflectivelyAccessedClasses: Set<String>
): Set<String> {
    TODO("t4: reachableClasses(classes, roots + reflectivelyAccessedClasses)")
}

// TODO(t5): T5ObfuscationMappingTest
// Build a deterministic obfuscation mapping table: sort `classNames`
// alphabetically, then assign each one the next short name from the
// sequence "a", "b", ..., "z", "aa", "ab", ... skipping any short name
// that appears in `reservedShortNames`. Return the original-to-short-name
// map in assignment order.
fun obfuscationMapping(classNames: Set<String>, reservedShortNames: Set<String> = emptySet()): Map<String, String> {
    TODO("t5: sort class names, assign the next non-reserved short name from the a,b,...,z,aa,... sequence to each")
}

// TODO(t6): T6RetraceClassNameTest
// Reverse-lookup `obfuscatedName` in `mapping` (original -> short name) to
// recover the original class name. If no entry in `mapping` has that
// short name as its value, return `obfuscatedName` unchanged (nothing to
// retrace it to). Note this is a pure lookup: if you retrace using a
// mapping.txt from a DIFFERENT build than the one that produced the crash,
// the short name can still exist in that mapping (pointing at some other
// class entirely), so this happily returns a wrong-but-plausible-looking
// answer instead of failing loudly.
fun retraceClassName(mapping: Map<String, String>, obfuscatedName: String): String {
    TODO("t6: find the mapping entry whose value equals obfuscatedName, return its key, else return obfuscatedName")
}
