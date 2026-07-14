package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: scoped-storage-alike path validation.
class T5ResolveWithinAppStorageTest {
    private val appDir = "/data/data/com.example.app/files"

    @Test fun `a plain filename resolves inside the app directory`() {
        assertEquals(
            "$appDir/avatar.png",
            resolveWithinAppStorage(appDir, "avatar.png")
        )
    }

    @Test fun `a harmless dot-dot that still lands inside the app directory is fine`() {
        assertEquals(
            "$appDir/avatar.png",
            resolveWithinAppStorage(appDir, "images/../avatar.png")
        )
    }

    @Test fun `a dot-dot that escapes the app directory throws`() {
        assertThrows(PathTraversalException::class.java) {
            resolveWithinAppStorage(appDir, "../../etc/passwd")
        }
    }
}
