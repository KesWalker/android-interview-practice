package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: a keystore-alike where the key never leaves the store.
class T3KeyStoreAlikeTest {
    @Test fun `encrypt then decrypt round-trips to the original plaintext`() {
        val store = KeyStoreAlike()
        val ciphertext = store.encrypt("session-token", "super-secret-value")
        assertEquals("super-secret-value", store.decrypt("session-token", ciphertext))
    }

    @Test fun `ciphertext is not the plaintext`() {
        val store = KeyStoreAlike()
        val ciphertext = store.encrypt("session-token", "super-secret-value")
        assertNotEquals("super-secret-value", ciphertext)
    }

    @Test fun `decrypting with an alias that has no key throws`() {
        val store = KeyStoreAlike()
        assertThrows(IllegalStateException::class.java) {
            store.decrypt("never-used-alias", "whatever")
        }
    }

    @Test fun `decrypting with the wrong alias's key does not recover the plaintext`() {
        val store = KeyStoreAlike()
        val ciphertext = store.encrypt("token-a", "super-secret-value")
        store.encrypt("token-b", "unrelated")
        assertNotEquals("super-secret-value", store.decrypt("token-b", ciphertext))
    }
}
