package permissions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T1NextStateTest {

    @Test
    fun `granted result moves NotRequested to Granted`() {
        val result = nextState(PermissionState.NotRequested, PermissionResult(granted = true, showRationale = false))
        assertEquals(PermissionState.Granted, result)
    }

    @Test
    fun `first denial with rationale allowed moves to Denied`() {
        val result = nextState(PermissionState.NotRequested, PermissionResult(granted = false, showRationale = true))
        assertEquals(PermissionState.Denied, result)
    }

    @Test
    fun `denial without rationale moves to PermanentlyDenied`() {
        val result = nextState(PermissionState.Denied, PermissionResult(granted = false, showRationale = false))
        assertEquals(PermissionState.PermanentlyDenied, result)
    }

    @Test
    fun `already granted stays granted regardless of a stray result`() {
        val result = nextState(PermissionState.Granted, PermissionResult(granted = false, showRationale = false))
        assertEquals(PermissionState.Granted, result)
    }
}
