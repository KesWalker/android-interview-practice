package work

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T6CanStartForegroundServiceTest {

    @Test
    fun `foreground and background-exempt states allow the start`() {
        assertTrue(canStartForegroundService(AppProcessState.FOREGROUND))
        assertTrue(canStartForegroundService(AppProcessState.BACKGROUND_EXEMPT))
    }

    @Test
    fun `plain background state does not allow the start`() {
        assertFalse(canStartForegroundService(AppProcessState.BACKGROUND))
    }
}
