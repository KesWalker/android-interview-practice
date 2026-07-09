package di

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ContainerResolutionTest {

    @Test
    fun `resolves a registered binding`() {
        val container = Container()
        container.register("logger") { Logger() }

        val resolved = container.resolve("logger")

        assertTrue(resolved is Logger)
    }

    @Test
    fun `throws a clear error for an unregistered binding`() {
        val container = Container()
        container.register("logger") { Logger() }

        val error = assertThrows(IllegalStateException::class.java) {
            container.resolve("repository")
        }

        assertTrue(error.message!!.contains("repository"))
    }
}
