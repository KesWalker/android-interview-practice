package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class T5ResumeFromStopTest {

    @Test
    fun `the surviving instance is restarted, not recreated`() {
        assertEquals(
            listOf(Callback.ON_RESTART, Callback.ON_START, Callback.ON_RESUME),
            resumeFromStop(wasRecreated = false)
        )
    }

    @Test
    fun `a process-death recreation goes through onCreate instead`() {
        val callbacks = resumeFromStop(wasRecreated = true)

        assertEquals(listOf(Callback.ON_CREATE, Callback.ON_START, Callback.ON_RESUME), callbacks)
        assertFalse(callbacks.contains(Callback.ON_RESTART))
    }
}
