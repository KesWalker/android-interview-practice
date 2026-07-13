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

// TODO(t5): T5RotatingRefreshTokenStoreTest
// Rotate the refresh token on every successful refresh, invalidating the old
// one; if an already-rotated (stale) refresh token is presented again, treat it
// as reuse and revoke the whole token family instead of accepting it.
class TokenReuseDetectedException : IllegalStateException("Refresh token reuse detected")

class RotatingRefreshTokenStore(
    initialToken: String,
    private val generateToken: () -> String
) {
    fun refresh(presentedToken: String): String {
        TODO("t5: rotate on the currently-valid token, otherwise revoke the family and throw TokenReuseDetectedException")
    }

    fun isFamilyRevoked(): Boolean {
        TODO("t5: report whether reuse has revoked the whole token family")
    }
}

// TODO(t6): T6AttemptRefreshTest
// Attempt a refresh; a 401 means the refresh token itself was rejected, so
// report SessionExpired instead of retrying, while any other failure should
// propagate so the caller can decide whether to retry.
class HttpException(val code: Int) : Exception("HTTP $code")

sealed interface RefreshOutcome {
    data class Refreshed(val token: String) : RefreshOutcome
    data object SessionExpired : RefreshOutcome
}

suspend fun attemptRefresh(refresh: suspend () -> String): RefreshOutcome {
    TODO("t6: return Refreshed on success, SessionExpired on a 401, and let every other failure propagate")
}

// TODO(t7): T7RedactForLoggingTest
// Prepare request headers for a debug log line: mask the Authorization header's
// value so a bearer token never ends up in Logcat or a crash report.
fun redactForLogging(headers: Map<String, String>): Map<String, String> {
    TODO("t7: replace the Authorization value (any casing) with REDACTED, leaving every other header alone")
}
