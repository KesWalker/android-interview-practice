package effects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a captured reference always reads the latest updated value.
class T5RememberUpdatedStateTest {
    @Test fun `current reflects the latest updated value`() {
        val ref = UpdatedStateRef(0)

        ref.update(1)
        ref.update(2)

        assertEquals(2, ref.current())
    }

    @Test fun `a reference captured before updates still sees them`() {
        val ref = UpdatedStateRef("first")
        val capturedByLongRunningEffect = ref

        ref.update("second")

        assertEquals("second", capturedByLongRunningEffect.current())
    }
}
