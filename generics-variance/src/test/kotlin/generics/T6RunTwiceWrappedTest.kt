package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: crossinline — inlined into an object expression, no non-local return.
class T6RunTwiceWrappedTest {
    @Test fun `does not run block while building the Runnable`() {
        var calls = 0
        runTwiceWrapped { calls++ }
        assertEquals(0, calls)
    }

    @Test fun `runs block exactly twice when run is invoked`() {
        var calls = 0
        val runnable = runTwiceWrapped { calls++ }
        runnable.run()
        assertEquals(2, calls)
    }

    @Test fun `running it again runs block twice more`() {
        var calls = 0
        val runnable = runTwiceWrapped { calls++ }
        runnable.run()
        runnable.run()
        assertEquals(4, calls)
    }
}
