package urlresolution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: the classic Retrofit gotcha, leading slash vs relative vs full URL.
class T1ResolveUrlTest {
    @Test fun `relative path with no leading slash appends to baseUrl's path`() {
        assertEquals(
            "https://api.example.com/v1/users/42",
            resolveUrl("https://api.example.com/v1/", "users/42")
        )
    }

    @Test fun `leading slash replaces baseUrl's whole path`() {
        assertEquals(
            "https://api.example.com/users/42",
            resolveUrl("https://api.example.com/v1/", "/users/42")
        )
    }

    @Test fun `a full URL replaces baseUrl entirely`() {
        assertEquals(
            "https://other.example.com/x",
            resolveUrl("https://api.example.com/v1/", "https://other.example.com/x")
        )
    }

    @Test fun `relative path joins cleanly even when baseUrl has no trailing slash`() {
        assertEquals(
            "https://api.example.com/v1/users/42",
            resolveUrl("https://api.example.com/v1", "users/42")
        )
    }
}
