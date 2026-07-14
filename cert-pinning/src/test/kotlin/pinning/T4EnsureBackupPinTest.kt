package pinning

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 4: a lone pin bricks the app the moment its cert rotates.
class T4EnsureBackupPinTest {
    @Test fun `two pins is enough, no exception`() {
        assertDoesNotThrow { ensureBackupPin(setOf("hash-a", "hash-b")) }
    }

    @Test fun `a single pin is rejected`() {
        assertThrows(InsufficientPinsException::class.java) {
            ensureBackupPin(setOf("hash-a"))
        }
    }

    @Test fun `no pins at all is rejected`() {
        assertThrows(InsufficientPinsException::class.java) {
            ensureBackupPin(emptySet())
        }
    }
}
