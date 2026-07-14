package expectactual

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: fail loudly, with a useful message, instead of letting a leak slide.
class T5RequireCommonMainSafeTest {
    @Test fun `clean imports do not throw`() {
        assertDoesNotThrow {
            requireCommonMainSafe(listOf("kotlin.collections.List", "kotlin.text.Regex"))
        }
    }

    @Test fun `a leaked import throws PlatformLeakException`() {
        assertThrows(PlatformLeakException::class.java) {
            requireCommonMainSafe(listOf("androidx.compose.ui.graphics.Color"))
        }
    }

    @Test fun `the exception message names every leaked import`() {
        val exception = assertThrows(PlatformLeakException::class.java) {
            requireCommonMainSafe(listOf("kotlin.text.Regex", "java.util.UUID", "UIKit.UIView"))
        }

        assertTrue(exception.message!!.contains("java.util.UUID"))
        assertTrue(exception.message!!.contains("UIKit.UIView"))
        assertTrue(!exception.message!!.contains("kotlin.text.Regex"))
    }
}
