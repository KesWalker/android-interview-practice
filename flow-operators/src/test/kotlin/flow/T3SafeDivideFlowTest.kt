package flow

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: recovering an upstream failure without wrapping emit in try/catch.
class T3SafeDivideFlowTest {
    @Test fun `passes through when nothing throws`() = runTest {
        assertEquals(listOf(25, 20), safeDivideFlow(listOf(4, 5)).toList())
    }

    @Test fun `recovers a divide-by-zero as -1`() = runTest {
        assertEquals(listOf(50, -1), safeDivideFlow(listOf(2, 0, 4)).toList())
    }

    @Test fun `stops instead of resuming upstream after the recovery`() = runTest {
        assertEquals(listOf(-1), safeDivideFlow(listOf(0, 100)).toList())
    }
}
