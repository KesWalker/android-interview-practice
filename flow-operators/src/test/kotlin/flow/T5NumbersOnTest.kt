package flow

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

// Task 5: flowOn only shifts the upstream (producer) side of the chain.
class T5NumbersOnTest {
    @Test fun `emits 1, 2, 3`() = runTest {
        val executor = Executors.newSingleThreadExecutor { runnable -> Thread(runnable, "producer-thread") }
        val dispatcher = executor.asCoroutineDispatcher()
        try {
            val values = numbersOn(dispatcher) { }.toList()
            assertEquals(listOf(1, 2, 3), values)
        } finally {
            dispatcher.close()
        }
    }

    @Test fun `runs the producer on the injected dispatcher, not the collector's context`() = runTest {
        val executor = Executors.newSingleThreadExecutor { runnable -> Thread(runnable, "producer-thread") }
        val dispatcher = executor.asCoroutineDispatcher()
        try {
            var producerThread: String? = null
            var collectorThread: String? = null

            numbersOn(dispatcher) { producerThread = it }
                .onEach { collectorThread = Thread.currentThread().name }
                .toList()

            // startsWith, not equals: with assertions on (as Gradle runs tests),
            // coroutine debug mode appends "@coroutine#N" to the thread name.
            assertTrue(producerThread!!.startsWith("producer-thread"))
            assertFalse(collectorThread!!.startsWith("producer-thread"))
        } finally {
            dispatcher.close()
        }
    }
}
