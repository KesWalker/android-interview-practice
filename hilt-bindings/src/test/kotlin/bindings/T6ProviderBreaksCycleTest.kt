package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: Provider<T> defers resolution, which is how real Dagger code
// breaks a cycle that plain constructor injection can't.
class T6ProviderBreaksCycleTest {
    private class BarHolder(val fooProvider: Provider)

    private val fooKey = Key("Foo")
    private val barKey = Key("Bar")
    private val graph: Graph = mapOf(
        fooKey to Binding(fooKey, listOf(barKey)) { "resolved-foo" },
        barKey to Binding(barKey, listOf(fooKey)) { args -> BarHolder(args[0] as Provider) }
    )

    @Test fun `resolving the lazy side of the cycle terminates instead of recursing forever`() {
        // If Foo were resolved eagerly here instead of deferred behind a
        // Provider, this call would recurse Bar -> Foo -> Bar -> Foo forever.
        val bar = resolveWithProviders(graph, barKey, setOf(fooKey)) as BarHolder

        assertEquals("resolved-foo", bar.fooProvider.get())
    }

    @Test fun `the Provider resolves the real instance on demand`() {
        val bar = resolveWithProviders(graph, barKey, setOf(fooKey)) as BarHolder

        assertEquals("resolved-foo", bar.fooProvider.get())
    }

    @Test fun `entering from the other side of the cycle also terminates`() {
        val foo = resolveWithProviders(graph, fooKey, setOf(fooKey))

        assertEquals("resolved-foo", foo)
    }
}
