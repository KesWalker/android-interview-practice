package permissions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T3ActionForTest {

    @Test
    fun `not requested should trigger a request`() {
        assertEquals(PermissionAction.REQUEST, actionFor(PermissionState.NotRequested))
    }

    @Test
    fun `denied once should trigger another request`() {
        assertEquals(PermissionAction.REQUEST, actionFor(PermissionState.Denied))
    }

    @Test
    fun `granted should proceed`() {
        assertEquals(PermissionAction.PROCEED, actionFor(PermissionState.Granted))
    }

    @Test
    fun `permanently denied should open settings`() {
        assertEquals(PermissionAction.OPEN_SETTINGS, actionFor(PermissionState.PermanentlyDenied))
    }
}
