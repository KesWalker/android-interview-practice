package expectactual

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: catch a commonMain purity violation.
class T4FindPlatformLeaksTest {
    @Test fun `all-common imports report no leaks`() {
        val imports = listOf("kotlin.collections.List", "kotlin.text.Regex", "kotlin.math.abs")
        assertEquals(emptyList<String>(), findPlatformLeaks(imports))
    }

    @Test fun `an androidx import is a leak`() {
        val imports = listOf("kotlin.collections.List", "androidx.compose.ui.graphics.Color")
        assertEquals(listOf("androidx.compose.ui.graphics.Color"), findPlatformLeaks(imports))
    }

    @Test fun `mixed imports return only the leaked ones in order`() {
        val imports = listOf(
            "kotlin.text.Regex",
            "java.util.UUID",
            "kotlin.collections.List",
            "platform.Foundation.NSDate",
            "UIKit.UIView"
        )

        assertEquals(
            listOf("java.util.UUID", "platform.Foundation.NSDate", "UIKit.UIView"),
            findPlatformLeaks(imports)
        )
    }

    @Test fun `empty import list reports no leaks`() {
        assertEquals(emptyList<String>(), findPlatformLeaks(emptyList()))
    }
}
