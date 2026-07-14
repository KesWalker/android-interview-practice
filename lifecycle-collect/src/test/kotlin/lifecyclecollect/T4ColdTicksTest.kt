package lifecyclecollect

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: cold flows re-run their startup step on every independent collection.
class T4ColdTicksTest {
    @Test fun `does not touch the counter until something actually collects it`() {
        val startCounter = Counter()
        coldTicks(startCounter)

        assertEquals(0, startCounter.value)
    }

    @Test fun `re-runs its startup step and emits fresh values on every collection`() = runTest {
        val startCounter = Counter()
        val flow = coldTicks(startCounter)

        val firstRun = mutableListOf<Int>()
        flow.collect { firstRun.add(it) }
        assertEquals(listOf(1, 2, 3), firstRun)
        assertEquals(1, startCounter.value)

        val secondRun = mutableListOf<Int>()
        flow.collect { secondRun.add(it) }
        assertEquals(listOf(1, 2, 3), secondRun)
        assertEquals(2, startCounter.value)
    }
}
