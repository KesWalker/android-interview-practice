package testflow

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 1: inject the dispatcher so a test can drive it deterministically.
class GreetingLoaderTest {
    @Test fun `starts with no greeting loaded`() = runTest {
        val loader = GreetingLoader(StandardTestDispatcher(testScheduler)) { name -> "Hello, $name!" }

        assertNull(loader.state.value)
    }

    @Test fun `does not publish the greeting until the dispatcher is advanced`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val loader = GreetingLoader(dispatcher) { name -> "Hello, $name!" }

        loader.load("Ada")

        assertNull(loader.state.value)
    }

    @Test fun `publishes the greeting once the dispatcher runs the queued work`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val loader = GreetingLoader(dispatcher) { name -> "Hello, $name!" }

        loader.load("Ada")
        advanceUntilIdle()

        assertEquals("Hello, Ada!", loader.state.value)
    }
}
