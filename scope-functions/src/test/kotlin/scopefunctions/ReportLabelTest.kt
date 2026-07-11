package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: with for non-extension, this-style access to a non-null receiver.
class ReportLabelTest {
    @Test fun `labels an unpublished report`() =
        assertEquals("Q1 (12 pages)", reportLabel(Report(title = "Q1", pageCount = 12, published = false)))

    @Test fun `appends published marker`() =
        assertEquals("Annual (40 pages) · published", reportLabel(Report(title = "Annual", pageCount = 40, published = true)))

    @Test fun `handles a zero page count`() =
        assertEquals("Draft (0 pages)", reportLabel(Report(title = "Draft", pageCount = 0, published = false)))
}
