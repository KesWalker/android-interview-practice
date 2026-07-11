package testflow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: collecting a Flow's emissions into a plain list to assert on them.
class T3AboveThresholdTest {
    @Test fun `keeps only values greater than the threshold`() = runTest {
        val result = aboveThreshold(flowOf(1, 5, 10, 3, 8), threshold = 4).toList()

        assertEquals(listOf(5, 10, 8), result)
    }

    @Test fun `returns an empty list when nothing clears the bar`() = runTest {
        val result = aboveThreshold(flowOf(1, 2, 3), threshold = 10).toList()

        assertEquals(emptyList<Int>(), result)
    }

    @Test fun `collects only the first two matches from a flow that never ends`() = runTest {
        val endless = flow {
            var i = 0
            while (true) {
                emit(i)
                i++
            }
        }

        val result = aboveThreshold(endless, threshold = 5).take(2).toList()

        assertEquals(listOf(6, 7), result)
    }
}
