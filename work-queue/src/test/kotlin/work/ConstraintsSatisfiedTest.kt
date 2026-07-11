package work

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ConstraintsSatisfiedTest {

    @Test
    fun `an unconstrained job always runs`() {
        val constraints = WorkConstraints()
        val device = DeviceConditions(isUnmetered = false, isCharging = false, isBatteryLow = true)
        assertTrue(constraintsSatisfied(constraints, device))
    }

    @Test
    fun `a job requiring unmetered network is blocked on a metered connection`() {
        val constraints = WorkConstraints(requiresUnmeteredNetwork = true)
        val device = DeviceConditions(isUnmetered = false, isCharging = true, isBatteryLow = false)
        assertFalse(constraintsSatisfied(constraints, device))
    }

    @Test
    fun `a job requiring charging is blocked while unplugged`() {
        val constraints = WorkConstraints(requiresCharging = true)
        val device = DeviceConditions(isUnmetered = true, isCharging = false, isBatteryLow = false)
        assertFalse(constraintsSatisfied(constraints, device))
    }

    @Test
    fun `a job needing all three constraints only runs once every device condition is met`() {
        val constraints = WorkConstraints(
            requiresUnmeteredNetwork = true,
            requiresCharging = true,
            requiresBatteryNotLow = true,
        )
        val almost = DeviceConditions(isUnmetered = true, isCharging = true, isBatteryLow = true)
        assertFalse(constraintsSatisfied(constraints, almost))

        val allMet = DeviceConditions(isUnmetered = true, isCharging = true, isBatteryLow = false)
        assertTrue(constraintsSatisfied(constraints, allMet))
    }
}
