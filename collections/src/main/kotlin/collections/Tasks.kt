package collections

/**
 * Kotlin Collections & stdlib practice.
 *
 * Each declaration below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it idiomatically so its test goes GREEN. Run a single test class
 * from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :collections:test
 */

class Cart {
    private val _items = mutableListOf<String>()

    fun add(item: String) {
        _items.add(item)
    }

    // TODO(t1): T1CartViewTest
    // Expose the cart's current items, without copying the underlying list.
    val items: List<String>
        get() = TODO("t1: return the cart's items without copying them into a new list")
}

// TODO(t2): T2TotalValueTest
// Add up the numeric value of every item in `values` and return it as a Double.
fun totalValue(values: List<Number>): Double {
    TODO("t2: return the sum of every value in the list, as a Double")
}

// TODO(t3): T3SplitByLengthTest
// Split `words` into two lists: those with length >= minLength, then the
// rest, each keeping its original order.
fun splitByLength(words: List<String>, minLength: Int): Pair<List<String>, List<String>> {
    TODO("t3: return (words with length >= minLength) paired with (the rest)")
}

// TODO(t4): T4FirstSquareOverTest
// Return the first perfect square (1, 4, 9, 16, ...) that is strictly
// greater than `threshold`, without pre-computing every square up to it.
fun firstSquareOver(threshold: Int): Int {
    TODO("t4: return the first perfect square strictly greater than threshold")
}
