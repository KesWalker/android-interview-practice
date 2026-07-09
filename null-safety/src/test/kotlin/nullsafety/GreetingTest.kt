package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: elvis with a sensible default.
class GreetingTest {
    @Test fun `greets by name`() = assertEquals("Hello, Ada!", greetingFor("Ada"))
    @Test fun `null falls back`() = assertEquals("Hello, there!", greetingFor(null))
    @Test fun `blank falls back`() = assertEquals("Hello, there!", greetingFor("   "))
}
