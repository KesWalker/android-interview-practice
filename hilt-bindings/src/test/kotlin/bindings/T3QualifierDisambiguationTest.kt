package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: two bindings of the same type, told apart only by qualifier.
class T3QualifierDisambiguationTest {
    private val prodKey = Key("ApiClient", "prod")
    private val testKey = Key("ApiClient", "test")
    private val graph: Graph = mapOf(
        prodKey to Binding(prodKey) { "prod-client" },
        testKey to Binding(testKey) { "test-client" }
    )

    @Test fun `each qualifier resolves its own binding`() {
        assertEquals("prod-client", resolveQualified(graph, prodKey))
        assertEquals("test-client", resolveQualified(graph, testKey))
    }

    @Test fun `an unqualified request never falls back to a qualified binding`() {
        assertThrows(MissingBindingException::class.java) {
            resolveQualified(graph, Key("ApiClient"))
        }
    }

    @Test fun `a request for the wrong qualifier is not satisfied by the other one`() {
        assertThrows(MissingBindingException::class.java) {
            resolveQualified(graph, Key("ApiClient", "staging"))
        }
    }
}
