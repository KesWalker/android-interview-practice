package flow

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: a cold flow re-runs its producer for every new collector.
class T1ColdNumbersTest {
    @Test fun `emits 1, 2, 3`() = runTest {
        assertEquals(listOf(1, 2, 3), coldNumbers {}.toList())
    }

    @Test fun `calls onStart once per collection`() = runTest {
        var starts = 0
        val numbers = coldNumbers { starts++ }

        numbers.toList()
        assertEquals(1, starts)

        numbers.toList()
        assertEquals(2, starts)
    }

    @Test fun `produces the same values on every independent collection`() = runTest {
        val numbers = coldNumbers {}
        assertEquals(numbers.toList(), numbers.toList())
    }
}
