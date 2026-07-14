package flowbasics

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T2MapFilterTakeTest {

    @Test
    fun `squares, keeps evens, then stops at the limit`() = runTest {
        val source = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        assertEquals(listOf(4, 16, 36), firstEvenSquares(source, limit = 3).toList())
    }

    @Test
    fun `a smaller limit yields fewer results`() = runTest {
        val source = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        assertEquals(listOf(4), firstEvenSquares(source, limit = 1).toList())
    }

    @Test
    fun `take bounds an infinite upstream source`() = runTest {
        val infinite = flow {
            var i = 1
            while (true) {
                emit(i)
                i++
            }
        }

        assertEquals(listOf(4, 16, 36), firstEvenSquares(infinite, limit = 3).toList())
    }
}
