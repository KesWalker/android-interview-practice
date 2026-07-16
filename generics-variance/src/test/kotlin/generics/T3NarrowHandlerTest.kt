package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

// Task 3: declaration-site contravariance (`in`). You add the variance
// annotation to AnimalHandler AND declare narrowToDogHandler yourself in
// Tasks.kt. Same-object assertions again: only `return handler` passes, and
// that only compiles once AnimalHandler's T is contravariant.
class T3NarrowHandlerTest {
    private fun narrow(handler: AnimalHandler<Animal>): AnimalHandler<Dog> {
        val m = findDeclaration("narrowToDogHandler", 1)
            ?: notDeclaredYet(
                "t3: narrowToDogHandler",
                "Declare a top-level function taking an AnimalHandler<Animal> and " +
                    "returning AnimalHandler<Dog> - by returning the SAME object, " +
                    "unwrapped. If the compiler refuses, AnimalHandler's declaration " +
                    "needs to promise that T only ever goes in.",
            )
        @Suppress("UNCHECKED_CAST")
        return m.call(handler) as AnimalHandler<Dog>
    }

    @Test fun `handles a dog via the animal handler`() {
        val handler: AnimalHandler<Animal> = AnimalHandler<Animal> { animal -> "handled ${animal.name}" }
        val dogHandler: AnimalHandler<Dog> = narrow(handler)
        assertEquals("handled Rex", dogHandler.handle(Dog("Rex")))
    }

    @Test fun `returns the very same handler, not a wrapper`() {
        val handler: AnimalHandler<Animal> = AnimalHandler<Animal> { animal -> animal.name.uppercase() }
        val dogHandler: AnimalHandler<Dog> = narrow(handler)
        assertSame(handler, dogHandler, "t3: a NEW handler came back. Contravariance means the AnimalHandler<Animal> you were handed already IS an AnimalHandler<Dog> - return it as itself, no wrapping.")
        assertEquals("FIDO", dogHandler.handle(Dog("Fido")))
    }

    @Test fun `different handlers stay independent`() {
        val greet: AnimalHandler<Dog> = narrow(AnimalHandler<Animal> { "hi ${it.name}" })
        val bark: AnimalHandler<Dog> = narrow(AnimalHandler<Animal> { "${it.name} says woof" })
        assertEquals("hi Rex", greet.handle(Dog("Rex")))
        assertEquals("Rex says woof", bark.handle(Dog("Rex")))
    }
}
