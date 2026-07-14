package effects

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: dispose the current effect when the composable leaves.
class T4DisposableLeaveTest {
    @Test fun `leave disposes the current effect`() {
        val runner = DisposableEffectRunner()
        var disposed = false

        runner.run(listOf(1)) { { disposed = true } }
        runner.leave()

        assertTrue(disposed)
    }

    @Test fun `leave without a prior run is a no-op`() {
        val runner = DisposableEffectRunner()

        assertDoesNotThrow { runner.leave() }
    }

    @Test fun `after leave, the same keys run again as if it were the first time`() {
        val runner = DisposableEffectRunner()
        var runs = 0

        runner.run(listOf(1)) { runs++; {} }
        runner.leave()
        runner.run(listOf(1)) { runs++; {} }

        assertEquals(2, runs)
    }
}
