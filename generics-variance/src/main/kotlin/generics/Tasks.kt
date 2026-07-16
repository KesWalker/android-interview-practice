package generics

/**
 * Generics & Variance practice.
 *
 * Tasks 2, 3 and 5 are DECLARED BY YOU, from scratch - variance annotations
 * and generic bounds live in declarations, so those aren't stubbed. Their
 * tests find your declaration by reflection once it exists, and until then
 * they fail with a pointer to what's missing. Tasks 1, 4 and 6 keep their
 * signatures (reified functions can't even be called by reflection - which is
 * itself a lesson about what inlining really does) and you write the bodies.
 *
 * Run a single test class from the gutter in Android Studio, or all of them:
 *
 *     ./gradlew :generics-variance:test
 *
 * Rules of the game: every task has an idiomatic short solution.
 */

open class Animal(val name: String)
class Dog(name: String) : Animal(name)

// NOTE for t2: Producer is declared INVARIANT right now - that's part of the
// task, not an accident.
fun interface Producer<T> {
    fun produce(): T
}

// TODO(t1): T1FilterByTypeTest
// Keep only the elements of `items` that are actually a T, in their original order.
inline fun <reified T> filterByType(items: List<Any?>): List<T> {
    TODO("t1: keep only the elements of items that are a T")
}

// TODO(t2): T2WidenProducerTest
// A Producer<Dog> should already BE a usable Producer<Animal> - every value it
// hands out is an Animal. Two steps, both yours: (1) change Producer's
// declaration above so the compiler agrees (T is only ever produced, never
// consumed - which variance annotation says so?), then (2) declare a function
// `widenToAnimalProducer(dogs: Producer<Dog>): Producer<Animal>` that returns
// `dogs` ITSELF. The test checks the very same object comes back: if you had
// to wrap it in a new Producer, variance isn't doing the work yet.

// NOTE for t3: AnimalHandler is also declared INVARIANT right now - same deal.
fun interface AnimalHandler<T> {
    fun handle(item: T): String
}

// TODO(t3): T3NarrowHandlerTest
// The mirror image: anything that can handle ANY Animal can obviously handle a
// Dog. Change AnimalHandler's declaration so the compiler agrees (T is only
// ever consumed - which annotation?), then declare
// `narrowToDogHandler(handler: AnimalHandler<Animal>): AnimalHandler<Dog>`
// that returns `handler` ITSELF. Same-object check applies here too.

class NumberBox<out T : Number>(val value: T)

// TODO(t4): T4SumBoxesTest
// Return the sum, as a Double, of every box's value in `boxes`.
fun sumBoxes(boxes: List<NumberBox<*>>): Double {
    TODO("t4: sum every box's value as a Double")
}

// TODO(t5): T5MaxByNaturalOrderTest
// Declare, from scratch, a generic function `maxByNaturalOrder(a, b)` that
// returns whichever argument is greater - for a type parameter that must be
// BOTH a CharSequence AND Comparable to itself. One upper bound fits after
// the type parameter; two need a different piece of syntax.

// TODO(t6): T6RunTwiceWrappedTest
// Return a Runnable that calls `block` twice when it's run, given `block` as a
// crossinline inline-function parameter.
inline fun runTwiceWrapped(crossinline block: () -> Unit): Runnable {
    TODO("t6: return a Runnable that calls block twice when run")
}
