package flowbasics

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T4UpstreamCatchTest {

    @Test
    fun `substitutes a fallback when the upstream flow throws`() = runTest {
        val faulty = flow {
            emit(1)
            emit(2)
            throw RuntimeException("boom")
        }

        assertEquals(listOf(1, 2, -1), collectWithFallback(faulty, fallback = -1))
    }

    @Test
    fun `a flow that never throws is unaffected`() = runTest {
        assertEquals(listOf(1, 2, 3), collectWithFallback(flowOf(1, 2, 3), fallback = -1))
    }
}
