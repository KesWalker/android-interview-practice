package nothingany

/**
 * Nothing & Any practice.
 *
 * Each function below is unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * make the test go GREEN. Run a single test class from the gutter
 * in Android Studio, or run them all with:
 *
 *     ./gradlew :nothing-any:test
 */

// -- Models used by the tasks --

sealed class Outcome<out T> {
    data class Success<out T>(val value: T) : Outcome<T>()
    data class Failure(val error: String) : Outcome<Nothing>()
}

// -- Tasks --

// TODO(t1): T1FailWithTest
// Write a function that always throws IllegalStateException with the given
// message. The return type should tell the compiler this function never returns.
fun failWith(message: String): Nothing {
    TODO("t1: throw IllegalStateException(message), return type Nothing")
}

// TODO(t2): T2RequireNotBlankTest
// Return the string if non-null and not blank, otherwise throw
// IllegalArgumentException("must not be blank"). Use an elvis expression.
fun requireNotBlank(input: String?): String {
    TODO("t2: return non-blank input, or throw via elvis")
}

// TODO(t3): T3DescribeTypeTest
// Given Any, return "text: <value>" for String, "number: <value>" for Int,
// "decimal: <value>" for Double, "other: <value>" for anything else.
fun describeType(value: Any): String {
    TODO("t3: when on Any with is checks + else")
}

// TODO(t4): T4UnwrapOrThrowTest
// Return the value inside Success, or throw RuntimeException(error) for Failure.
fun <T> unwrapOrThrow(outcome: Outcome<T>): T {
    TODO("t4: exhaustive when on sealed Outcome, throw in Failure branch")
}

// TODO(t5): T5SafeEmptyTest
// Return an empty List<T>. Call the no-argument emptyList() and let Nothing's
// subtyping do the work -- do NOT pass an explicit type argument.
fun <T> safeEmpty(): List<T> {
    TODO("t5: return emptyList() without type argument")
}

// TODO(t6): T6AsAnyTest
// Count how many elements in a List<Any> are Strings.
fun countStrings(items: List<Any>): Int {
    TODO("t6: count items that are Strings using is check")
}
