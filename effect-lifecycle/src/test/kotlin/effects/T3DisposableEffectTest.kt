package effects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: dispose the previous effect before running a new one.
class T3DisposableEffectTest {
    @Test fun `runs the effect on the first call`() {
        val runner = DisposableEffectRunner()
        var ran = false

        runner.run(listOf(1)) { ran = true; {} }

        assertEquals(true, ran)
    }

    @Test fun `disposes the previous effect before running the new one on key change`() {
        val runner = DisposableEffectRunner()
        val log = mutableListOf<String>()

        runner.run(listOf(1)) {
            log += "run-1"
            { log += "dispose-1" }
        }
        runner.run(listOf(2)) {
            log += "run-2"
            { log += "dispose-2" }
        }

        assertEquals(listOf("run-1", "dispose-1", "run-2"), log)
    }

    @Test fun `same keys do not rerun or dispose`() {
        val runner = DisposableEffectRunner()
        var runs = 0

        runner.run(listOf("k")) { runs++; {} }
        runner.run(listOf("k")) { runs++; {} }

        assertEquals(1, runs)
    }
}
