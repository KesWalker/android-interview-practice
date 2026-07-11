package tokens

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: refresh-token rotation with reuse detection.
class T5RotatingRefreshTokenStoreTest {
    @Test fun `refreshing with the current token rotates it and returns the new value`() {
        var generated = 0
        val store = RotatingRefreshTokenStore("initial") {
            generated++
            "rotated-$generated"
        }

        val next = store.refresh("initial")

        assertEquals("rotated-1", next)
    }

    @Test fun `refreshing again with the now-stale original token throws and revokes the family`() {
        val store = RotatingRefreshTokenStore("initial") { "rotated-1" }
        store.refresh("initial")

        assertThrows(TokenReuseDetectedException::class.java) {
            store.refresh("initial")
        }
        assertTrue(store.isFamilyRevoked())
    }

    @Test fun `once revoked, even the correct rotated token is refused`() {
        val store = RotatingRefreshTokenStore("initial") { "rotated-1" }
        store.refresh("initial")
        assertThrows(TokenReuseDetectedException::class.java) { store.refresh("initial") }

        assertThrows(TokenReuseDetectedException::class.java) {
            store.refresh("rotated-1")
        }
    }
}
