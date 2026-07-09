package extensions

/**
 * Extension Functions & Properties practice.
 *
 * Each declaration below is unwritten and has a matching test in src/test that
 * is currently RED. Your job, one task at a time, is to write the extension
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :extensions:test
 *
 * Rules of the game: no `!!`. Every task has an idiomatic short solution.
 */

// TODO(t1): DisplayNameTest
// Return this string trimmed, or "Unknown" when the receiver is null or blank.
fun String?.displayNameOrUnknown(): String {
    TODO("t1: trim this, or return \"Unknown\" when it's null or blank")
}

// TODO(t2): WordCountTest
// Number of whitespace-separated words in this string, or 0 when it's blank.
val String.wordCount: Int
    get() = TODO("t2: count whitespace-separated words, 0 when blank")

data class Point(val x: Int, val y: Int)

// TODO(t3): PointIndexingTest
// Index 0 yields x, index 1 yields y, any other index is invalid.
operator fun Point.get(index: Int): Int {
    TODO("t3: return x for index 0, y for index 1, else throw IndexOutOfBoundsException")
}

class Meters(val value: Double) {
    companion object
}

// TODO(t4): MetersParseTest
// Parse strings shaped like "5.5m" into a Meters value; anything else is null.
fun Meters.Companion.parse(raw: String): Meters? {
    TODO("t4: parse \"<number>m\" into Meters, or null when it doesn't match")
}
