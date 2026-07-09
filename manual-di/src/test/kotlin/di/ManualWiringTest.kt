package di

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class ManualWiringTest {

    @Test
    fun `service and repository share the same logger instance`() {
        val service = buildUserService()

        assertSame(service.logger, service.repository.logger)
    }

    @Test
    fun `greeting flows through the whole graph and logs both steps`() {
        val service = buildUserService()

        val greeting = service.greet(7)

        assertEquals("Hello, user-7", greeting)
        assertEquals(listOf("find 7", "greet user-7"), service.logger.logs)
    }
}
