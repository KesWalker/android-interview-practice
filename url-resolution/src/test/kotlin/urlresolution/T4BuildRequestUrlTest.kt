package urlresolution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: composes resolveUrl, substitutePathParams and buildQueryString.
class T4BuildRequestUrlTest {
    @Test fun `assembles a relative path with a path param and a query param`() {
        assertEquals(
            "https://api.example.com/v1/users/42?verbose=true",
            buildRequestUrl(
                baseUrl = "https://api.example.com/v1/",
                pathTemplate = "users/{id}",
                pathParams = mapOf("id" to "42"),
                queryParams = mapOf("verbose" to "true")
            )
        )
    }

    @Test fun `a leading-slash path overrides baseUrl's path and skips a null query param`() {
        assertEquals(
            "https://api.example.com/users/7",
            buildRequestUrl(
                baseUrl = "https://api.example.com/v1/",
                pathTemplate = "/users/{id}",
                pathParams = mapOf("id" to "7"),
                queryParams = mapOf("verbose" to null)
            )
        )
    }

    @Test fun `no query params means no trailing question mark`() {
        assertEquals(
            "https://api.example.com/v1/users/1",
            buildRequestUrl(
                baseUrl = "https://api.example.com/v1/",
                pathTemplate = "users/{id}",
                pathParams = mapOf("id" to "1")
            )
        )
    }
}
