package testflow

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: Turbine's test{} DSL, awaitItem/awaitComplete, and its strictness about unconsumed events.
class ResultsFlowTest {
    @Test fun `emits every item in order, then completes`() = runTest {
        resultsFlow(listOf(1, 2, 3)).test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test fun `emits nothing and completes immediately for an empty list`() = runTest {
        resultsFlow(emptyList<Int>()).test {
            awaitComplete()
        }
    }

    @Test fun `a test block that leaves an emitted item unconsumed fails`() {
        assertThrows(Throwable::class.java) {
            runTest {
                resultsFlow(listOf(1, 2, 3)).test {
                    // deliberately read nothing -- Turbine should reject an unconsumed event
                }
            }
        }
    }
}
