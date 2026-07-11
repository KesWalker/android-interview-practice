package permissions

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T6CanRequestBackgroundLocationTest {

    @Test
    fun `a Granted foreground state allows the background request`() {
        assertTrue(canRequestBackgroundLocation(PermissionState.Granted))
    }

    @Test
    fun `NotRequested blocks the background request`() {
        assertFalse(canRequestBackgroundLocation(PermissionState.NotRequested))
    }

    @Test
    fun `Denied blocks the background request`() {
        assertFalse(canRequestBackgroundLocation(PermissionState.Denied))
    }

    @Test
    fun `PermanentlyDenied blocks the background request`() {
        assertFalse(canRequestBackgroundLocation(PermissionState.PermanentlyDenied))
    }
}
