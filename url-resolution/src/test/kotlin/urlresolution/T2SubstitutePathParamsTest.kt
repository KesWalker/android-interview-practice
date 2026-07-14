package urlresolution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: {name} substitution with encoding, and a missing param throws.
class T2SubstitutePathParamsTest {
    @Test fun `substitutes a single placeholder`() {
        assertEquals(
            "/users/42",
            substitutePathParams("/users/{id}", mapOf("id" to "42"))
        )
    }

    @Test fun `substitutes multiple placeholders`() {
        assertEquals(
            "/users/1/posts/2",
            substitutePathParams("/users/{id}/posts/{postId}", mapOf("id" to "1", "postId" to "2"))
        )
    }

    @Test fun `encodes reserved characters in the substituted value`() {
        assertEquals(
            "/search/a%20b%2Fc",
            substitutePathParams("/search/{query}", mapOf("query" to "a b/c"))
        )
    }

    @Test fun `missing param throws IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            substitutePathParams("/users/{id}", emptyMap())
        }
    }
}
