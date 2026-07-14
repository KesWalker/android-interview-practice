package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class T3BackgroundingCallbacksTest {

    @Test
    fun `backgrounding a resumed activity pauses then stops it`() {
        assertEquals(
            listOf(Callback.ON_PAUSE, Callback.ON_STOP),
            backgroundingCallbacks(LifecycleState.RESUMED)
        )
    }

    @Test
    fun `backgrounding an already-paused activity only stops it`() {
        assertEquals(listOf(Callback.ON_STOP), backgroundingCallbacks(LifecycleState.STARTED))
    }

    @Test
    fun `a stopped activity can't be backgrounded again`() {
        assertThrows(IllegalStateException::class.java) {
            backgroundingCallbacks(LifecycleState.STOPPED)
        }
    }
}
