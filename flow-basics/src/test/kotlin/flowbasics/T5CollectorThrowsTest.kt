package flowbasics

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T5CollectorThrowsTest {

    @Test
    fun `stops counting once the collector throws`() = runTest {
        assertEquals(2, countUntilCollectorThrows(flowOf(1, 2, 3, 4, 5), failOn = 3))
    }

    @Test
    fun `counts everything when the collector never throws`() = runTest {
        assertEquals(3, countUntilCollectorThrows(flowOf(1, 2, 3), failOn = 99))
    }
}
