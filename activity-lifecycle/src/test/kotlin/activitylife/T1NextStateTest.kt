package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class T1NextStateTest {

    @Test
    fun `walks forward through the normal startup sequence`() {
        assertEquals(LifecycleState.CREATED, nextState(LifecycleState.INITIAL, Callback.ON_CREATE))
        assertEquals(LifecycleState.STARTED, nextState(LifecycleState.CREATED, Callback.ON_START))
        assertEquals(LifecycleState.RESUMED, nextState(LifecycleState.STARTED, Callback.ON_RESUME))
    }

    @Test
    fun `walks backward through pause, stop and destroy`() {
        assertEquals(LifecycleState.PAUSED, nextState(LifecycleState.RESUMED, Callback.ON_PAUSE))
        assertEquals(LifecycleState.STOPPED, nextState(LifecycleState.PAUSED, Callback.ON_STOP))
        assertEquals(LifecycleState.DESTROYED, nextState(LifecycleState.STOPPED, Callback.ON_DESTROY))
    }

    @Test
    fun `a dialog dismissing resumes without stopping`() {
        assertEquals(LifecycleState.RESUMED, nextState(LifecycleState.PAUSED, Callback.ON_RESUME))
    }

    @Test
    fun `an impossible combination throws`() {
        assertThrows(IllegalStateException::class.java) {
            nextState(LifecycleState.RESUMED, Callback.ON_START)
        }
    }
}
