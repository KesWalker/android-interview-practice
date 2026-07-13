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

// TODO(t1): T1SafeLengthTest
// Return the length of `text`, or 0 when it is null.
fun safeLength(text: String?): Int {
    TODO("t1: return text's length, or 0 if it is null (no !!)")
}

// TODO(t2): T2GreetingTest
// Return "Hello, <name>!" but fall back to "Hello, there!" when `name` is null
// or blank.
fun greetingFor(name: String?): String {
    TODO("t2: greet by name, or 'Hello, there!' when null/blank")
}

// TODO(t3): T3ValidAgesTest
// Turn a list of raw strings into the ages that actually parse as Int, dropping
// anything that doesn't. Input like ["21", "x", "34", ""] -> [21, 34].
fun validAges(raw: List<String>): List<Int> {
    TODO("t3: keep only the entries that parse as Int")
}

// TODO(t4): T4ShoutTest
// If `value` is a String, return it uppercased; for anything else (including
// null) return null. Do it without `is`/`if` casting.
fun shout(value: Any?): String? {
    TODO("t4: uppercase value only when it's a String, else null")
}

class SessionCache {
    var token: String? = null
}

// TODO(t5): T5TokenStatusTest
// SessionCache.token is a mutable var property. Return "Token: <token> (<n> chars)"
// when it's set, or "No token set" when it's null (no `!!`).
fun tokenStatus(cache: SessionCache): String {
    TODO("t5: describe the token when set, else 'No token set'")
}

// TODO(t6): T6DiscountPercentTest
// Given a nullable discount code like "20OFF", return the percent as an Int, or
// null when the code is null, malformed, or doesn't parse.
fun discountPercent(code: String?): Int? {
    TODO("t6: pull the percent out of a well-formed code, else null")
}

// TODO(t7): T7ValidatedConfigTest
// Return `config` unchanged when it's non-null; throw an IllegalArgumentException
// with the message "config is required" when it's null (no `!!`).
fun validatedConfig(config: String?): String {
    TODO("t7: return config, or throw IllegalArgumentException('config is required') when null")
}
