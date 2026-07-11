package stateflow

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: StateFlow for continuous state, SharedFlow for a one-off event, side by side.
class T2LoginControllerTest {
    @Test fun `toggles loading on then off around the check`() = runTest {
        val controller = LoginController()
        val loadingStates = mutableListOf<Boolean>()
        val job = launch { controller.isLoading.collect { loadingStates.add(it) } }
        runCurrent()

        controller.login("hunter2")
        runCurrent()

        job.cancel()
        assertEquals(listOf(false, true, false), loadingStates)
    }

    @Test fun `emits exactly one failure event for a blank password`() = runTest {
        val controller = LoginController()
        val events = mutableListOf<LoginEvent>()
        val job = launch { controller.events.collect { events.add(it) } }
        runCurrent()

        controller.login("")
        runCurrent()

        assertEquals(1, events.size)
        assertEquals("Password cannot be blank", (events.single() as LoginEvent.ShowError).message)
        job.cancel()
    }

    @Test fun `emits no event when the password is present`() = runTest {
        val controller = LoginController()
        val events = mutableListOf<LoginEvent>()
        val job = launch { controller.events.collect { events.add(it) } }
        runCurrent()

        controller.login("hunter2")
        runCurrent()

        assertEquals(0, events.size)
        job.cancel()
    }
}
