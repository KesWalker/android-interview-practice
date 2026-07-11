package deeplink

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: split on "://", then "?", then "/" and "&"/"=".
class T1ParseUriTest {
    @Test fun `splits scheme host path and query`() {
        val result = parseUri("audatours://app/tour/42?lang=en&ref=email")
        assertEquals("audatours", result.scheme)
        assertEquals("app", result.host)
        assertEquals(listOf("tour", "42"), result.pathSegments)
        assertEquals(mapOf("lang" to "en", "ref" to "email"), result.queryParams)
    }

    @Test fun `missing query string yields an empty map`() {
        val result = parseUri("https://example.com/tour/7")
        assertEquals("https", result.scheme)
        assertEquals("example.com", result.host)
        assertEquals(listOf("tour", "7"), result.pathSegments)
        assertEquals(emptyMap<String, String>(), result.queryParams)
    }

    @Test fun `host with no path segments`() {
        val result = parseUri("audatours://home")
        assertEquals("audatours", result.scheme)
        assertEquals("home", result.host)
        assertEquals(emptyList<String>(), result.pathSegments)
        assertEquals(emptyMap<String, String>(), result.queryParams)
    }

    @Test fun `trailing slash does not add an empty segment`() {
        val result = parseUri("audatours://app/search/")
        assertEquals(listOf("search"), result.pathSegments)
    }
}
