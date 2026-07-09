package deeplink

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 2: walk both lists in step, branching on "{" / "}".
class MatchPathTest {
    @Test fun `captures a single placeholder`() {
        assertEquals(mapOf("id" to "42"), matchPath("tour/{id}", listOf("tour", "42")))
    }

    @Test fun `captures multiple placeholders`() {
        assertEquals(
            mapOf("city" to "paris", "id" to "9"),
            matchPath("city/{city}/tour/{id}", listOf("city", "paris", "tour", "9")),
        )
    }

    @Test fun `literal mismatch is null`() {
        assertNull(matchPath("tour/{id}", listOf("search", "42")))
    }

    @Test fun `different segment count is null`() {
        assertNull(matchPath("tour/{id}", listOf("tour", "42", "extra")))
    }

    @Test fun `pattern with no placeholders needs an exact match`() {
        assertEquals(emptyMap<String, String>(), matchPath("search", listOf("search")))
        assertNull(matchPath("search", listOf("search", "extra")))
    }
}
