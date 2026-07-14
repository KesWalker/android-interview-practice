package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class T4FinishingCallbacksTest {

    @Test
    fun `finishing a resumed activity runs the full teardown`() {
        assertEquals(
            listOf(Callback.ON_PAUSE, Callback.ON_STOP, Callback.ON_DESTROY),
            finishingCallbacks(LifecycleState.RESUMED)
        )
    }

    @Test
    fun `finishing a started activity skips pause`() {
        assertEquals(
            listOf(Callback.ON_STOP, Callback.ON_DESTROY),
            finishingCallbacks(LifecycleState.STARTED)
        )
    }

    @Test
    fun `finishing a created activity that never started just destroys it`() {
        assertEquals(listOf(Callback.ON_DESTROY), finishingCallbacks(LifecycleState.CREATED))
    }

    @Test
    fun `finishing an already-stopped activity just destroys it`() {
        assertEquals(listOf(Callback.ON_DESTROY), finishingCallbacks(LifecycleState.STOPPED))
    }

    @Test
    fun `an activity that was never created can't be finished`() {
        assertThrows(IllegalStateException::class.java) {
            finishingCallbacks(LifecycleState.INITIAL)
        }
    }
}
