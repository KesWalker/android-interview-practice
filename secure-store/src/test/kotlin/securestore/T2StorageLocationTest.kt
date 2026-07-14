package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: pick the storage location for a sensitivity class.
class T2StorageLocationTest {
    @Test fun `public data goes to plain prefs`() {
        assertEquals(StorageLocation.PLAIN_PREFS, storageLocationFor(Sensitivity.PUBLIC))
    }

    @Test fun `personal data goes to the encrypted store`() {
        assertEquals(StorageLocation.ENCRYPTED_STORE, storageLocationFor(Sensitivity.PERSONAL))
    }

    @Test fun `secret data goes to the keystore-backed store, never plain prefs`() {
        assertEquals(StorageLocation.KEYSTORE_BACKED, storageLocationFor(Sensitivity.SECRET))
    }
}
