package broadcast

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: the full content-URI matcher, mirroring UriMatcher.addURI/match.
class T2MatchUriTest {
    private val patterns = listOf(
        UriPattern("com.app.provider", "users", 1),
        UriPattern("com.app.provider", "users/#", 2),
        UriPattern("com.app.provider", "users/*/posts", 3)
    )

    @Test fun `matches a literal path`() {
        assertEquals(1, matchUri(patterns, SimpleUri("com.app.provider", "users")))
    }

    @Test fun `matches a hash segment for an all-digit id`() {
        assertEquals(2, matchUri(patterns, SimpleUri("com.app.provider", "users/42")))
    }

    @Test fun `matches a star segment for any value`() {
        assertEquals(3, matchUri(patterns, SimpleUri("com.app.provider", "users/42/posts")))
        assertEquals(3, matchUri(patterns, SimpleUri("com.app.provider", "users/abc/posts")))
    }

    @Test fun `returns NO_MATCH for a wrong authority or an unregistered path`() {
        assertEquals(NO_MATCH, matchUri(patterns, SimpleUri("other.authority", "users")))
        assertEquals(NO_MATCH, matchUri(patterns, SimpleUri("com.app.provider", "users/42/comments")))
    }

    @Test fun `the first matching pattern in list order wins`() {
        val ambiguous = listOf(
            UriPattern("a", "*", 100),
            UriPattern("a", "users", 200)
        )
        assertEquals(100, matchUri(ambiguous, SimpleUri("a", "users")))
    }
}
