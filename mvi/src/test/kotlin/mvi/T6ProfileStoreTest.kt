package mvi

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private class FakeProfileUserRepository(
    private val name: String,
    private val delayMs: Long = 0
) : ProfileUserRepository {
    override suspend fun fetchUser(): String {
        delay(delayMs)
        return name
    }
}

private class FakeProfileSettingsRepository(
    private val darkMode: Boolean,
    private val delayMs: Long = 0
) : ProfileSettingsRepository {
    override suspend fun fetchSettings(): Boolean {
        delay(delayMs)
        return darkMode
    }
}

// Task 6: folding two independent async results into one state via a single intent.
class T6ProfileStoreTest {
    @Test fun `combines the user and settings results once both resolve`() = runTest {
        val store = ProfileStore()
        loadProfile(store, FakeProfileUserRepository("Ada"), FakeProfileSettingsRepository(true))

        assertEquals(ProfileState(userName = "Ada", darkMode = true), store.state.value)
    }

    @Test fun `works the same when the user fetch resolves after settings`() = runTest {
        val store = ProfileStore()
        loadProfile(
            store,
            FakeProfileUserRepository("Grace", delayMs = 100),
            FakeProfileSettingsRepository(false, delayMs = 10)
        )

        assertEquals(ProfileState(userName = "Grace", darkMode = false), store.state.value)
    }

    @Test fun `works the same when settings resolves after the user`() = runTest {
        val store = ProfileStore()
        loadProfile(
            store,
            FakeProfileUserRepository("Grace", delayMs = 10),
            FakeProfileSettingsRepository(false, delayMs = 100)
        )

        assertEquals(ProfileState(userName = "Grace", darkMode = false), store.state.value)
    }
}
