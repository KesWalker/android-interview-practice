package stability

/**
 * Compose stability inference practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :stability-inference:test
 */

/** Whether the Compose compiler can treat a type as stable. */
enum class Stability { STABLE, UNSTABLE }

/** A `@Stable` or `@Immutable` annotation the author put on a type by hand. */
enum class StabilityAnnotation { STABLE, IMMUTABLE }

/** One field of a class-like type, and whether it's a `val` or a `var`. */
data class FieldDescriptor(
    val name: String,
    val type: TypeDescriptor,
    val isVar: Boolean = false
)

/** Describes a type the way the Compose compiler reasons about it. */
sealed class TypeDescriptor {
    // Primitives Compose always treats as stable: Int, Long, Float, Double,
    // Boolean, Char, String, Unit.
    data class Primitive(val name: String) : TypeDescriptor()

    // A concrete class the compiler can inspect field-by-field.
    data class ClassType(
        val name: String,
        val fields: List<FieldDescriptor>,
        val explicitAnnotation: StabilityAnnotation? = null
    ) : TypeDescriptor()

    // An interface-typed field or param, e.g. List<T>, Map<K, V>, or any
    // user-defined interface. The compiler cannot see every implementation,
    // so it can't assume the runtime instance is immutable.
    data class InterfaceType(
        val name: String,
        val explicitAnnotation: StabilityAnnotation? = null
    ) : TypeDescriptor()
}

// TODO(t1): T1InferStabilityTest
// Infer whether `type` is stable, the way the Compose compiler would:
//  - Primitive is always stable.
//  - InterfaceType is unstable, UNLESS it carries an explicit
//    StabilityAnnotation (List is unstable because it's an interface, the
//    compiler can't assume every implementation is immutable).
//  - ClassType is stable if it carries an explicit StabilityAnnotation
//    (trust the annotation, ignore its fields). Otherwise it's stable only
//    if it has no `var` fields (a `var` can change without Compose being
//    able to observe it) AND every field's type is itself stable.
fun inferStability(type: TypeDescriptor): Stability {
    TODO("t1: recursively classify Primitive / InterfaceType / ClassType as STABLE or UNSTABLE")
}

// TODO(t2): T2IsSkippableTest
// A composable is skippable only if every one of its parameter types is
// stable. A composable with zero parameters is trivially skippable.
fun isSkippable(paramTypes: List<TypeDescriptor>): Boolean {
    TODO("t2: true only if every paramType infers as STABLE")
}

// TODO(t3): T3StrongSkippingEqualsTest
// Strong Skipping compares old and new argument values differently
// depending on the param's inferred stability: stable params compare by
// `==` (structural equality), unstable params compare by `===` (reference
// identity), since the compiler can't trust an unstable type's equals().
fun strongSkippingEquals(type: TypeDescriptor, old: Any?, new: Any?): Boolean {
    TODO("t3: use == when type is stable, === when type is unstable")
}

// TODO(t4): T4LambdaIsStableTest
// A lambda's stability is the stability of everything it closes over: it's
// stable only if every one of `capturedTypes` is itself stable. A lambda
// that captures nothing is always stable.
fun lambdaIsStable(capturedTypes: List<TypeDescriptor>): Boolean {
    TODO("t4: true only if every captured type infers as STABLE")
}

// TODO(t5): T5ShouldRecomposeTest
// Decide whether a composable call needs to recompose given its param
// types and its old vs. new argument values (parallel lists, same order,
// same length as paramTypes). If the composable isn't skippable at all
// (see isSkippable), always recompose. Otherwise recompose only if
// strongSkippingEquals says at least one param's old and new values differ.
fun shouldRecompose(paramTypes: List<TypeDescriptor>, oldArgs: List<Any?>, newArgs: List<Any?>): Boolean {
    TODO("t5: not skippable -> true; else true iff any param fails strongSkippingEquals")
}

// TODO(t6): T6RecompositionCountTest
// `argsSequence` is a series of argument lists passed to the same
// composable call site across successive recompositions of its parent (the
// first entry is the initial composition, always counted). Count how many
// of those calls actually recompose, using shouldRecompose against the
// immediately preceding entry.
fun countRecompositions(paramTypes: List<TypeDescriptor>, argsSequence: List<List<Any?>>): Int {
    TODO("t6: count the initial call plus every later entry where shouldRecompose is true against the previous entry")
}
