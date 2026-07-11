package tokens

import kotlinx.coroutines.CoroutineScope

/**
 * Auth Tokens & Idempotency practice.
 *
 * Each class below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :tokens-idempotency:test
 */

// TODO(t1): T1IdempotencyStoreTest
// Run `operation` only the first time a given key is seen; a repeat call with
// the same key returns the stored result without running it again.
class IdempotencyStore<T> {
    fun execute(key: String, operation: () -> T): T {
        TODO("t1: run operation once per key, returning the stored result for repeat keys")
    }
}

// TODO(t2): T2SingleFlightTokenManagerTest
// Serve a cached token; when none is cached, only one concurrent caller should
// actually run `refresh`, and every other caller should get its result.
class SingleFlightTokenManager(private val refresh: suspend () -> String) {
    suspend fun getToken(): String {
        TODO("t2: return the cached token, or have exactly one caller refresh and share the result")
    }
}

// TODO(t3): T3SharedRefreshTokenManagerTest
// Share one in-flight refresh across every waiting caller, run under `scope`
// (not the caller's own job) so cancelling one caller can't cancel the rest.
class SharedRefreshTokenManager(
    private val scope: CoroutineScope,
    private val refresh: suspend () -> String
) {
    suspend fun getToken(): String {
        TODO("t3: share one in-flight refresh across callers, owned so cancelling a caller can't cancel it")
    }
}

// TODO(t4): T4ExpiringTokenCacheTest
// Cache a Token until it expires; a failed refresh must propagate and must
// leave the previously cached token untouched (swap only on success).
data class Token(val value: String, val expiresAtEpochMillis: Long)

class ExpiringTokenCache(private val refresh: suspend () -> Token) {
    suspend fun getToken(nowEpochMillis: Long): String {
        TODO("t4: return the cached token while valid, otherwise refresh, cache, and return the fresh one")
    }

    fun peek(): Token? {
        TODO("t4: expose whatever token is currently cached, or null if none has been fetched yet")
    }
}
