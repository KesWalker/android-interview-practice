package channels

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T1ProduceSquaresTest {
    @Test fun `produces squares of 1 to 3`() = runTest {
        val ch = produceSquares(3)
        assertEquals(listOf(1, 4, 9), buildList { for (v in ch) add(v) })
    }

    @Test fun `produces squares of 1 to 5`() = runTest {
        val ch = produceSquares(5)
        assertEquals(listOf(1, 4, 9, 16, 25), buildList { for (v in ch) add(v) })
    }

    @Test fun `zero produces empty channel`() = runTest {
        val ch = produceSquares(0)
        assertEquals(emptyList<Int>(), buildList { for (v in ch) add(v) })
    }
}
