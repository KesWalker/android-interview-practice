package extensions

/**
 * Extension Functions & Properties practice.
 *
 * Tasks 1-4 are DECLARED BY YOU, from scratch: the whole point of extensions is
 * the declaration site (the receiver type, the operator modifier, the companion
 * receiver), so nothing is stubbed for those - you write the full declaration
 * yourself, top-level in this file. Their tests find your declaration by
 * reflection once it exists, and until then they fail with a pointer to what's
 * missing. Tasks 5-6 flip it around: the extensions are already written, and
 * the lesson is what happens at the CALL SITE.
 *
 * Run a single test class from the gutter in Android Studio, or all of them:
 *
 *     ./gradlew :extensions:test
 *
 * Rules of the game: no `!!`. Every task has an idiomatic short solution.
 */

// TODO(t1): T1DisplayNameTest
// Declare an extension FUNCTION named `displayNameOrUnknown` on a NULLABLE
// String receiver, returning String: the string trimmed, or "Unknown" when the
// receiver is null or blank. Write the whole declaration yourself, right here.

// TODO(t2): T2WordCountTest
// Declare an extension PROPERTY named `wordCount` on String, of type Int: the
// number of whitespace-separated words, or 0 when the string is blank.
// Extension properties can't have a backing field - think about what that
// forces the declaration to look like.

data class Point(val x: Int, val y: Int)

// TODO(t3): T3PointIndexingTest
// Point is a plain data class with no indexing of its own. Declare something
// top-level that makes `point[0]` return x and `point[1]` return y, with any
// other index throwing IndexOutOfBoundsException.

class Meters(val value: Double) {
    companion object
}

// TODO(t4): T4MetersParseTest
// Meters has no factory. Declare an extension named `parse` so that
// `Meters.parse("5.5m")` (called on the TYPE, not an instance) returns
// Meters(5.5), and returns null for anything that doesn't match "<number>m".
// Meters already declares an empty companion object - that's your receiver.

open class Shape
class Circle : Shape()

fun Shape.kind(): String = "shape"
fun Circle.kind(): String = "circle"

// TODO(t5): T5KindsOfTest
// Return each shape's `.kind()`, one per element, in order, given a list of
// Shape (all of them actually Circle underneath).
fun kindsOf(shapes: List<Shape>): List<String> {
    TODO("t5: return each shape's kind(), in order")
}

class Greeter {
    fun greet(): String = "member"
}

fun Greeter.greet(): String = "extension"

// TODO(t6): T6GreetTwiceTest
// Call `g.greet()` twice and concatenate the results, given a Greeter that
// declares both a member greet() and an extension greet().
fun greetTwice(g: Greeter): String {
    TODO("t6: call g.greet() twice and concatenate the results")
}
