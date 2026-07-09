package lambdas

/**
 * Lambdas & Inline Functions practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to fill it in so its test goes
 * GREEN. Run a single test class from the gutter in Android Studio, or run them
 * all with:
 *
 *     ./gradlew :lambdas-inline:test
 */

// TODO(t1): SumOfPositivesTest
// Add up only the numbers in `numbers` that are greater than zero.
fun sumOfPositives(numbers: List<Int>): Int {
    TODO("t1: sum only the positive numbers in the list")
}

// TODO(t2): CounterTest
// Return a callable that reports how many times it has itself been called,
// starting at 1. A second call to makeCounter() must produce an independent
// callable with its own separate count.
fun makeCounter(): () -> Int {
    TODO("t2: return a callable that counts its own invocations")
}

// TODO(t3): IndexOfFirstNegativeTest
// Return the index of the first negative number in `numbers`, or -1 if there
// isn't one.
fun indexOfFirstNegative(numbers: List<Int>): Int {
    TODO("t3: return the index of the first negative number, or -1")
}

// TODO(t4): FilterIsInstanceTest
// Return only the elements of `items` whose runtime type is T, keeping order.
inline fun <reified T> filterIsInstance2(items: List<Any?>): List<T> {
    TODO("t4: keep only the elements that are actually of type T")
}
