package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: pairing the latest value from two independent sources.
class T2LatestPairTest {
    @Test fun `emits a new pair every time either source produces a value`() = runTest {
        val numbers = flow {
            emit(1)
            delay(100)
            emit(2)
        }
        val letters = flow {
            delay(50)
            emit("X")
            delay(100)
            emit("Y")
        }

        val result = latestPair(numbers, letters).toList()

        assertEquals(listOf("1X", "2X", "2Y"), result)
    }

    @Test fun `keeps using the last value from a source that finished early`() = runTest {
        val numbers = flowOf(1, 2, 3)
        val letters = flow {
            delay(10)
            emit("A")
        }

        val result = latestPair(numbers, letters).toList()

        assertEquals(listOf("3A"), result)
    }
}
