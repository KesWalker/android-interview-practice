package di

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class T6UserSessionFactoryTest {

    @Test
    fun `sessions built from different userIds get their own distinct id`() {
        val logger = Logger()
        val factory = UserSessionFactory(logger)

        val session7 = factory.create(7)
        val session9 = factory.create(9)

        assertEquals(7, session7.userId)
        assertEquals(9, session9.userId)
    }

    @Test
    fun `every session shares the one Logger the factory was built with`() {
        val logger = Logger()
        val factory = UserSessionFactory(logger)

        val session7 = factory.create(7)
        val session9 = factory.create(9)

        assertSame(logger, session7.logger)
        assertSame(logger, session9.logger)
    }

    @Test
    fun `each create call logs through the shared logger`() {
        val logger = Logger()
        val factory = UserSessionFactory(logger)

        factory.create(7).logger.log("session for 7")
        factory.create(9).logger.log("session for 9")

        assertEquals(listOf("session for 7", "session for 9"), logger.logs)
    }
}
