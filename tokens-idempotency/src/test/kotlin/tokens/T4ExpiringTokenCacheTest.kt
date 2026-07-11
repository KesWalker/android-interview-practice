package tokens

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

// Task 4: expiry-aware caching that swaps only on a successful refresh.
class T4ExpiringTokenCacheTest {
    @Test fun `fetches and caches a token when none is cached yet`() = runTest {
        val cache = ExpiringTokenCache { Token("token-1", expiresAtEpochMillis = 1_000) }
        assertEquals("token-1", cache.getToken(nowEpochMillis = 0))
    }

    @Test fun `returns the cached token without refreshing again while still valid`() = runTest {
        var refreshCount = 0
        val cache = ExpiringTokenCache {
            refreshCount++
            Token("token-$refreshCount", expiresAtEpochMillis = 1_000)
        }
        cache.getToken(nowEpochMillis = 0)
        val second = cache.getToken(nowEpochMillis = 500)
        assertEquals("token-1", second)
        assertEquals(1, refreshCount)
    }

    @Test fun `refreshes again once the cached token has expired`() = runTest {
        var refreshCount = 0
        val cache = ExpiringTokenCache {
            refreshCount++
            Token("token-$refreshCount", expiresAtEpochMillis = 1_000L * refreshCount)
        }
        cache.getToken(nowEpochMillis = 0)
        val second = cache.getToken(nowEpochMillis = 1_500)
        assertEquals("token-2", second)
        assertEquals(2, refreshCount)
    }

    @Test fun `a failed refresh propagates and leaves the previously cached token untouched`() = runTest {
        var shouldFail = false
        val cache = ExpiringTokenCache {
            if (shouldFail) error("network down") else Token("token-1", expiresAtEpochMillis = 1_000)
        }
        cache.getToken(nowEpochMillis = 0)
        val before = cache.peek()

        shouldFail = true
        val thrown = try {
            cache.getToken(nowEpochMillis = 2_000)
            null
        } catch (e: IllegalStateException) {
            e
        }

        assertNotNull(thrown)
        assertEquals(before, cache.peek())
    }
}
