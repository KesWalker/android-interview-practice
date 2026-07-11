package coroutines

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: a suspend function that simulates latency without blocking.
class T1FetchGreetingTest {
    @Test fun `greets by name`() = runTest {
        assertEquals("Hello, Ada!", fetchGreeting("Ada"))
    }

    @Test fun `greets a different name`() = runTest {
        assertEquals("Hello, Grace!", fetchGreeting("Grace"))
    }

    @Test fun `simulates latency on virtual time instead of actually blocking`() = runTest {
        fetchGreeting("Ada")
        assertEquals(200L, testScheduler.currentTime)
    }
}
