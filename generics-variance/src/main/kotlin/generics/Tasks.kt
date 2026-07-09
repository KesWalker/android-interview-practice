package generics

/**
 * Generics & Variance practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to make the
 * code work so its test goes GREEN. Run a single test class from the gutter
 * in Android Studio, or run them all with:
 *
 *     ./gradlew :generics-variance:test
 *
 * Rules of the game: every task has an idiomatic short solution.
 */

open class Animal(val name: String)
class Dog(name: String) : Animal(name)

fun interface Producer<out T> {
    fun produce(): T
}

// TODO(t1): FilterByTypeTest
// Keep only the elements of `items` that are actually a T, in their original order.
inline fun <reified T> filterByType(items: List<Any?>): List<T> {
    TODO("t1: keep only the elements of items that are a T")
}

// TODO(t2): WidenProducerTest
// Return `dogs` but typed as something that produces Animal values instead of Dog values.
fun widenToAnimalProducer(dogs: Producer<Dog>): Producer<Animal> {
    TODO("t2: return dogs, typed to produce Animal")
}

fun interface AnimalHandler<in T> {
    fun handle(item: T): String
}

// TODO(t3): NarrowHandlerTest
// Return `handler` but typed as something that can handle Dog values instead of Animal values.
fun narrowToDogHandler(handler: AnimalHandler<Animal>): AnimalHandler<Dog> {
    TODO("t3: return handler, typed to handle Dog")
}

class NumberBox<out T : Number>(val value: T)

// TODO(t4): SumBoxesTest
// Return the sum, as a Double, of every box's value in `boxes`.
fun sumBoxes(boxes: List<NumberBox<*>>): Double {
    TODO("t4: sum every box's value as a Double")
}
