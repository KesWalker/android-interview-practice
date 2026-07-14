package broadcast

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: the single-segment matching primitive '#' / '*' / literal.
class T1SegmentMatchesTest {
    @Test fun `hash matches an all-digit segment`() {
        assertTrue(segmentMatches("#", "42"))
        assertFalse(segmentMatches("#", "42a"))
        assertFalse(segmentMatches("#", ""))
    }

    @Test fun `star matches any non-empty segment`() {
        assertTrue(segmentMatches("*", "anything123"))
        assertTrue(segmentMatches("*", "42"))
    }

    @Test fun `anything else must match literally`() {
        assertTrue(segmentMatches("users", "users"))
        assertFalse(segmentMatches("users", "posts"))
    }
}
