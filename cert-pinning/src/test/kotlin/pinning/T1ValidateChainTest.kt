package pinning

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: a chain is valid when signing links up and nothing has expired.
class T1ValidateChainTest {
    private val root = Certificate(
        subject = "Root CA",
        spkiHash = "hash-root",
        issuerSubject = "Root CA",
        notBefore = 0L,
        notAfter = 20_000L
    )
    private val intermediate = Certificate(
        subject = "Intermediate CA",
        spkiHash = "hash-intermediate",
        issuerSubject = "Root CA",
        notBefore = 0L,
        notAfter = 10_000L
    )
    private val leaf = Certificate(
        subject = "leaf.example.com",
        spkiHash = "hash-leaf",
        issuerSubject = "Intermediate CA",
        notBefore = 1_000L,
        notAfter = 5_000L
    )
    private val chain = listOf(leaf, intermediate, root)

    @Test fun `well formed chain is valid within every certs window`() {
        assertTrue(validateChain(chain, atTime = 2_000L))
    }

    @Test fun `broken signing link is invalid`() {
        val strayLeaf = leaf.copy(issuerSubject = "Some Other CA")
        assertFalse(validateChain(listOf(strayLeaf, intermediate, root), atTime = 2_000L))
    }

    @Test fun `expired certificate is invalid`() {
        assertFalse(validateChain(chain, atTime = 6_000L))
    }

    @Test fun `empty chain is never valid`() {
        assertFalse(validateChain(emptyList(), atTime = 2_000L))
    }
}
