package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 6: chained ?.let (optionally with takeIf) for a multi-step transform.
class T6DiscountPercentTest {
    @Test fun `parses a valid code`() = assertEquals(20, discountPercent("20OFF"))

    @Test fun `null code is null`() = assertNull(discountPercent(null))

    @Test fun `missing suffix is null`() = assertNull(discountPercent("20"))

    @Test fun `non-numeric prefix is null`() = assertNull(discountPercent("xxOFF"))
}
