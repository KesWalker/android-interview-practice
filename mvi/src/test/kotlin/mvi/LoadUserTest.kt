package mvi

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

private class FakeUserRepository(
    private val result: Result<String>,
    private val delayMs: Long = 100
) : UserRepository {
    override suspend fun fetchUser(id: String): String {
        delay(delayMs)
        return result.getOrThrow()
    }
}

// Task 3: side effects live outside the reducer; their outcome re-enters as an intent.
class LoadUserTest {
    @Test fun `marks loading true while the fetch is still in flight`() = runTest {
        val store = UserStore()
        val repo = FakeUserRepository(Result.success("Ada"))
        launch { loadUser(store, repo, "1") }
        runCurrent()
        assertEquals(true, store.state.value.isLoading)
        assertNull(store.state.value.user)
    }

    @Test fun `folds a successful fetch into user and clears loading`() = runTest {
        val store = UserStore()
        val repo = FakeUserRepository(Result.success("Ada"))
        loadUser(store, repo, "1")
        assertEquals(UserState(isLoading = false, user = "Ada", error = null), store.state.value)
    }

    @Test fun `folds a failed fetch into error and clears loading`() = runTest {
        val store = UserStore()
        val repo = FakeUserRepository(Result.failure(RuntimeException("network down")))
        loadUser(store, repo, "1")
        assertEquals(UserState(isLoading = false, user = null, error = "network down"), store.state.value)
    }
}
