package permissions

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T2ShouldShowRationaleTest {

    @Test
    fun `not requested does not need a rationale`() {
        assertFalse(shouldShowRationale(PermissionState.NotRequested))
    }

    @Test
    fun `a single denial does need a rationale`() {
        assertTrue(shouldShowRationale(PermissionState.Denied))
    }

    @Test
    fun `granted does not need a rationale`() {
        assertFalse(shouldShowRationale(PermissionState.Granted))
    }

    @Test
    fun `permanently denied does not need a rationale`() {
        assertFalse(shouldShowRationale(PermissionState.PermanentlyDenied))
    }
}
