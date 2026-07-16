package lambdas

/**
 * Lambdas & Inline Functions practice.
 *
 * Tasks 1-4 are bodies you fill in. Tasks 5 and 6 are DECLARED BY YOU, from
 * scratch: their whole lesson lives in the declaration's modifiers, and the
 * compiler itself will teach them to you - declare the function `inline` as
 * required, then watch what it refuses to let you do with the lambda
 * parameters, and read its suggestions. Their tests find your declaration by
 * reflection once it exists.
 *
 * Run a single test class from the gutter in Android Studio, or all of them:
 *
 *     ./gradlew :lambdas-inline:test
 */

// TODO(t1): T1SumOfPositivesTest
// Add up only the numbers in `numbers` that are greater than zero.
fun sumOfPositives(numbers: List<Int>): Int {
    TODO("t1: sum only the positive numbers in the list")
}

// TODO(t2): T2CounterTest
// Return a callable that reports how many times it has itself been called,
// starting at 1. A second call to makeCounter() must produce an independent
// callable with its own separate count.
fun makeCounter(): () -> Int {
    TODO("t2: return a callable that counts its own invocations")
}

// TODO(t3): T3IndexOfFirstNegativeTest
// Return the index of the first negative number in `numbers`, or -1 if there
// isn't one.
fun indexOfFirstNegative(numbers: List<Int>): Int {
    TODO("t3: return the index of the first negative number, or -1")
}

// TODO(t4): T4FilterIsInstanceTest
// Return only the elements of `items` whose runtime type is T, keeping order.
// This one keeps its signature: `reified` only exists because the body is
// inlined into the call site - there is no other way this function could know
// what T is at runtime.
inline fun <reified T> filterIsInstance2(items: List<Any?>): List<T> {
    TODO("t4: keep only the elements that are actually of type T")
}

// TODO(t5): T5TimedRunTest
// Declare, from scratch, an INLINE function
// `timedRun(label: String, log: MutableList<String>, action: () -> Unit)` that
// logs "start:<label>", runs `action` from inside a Runnable (a real wrapped
// object, run immediately - not a bare call), then logs "end:<label>". The
// compiler will refuse the naive version: an inlined lambda can't cross into
// another object. Read its error - it names the modifier that fixes it.

// TODO(t6): T6RegisterHandlersTest
// Declare, from scratch, an INLINE function
// `registerHandlers(immediate: () -> Unit, deferred: () -> Unit, store: MutableList<() -> Unit>)`
// that calls `immediate` right away and adds `deferred` to `store` for later.
// Storing an inlined lambda is impossible - it isn't an object at runtime.
// Again the compiler names the modifier that opts one parameter back out.
