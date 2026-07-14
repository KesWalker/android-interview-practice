package flowbasics

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T1ColdFlowTest {

    @Test
    fun `emits the counted-up range`() = runTest {
        val flow = countUpFlow(3) {}

        assertEquals(listOf(1, 2, 3), flow.toList())
    }

    @Test
    fun `re-runs the producer block for every new collector`() = runTest {
        var runs = 0
        val flow = countUpFlow(3) { runs++ }

        assertEquals(listOf(1, 2, 3), flow.toList())
        assertEquals(listOf(1, 2, 3), flow.toList())

        assertEquals(2, runs)
    }
}
