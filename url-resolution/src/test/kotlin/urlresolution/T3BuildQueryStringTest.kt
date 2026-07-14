package urlresolution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: query string building, skipping nulls, encoding, empty case.
class T3BuildQueryStringTest {
    @Test fun `builds an encoded query string from non-null params`() {
        assertEquals("?a=1&b=2", buildQueryString(linkedMapOf("a" to "1", "b" to "2")))
    }

    @Test fun `skips null-valued entries`() {
        assertEquals("?a=1", buildQueryString(linkedMapOf("a" to "1", "b" to null)))
    }

    @Test fun `empty or all-null params produce an empty string`() {
        assertEquals("", buildQueryString(emptyMap()))
        assertEquals("", buildQueryString(linkedMapOf("a" to null)))
    }

    @Test fun `encodes special characters in keys and values`() {
        assertEquals("?q=a%20b", buildQueryString(linkedMapOf("q" to "a b")))
    }
}
