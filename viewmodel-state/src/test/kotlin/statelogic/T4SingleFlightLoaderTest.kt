package statelogic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: guarding against duplicate concurrent work, the "don't double-fetch" idiom.
class T4SingleFlightLoaderTest {
    @Test fun `concurrent calls share a single underlying fetch`() = runTest {
        var callCount = 0
        val loader = SingleFlightLoader<Int>(this)

        val first = async { loader.load { callCount++; delay(100); 42 } }
        val second = async { loader.load { callCount++; delay(100); 99 } }
        advanceUntilIdle()

        assertEquals(1, callCount)
        assertEquals(42, first.await())
        assertEquals(42, second.await())
    }

    @Test fun `sequential calls each trigger a fresh fetch`() = runTest {
        var callCount = 0
        val loader = SingleFlightLoader<Int>(this)

        val r1 = loader.load { callCount++; 1 }
        val r2 = loader.load { callCount++; 2 }

        assertEquals(2, callCount)
        assertEquals(1, r1)
        assertEquals(2, r2)
    }

    @Test fun `a call after the first completes triggers another fetch`() = runTest {
        var callCount = 0
        val loader = SingleFlightLoader<Int>(this)

        loader.load { callCount++; delay(50); 1 }
        loader.load { callCount++; delay(50); 2 }

        assertEquals(2, callCount)
    }
}
