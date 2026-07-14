package flowbasics

import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T3RunningTotalsTest {

    @Test
    fun `returns a running sum after each emission`() = runTest {
        assertEquals(listOf(1, 3, 6, 10), runningTotals(flowOf(1, 2, 3, 4)))
    }

    @Test
    fun `an empty flow produces an empty list`() = runTest {
        assertEquals(emptyList<Int>(), runningTotals(emptyFlow()))
    }
}
