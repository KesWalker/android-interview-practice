package bindings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: an interface key resolves through @Binds to its implementation.
class T2BindsMappingTest {
    @Test fun `an unbound interface key follows the alias to its implementation`() {
        val loggerKey = Key("Logger")
        val analyticsImplKey = Key("FirebaseAnalytics")
        val analyticsKey = Key("Analytics")
        val graph: Graph = mapOf(
            loggerKey to Binding(loggerKey) { "logger" },
            analyticsImplKey to Binding(analyticsImplKey, listOf(loggerKey)) { args ->
                "firebaseAnalytics(${args[0]})"
            }
        )
        val binds = mapOf(analyticsKey to analyticsImplKey)

        assertEquals("firebaseAnalytics(logger)", resolveWithBinds(graph, binds, analyticsKey))
    }

    @Test fun `a directly bound key resolves without consulting binds`() {
        val loggerKey = Key("Logger")
        val graph: Graph = mapOf(loggerKey to Binding(loggerKey) { "logger" })

        assertEquals("logger", resolveWithBinds(graph, emptyMap(), loggerKey))
    }
}
