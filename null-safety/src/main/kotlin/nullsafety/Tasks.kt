package nullsafety

/**
 * Null Safety practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to make the
 * code null-safe so its test goes GREEN. Run a single test class from the gutter
 * in Android Studio, or run them all with:
 *
 *     ./gradlew :null-safety:test
 *
 * Rules of the game: no `!!`. Every task has an idiomatic one-liner solution.
 */

// TODO(t1): SafeLengthTest
// Return the length of `text`, or 0 when it is null. Hint: a safe call plus elvis.
fun safeLength(text: String?): Int {
    TODO("t1: return text's length, or 0 if it is null (no !!)")
}

// TODO(t2): GreetingTest
// Return "Hello, <name>!" but fall back to "Hello, there!" when `name` is null
// or blank. Hint: elvis with a default; String.isNullOrBlank() helps.
fun greetingFor(name: String?): String {
    TODO("t2: greet by name, or 'Hello, there!' when null/blank")
}

// TODO(t3): ValidAgesTest
// Turn a list of raw strings into the ages that actually parse as Int, dropping
// anything that doesn't. Input like ["21", "x", "34", ""] -> [21, 34].
// Hint: mapNotNull { it.toIntOrNull() }.
fun validAges(raw: List<String>): List<Int> {
    TODO("t3: keep only the entries that parse as Int")
}

// TODO(t4): ShoutTest
// If `value` is a String, return it uppercased; for anything else (including
// null) return null. Do it without `is`/`if` casting. Hint: safe cast `as?`.
fun shout(value: Any?): String? {
    TODO("t4: uppercase value only when it's a String, else null")
}
