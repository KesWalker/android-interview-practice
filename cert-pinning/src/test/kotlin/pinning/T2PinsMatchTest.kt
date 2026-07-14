package pinning

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: a pin set matches if any cert anywhere in the chain matches any pin.
class T2PinsMatchTest {
    private val leaf = Certificate("leaf.example.com", "hash-leaf", "Intermediate CA", 0L, 10_000L)
    private val intermediate = Certificate("Intermediate CA", "hash-intermediate", "Root CA", 0L, 10_000L)
    private val root = Certificate("Root CA", "hash-root", "Root CA", 0L, 10_000L)
    private val chain = listOf(leaf, intermediate, root)

    @Test fun `pin matching the leaf matches`() {
        assertTrue(pinsMatch(chain, setOf("hash-leaf")))
    }

    @Test fun `pin matching an intermediate matches`() {
        assertTrue(pinsMatch(chain, setOf("hash-intermediate")))
    }

    @Test fun `pin set with no matching hash does not match`() {
        assertFalse(pinsMatch(chain, setOf("hash-unrelated")))
    }
}
