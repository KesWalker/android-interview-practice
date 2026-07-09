package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: declaration-site covariance (`out`).
class WidenProducerTest {
    @Test fun `produces the same dog through the animal-typed reference`() {
        val dogs: Producer<Dog> = Producer<Dog> { Dog("Rex") }
        val animals: Producer<Animal> = widenToAnimalProducer(dogs)
        assertEquals("Rex", animals.produce().name)
    }

    @Test fun `the widened reference is still a working Producer of Animal`() {
        val dogs: Producer<Dog> = Producer<Dog> { Dog("Fido") }
        val animals: Producer<Animal> = widenToAnimalProducer(dogs)
        val name: String = animals.produce().name
        assertEquals("Fido", name)
    }

    @Test fun `different producers stay independent`() {
        val a: Producer<Animal> = widenToAnimalProducer(Producer<Dog> { Dog("A") })
        val b: Producer<Animal> = widenToAnimalProducer(Producer<Dog> { Dog("B") })
        assertEquals("A", a.produce().name)
        assertEquals("B", b.produce().name)
    }
}
