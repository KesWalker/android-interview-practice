package pinning

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: pinning only the leaf is fragile, it dies the moment the leaf rotates.
class T3IsFragilePinningTest {
    private val leaf = Certificate("leaf.example.com", "hash-leaf", "Intermediate CA", 0L, 10_000L)
    private val intermediate = Certificate("Intermediate CA", "hash-intermediate", "Root CA", 0L, 10_000L)
    private val root = Certificate("Root CA", "hash-root", "Root CA", 0L, 10_000L)
    private val chain = listOf(leaf, intermediate, root)

    @Test fun `pinning only the leaf hash is fragile`() {
        assertTrue(isFragilePinning(chain, setOf("hash-leaf")))
    }

    @Test fun `pinning an intermediate is not fragile`() {
        assertFalse(isFragilePinning(chain, setOf("hash-intermediate")))
    }

    @Test fun `pinning leaf and intermediate together is not fragile`() {
        assertFalse(isFragilePinning(chain, setOf("hash-leaf", "hash-intermediate")))
    }

    @Test fun `no matching pin at all is not fragile`() {
        assertFalse(isFragilePinning(chain, setOf("hash-unrelated")))
    }
}
