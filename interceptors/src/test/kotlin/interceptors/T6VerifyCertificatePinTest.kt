package interceptors

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: certificate pinning validation.
class T6VerifyCertificatePinTest {
    @Test fun `a host with no pin configured is allowed silently`() {
        assertDoesNotThrow {
            verifyCertificatePin("unpinned.example.com", "hash-anything", emptyMap())
        }
    }

    @Test fun `a pinned host whose hash matches one of its accepted pins is allowed`() {
        val pins = mapOf("api.example.com" to setOf("primary-hash", "backup-hash"))

        assertDoesNotThrow {
            verifyCertificatePin("api.example.com", "backup-hash", pins)
        }
    }

    @Test fun `a pinned host whose hash matches none of them throws`() {
        val pins = mapOf("api.example.com" to setOf("primary-hash", "backup-hash"))

        assertThrows(CertificatePinningException::class.java) {
            verifyCertificatePin("api.example.com", "attacker-hash", pins)
        }
    }
}
