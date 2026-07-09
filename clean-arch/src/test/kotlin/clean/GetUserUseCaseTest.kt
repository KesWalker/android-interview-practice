package clean

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class GetUserUseCaseTest {

    private class FakeUserRepository(private val users: Map<String, User>) : UserRepository {
        override suspend fun getUser(id: String): User? = users[id]
    }

    @Test
    fun `returns the matching user`() = runTest {
        val repository = FakeUserRepository(mapOf("u1" to User("u1", "Ada")))
        val useCase = GetUserUseCase(repository)

        val result = useCase("u1")

        assertEquals(User("u1", "Ada"), result)
    }

    @Test
    fun `returns null when the user is missing`() = runTest {
        val repository = FakeUserRepository(emptyMap())
        val useCase = GetUserUseCase(repository)

        val result = useCase("missing")

        assertNull(result)
    }
}
