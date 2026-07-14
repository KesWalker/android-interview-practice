package backstack

/**
 * BackStack practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :back-stack:test
 */

/** A single entry on the navigation back stack. */
data class NavEntry(
    val route: String,
    val graphId: String? = null,
    val savedState: Map<String, Any?> = emptyMap()
)

/** The stack itself: bottom of the list is the start destination, the last entry is the top. */
typealias BackStack = List<NavEntry>

// TODO(t1): T1NavigateTest
// Push a new entry for `route` onto the top of the stack and return the
// resulting stack. This is a pure function, `stack` itself is left
// untouched; the new entry starts with an empty saved-state map.
fun navigate(stack: BackStack, route: String, graphId: String? = null): BackStack {
    TODO("t1: push a new NavEntry for route onto the top of stack")
}

// TODO(t2): T2PopBackStackTest
// Remove the top entry and return the resulting stack. The stack always
// keeps at least one entry (its start destination): if `stack` only has one
// entry, popBackStack is a no-op and the same stack comes back unchanged.
fun popBackStack(stack: BackStack): BackStack {
    TODO("t2: drop the top entry, or no-op when only one entry remains")
}

// TODO(t3): T3PopUpToTest
// Pop entries off the top until `route` is reached, using the occurrence of
// `route` NEAREST THE TOP when it appears more than once (search from the
// top down). When `inclusive` is true, `route`'s own entry is popped too,
// so the new top is whatever was below it. When `inclusive` is false,
// `route`'s entry becomes the new top. If `route` doesn't appear in `stack`
// at all, return `stack` unchanged.
fun popUpTo(stack: BackStack, route: String, inclusive: Boolean): BackStack {
    TODO("t3: pop down to route's nearest-to-top occurrence, dropping it too when inclusive")
}

// TODO(t4): T4NavigateSingleTopTest
// Like navigate, but if the CURRENT TOP entry's route already equals
// `route`, don't push a duplicate: return `stack` unchanged instead. This
// only looks at the top of the stack; an earlier occurrence of `route`
// further down is not deduplicated, a new entry is still pushed on top of
// it in that case.
fun navigateSingleTop(stack: BackStack, route: String, graphId: String? = null): BackStack {
    TODO("t4: skip pushing a duplicate only when route is already on top")
}

/** The back stack plus the shared state each nested graph keeps alive while any of its entries is on the stack. */
data class NavGraphState(
    val stack: BackStack,
    val graphSharedState: Map<String, Map<String, Any?>> = emptyMap()
)

// TODO(t5): T5GraphScopedStateTest
// Pop the top entry (same rule as popBackStack: no-op if only one entry
// remains). A nested graph's shared state should only live as long as at
// least one of its entries is still on the stack, so after popping, if the
// popped entry's graphId no longer appears anywhere in the resulting stack,
// remove that graphId's entry from graphSharedState entirely. An entry with
// a null graphId never touches graphSharedState.
fun popBackStackClearingGraphState(state: NavGraphState): NavGraphState {
    TODO("t5: pop the top entry, dropping its graph's shared state once the graph fully leaves the stack")
}

// TODO(t6): T6PopWithResultTest
// Write a result back to the PREVIOUS entry (the one just below the top)
// before popping, the same way a screen returns a value to whoever launched
// it. Set `key` to `value` in the previous entry's savedState map, then pop
// the top entry as popBackStack would. If `stack` only has one entry there
// is no previous entry to write into, so return it unchanged.
fun popBackStackWithResult(stack: BackStack, key: String, value: Any?): BackStack {
    TODO("t6: write key/value into the previous entry's savedState, then pop the top")
}
