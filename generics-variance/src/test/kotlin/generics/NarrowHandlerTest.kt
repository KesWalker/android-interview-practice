package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: declaration-site contravariance (`in`).
class NarrowHandlerTest {
    @Test fun `handles a dog via the animal handler`() {
        val handler: AnimalHandler<Animal> = AnimalHandler<Animal> { animal -> "handled ${animal.name}" }
        val dogHandler: AnimalHandler<Dog> = narrowToDogHandler(handler)
        assertEquals("handled Rex", dogHandler.handle(Dog("Rex")))
    }

    @Test fun `passes the dog's own data through`() {
        val handler: AnimalHandler<Animal> = AnimalHandler<Animal> { animal -> animal.name.uppercase() }
        val dogHandler: AnimalHandler<Dog> = narrowToDogHandler(handler)
        assertEquals("FIDO", dogHandler.handle(Dog("Fido")))
    }

    @Test fun `different handlers stay independent`() {
        val greet: AnimalHandler<Dog> = narrowToDogHandler(AnimalHandler<Animal> { "hi ${it.name}" })
        val bark: AnimalHandler<Dog> = narrowToDogHandler(AnimalHandler<Animal> { "${it.name} says woof" })
        assertEquals("hi Rex", greet.handle(Dog("Rex")))
        assertEquals("Rex says woof", bark.handle(Dog("Rex")))
    }
}
