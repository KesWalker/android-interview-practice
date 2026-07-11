package permissions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T4FinalStateTest {

    @Test
    fun `no events leaves the permission not requested`() {
        assertEquals(PermissionState.NotRequested, finalState(emptyList()))
    }

    @Test
    fun `a single granted result ends in Granted`() {
        val events = listOf(PermissionResult(granted = true, showRationale = false))
        assertEquals(PermissionState.Granted, finalState(events))
    }

    @Test
    fun `two denials in a row end in PermanentlyDenied`() {
        val events = listOf(
            PermissionResult(granted = false, showRationale = true),
            PermissionResult(granted = false, showRationale = false)
        )
        assertEquals(PermissionState.PermanentlyDenied, finalState(events))
    }

    @Test
    fun `a denial followed by a grant ends in Granted`() {
        val events = listOf(
            PermissionResult(granted = false, showRationale = true),
            PermissionResult(granted = true, showRationale = false)
        )
        assertEquals(PermissionState.Granted, finalState(events))
    }
}
