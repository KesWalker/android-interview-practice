package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

// Task 2: declaration-site covariance (`out`). You add the variance annotation
// to Producer AND declare widenToAnimalProducer yourself in Tasks.kt. The
// same-object assertions are the teeth: only `return dogs` passes them, and
// `return dogs` only compiles once Producer's T is covariant.
class T2WidenProducerTest {
    private fun widen(dogs: Producer<Dog>): Producer<Animal> {
        val m = findDeclaration("widenToAnimalProducer", 1)
            ?: notDeclaredYet(
                "t2: widenToAnimalProducer",
                "Declare a top-level function taking a Producer<Dog> and returning " +
                    "Producer<Animal> - by returning the SAME object, unwrapped. If " +
                    "the compiler refuses, Producer's declaration needs to promise " +
                    "that T only ever comes out.",
            )
        @Suppress("UNCHECKED_CAST")
        return m.call(dogs) as Producer<Animal>
    }

    @Test fun `produces the same dog through the animal-typed reference`() {
        val dogs: Producer<Dog> = Producer<Dog> { Dog("Rex") }
        val animals: Producer<Animal> = widen(dogs)
        assertEquals("Rex", animals.produce().name)
    }

    @Test fun `returns the very same producer, not a wrapper`() {
        val dogs: Producer<Dog> = Producer<Dog> { Dog("Fido") }
        val animals: Producer<Animal> = widen(dogs)
        assertSame(dogs, animals, "t2: a NEW producer came back. Covariance means the Producer<Dog> you were handed already IS a Producer<Animal> - return it as itself, no wrapping.")
        assertEquals("Fido", animals.produce().name)
    }

    @Test fun `different producers stay independent`() {
        val a: Producer<Animal> = widen(Producer<Dog> { Dog("A") })
        val b: Producer<Animal> = widen(Producer<Dog> { Dog("B") })
        assertEquals("A", a.produce().name)
        assertEquals("B", b.produce().name)
    }
}
