package clean

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CalculateTotalUseCaseTest {

    private class CountingDispatcher : CoroutineDispatcher() {
        var dispatchCount = 0
            private set

        override fun dispatch(context: CoroutineContext, block: Runnable) {
            dispatchCount++
            block.run()
        }
    }

    @Test
    fun `computes the total`() = runTest {
        val dispatcher = CountingDispatcher()
        val useCase = CalculateTotalUseCase(dispatcher)

        val result = useCase(price = 12, quantity = 3)

        assertEquals(36, result)
    }

    @Test
    fun `runs the work on the supplied dispatcher`() = runTest {
        val dispatcher = CountingDispatcher()
        val useCase = CalculateTotalUseCase(dispatcher)

        useCase(price = 5, quantity = 2)

        assertTrue(dispatcher.dispatchCount > 0)
    }
}
