package doze

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: Doze needs screen off, not charging, stationary, and idle long enough.
class T1IsDozingTest {
    private val threshold = 30 * 60 * 1000L

    @Test fun `all conditions met enters doze`() {
        val state = DeviceState(screenOn = false, charging = false, stationary = true, idleMillis = threshold)
        assertTrue(isDozing(state, threshold))
    }

    @Test fun `screen on never dozes`() {
        val state = DeviceState(screenOn = true, charging = false, stationary = true, idleMillis = threshold)
        assertFalse(isDozing(state, threshold))
    }

    @Test fun `charging never dozes`() {
        val state = DeviceState(screenOn = false, charging = true, stationary = true, idleMillis = threshold)
        assertFalse(isDozing(state, threshold))
    }

    @Test fun `not idle long enough does not doze`() {
        val state = DeviceState(screenOn = false, charging = false, stationary = true, idleMillis = threshold - 1)
        assertFalse(isDozing(state, threshold))
    }
}
