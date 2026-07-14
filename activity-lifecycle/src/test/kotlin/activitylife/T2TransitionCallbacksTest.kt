package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class T2TransitionCallbacksTest {

    @Test
    fun `a fresh launch all the way to resumed fires all three callbacks`() {
        assertEquals(
            listOf(Callback.ON_CREATE, Callback.ON_START, Callback.ON_RESUME),
            transitionCallbacks(LifecycleState.INITIAL, LifecycleState.RESUMED)
        )
    }

    @Test
    fun `a single step fires a single callback`() {
        assertEquals(
            listOf(Callback.ON_START),
            transitionCallbacks(LifecycleState.CREATED, LifecycleState.STARTED)
        )
    }

    @Test
    fun `staying put fires nothing`() {
        assertEquals(emptyList<Callback>(), transitionCallbacks(LifecycleState.STARTED, LifecycleState.STARTED))
    }

    @Test
    fun `moving backward is not this function's job`() {
        assertThrows(IllegalArgumentException::class.java) {
            transitionCallbacks(LifecycleState.RESUMED, LifecycleState.CREATED)
        }
    }
}
