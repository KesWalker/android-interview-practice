package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 4: gate SECRET-class decryption behind a recent-enough auth.
class T4DecryptSecretTest {
    @Test fun `public data decrypts with no auth at all`() {
        val result = decryptSecret(
            Sensitivity.PUBLIC,
            AuthState(lastAuthenticatedAtMillis = null),
            nowMillis = 10_000L,
            authTimeoutMillis = 30_000L
        ) { "theme-value" }
        assertEquals("theme-value", result)
    }

    @Test fun `secret data decrypts when the auth is within the timeout`() {
        val result = decryptSecret(
            Sensitivity.SECRET,
            AuthState(lastAuthenticatedAtMillis = 10_000L),
            nowMillis = 20_000L,
            authTimeoutMillis = 30_000L
        ) { "token-value" }
        assertEquals("token-value", result)
    }

    @Test fun `secret data at exactly the timeout boundary is still allowed`() {
        val result = decryptSecret(
            Sensitivity.SECRET,
            AuthState(lastAuthenticatedAtMillis = 10_000L),
            nowMillis = 40_000L,
            authTimeoutMillis = 30_000L
        ) { "token-value" }
        assertEquals("token-value", result)
    }

    @Test fun `secret data past the timeout is denied`() {
        assertThrows(SecretAccessDeniedException::class.java) {
            decryptSecret(
                Sensitivity.SECRET,
                AuthState(lastAuthenticatedAtMillis = 10_000L),
                nowMillis = 40_001L,
                authTimeoutMillis = 30_000L
            ) { "token-value" }
        }
    }

    @Test fun `secret data with no prior auth is denied`() {
        assertThrows(SecretAccessDeniedException::class.java) {
            decryptSecret(
                Sensitivity.SECRET,
                AuthState(lastAuthenticatedAtMillis = null),
                nowMillis = 40_001L,
                authTimeoutMillis = 30_000L
            ) { "token-value" }
        }
    }
}
