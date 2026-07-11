package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 4: run for grouping calls on a nullable receiver.
class T4SummarizeTest {
    @Test fun `null order becomes null`() = assertNull(summarize(null))

    @Test fun `summarizes items and discount`() =
        assertEquals("3 items, 10% off", summarize(Order(listOf("a", "b", "c"), 10)))

    @Test fun `handles zero discount`() =
        assertEquals("1 items, 0% off", summarize(Order(listOf("a"), 0)))
}
