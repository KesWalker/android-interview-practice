package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: apply for builder-style configuration.
class BuildPublishedReportTest {
    @Test fun `sets title and page count`() {
        val report = buildPublishedReport("Q1", 12)
        assertEquals("Q1", report.title)
        assertEquals(12, report.pageCount)
    }

    @Test fun `marks it published`() = assertTrue(buildPublishedReport("Q1", 12).published)

    @Test fun `equals a manually built report`() =
        assertEquals(Report(title = "Annual", pageCount = 40, published = true), buildPublishedReport("Annual", 40))
}
