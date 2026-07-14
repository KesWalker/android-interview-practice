package anr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: total main-thread time by category, so you know what to move off it.
class T2CategoryTotalsTest {
    @Test fun `sums durations grouped by category, omitting categories with no items`() {
        val items = listOf(
            WorkItem("measure", Category.LAYOUT, 0, 100),
            WorkItem("parseJson", Category.NETWORK, 100, 300),
            WorkItem("layoutPass", Category.LAYOUT, 400, 50)
        )
        val expected = mapOf(
            Category.LAYOUT to 150L,
            Category.NETWORK to 300L
        )
        assertEquals(expected, totalTimeByCategory(items))
    }

    @Test fun `an empty trace produces an empty map`() {
        assertEquals(emptyMap<Category, Long>(), totalTimeByCategory(emptyList()))
    }
}
