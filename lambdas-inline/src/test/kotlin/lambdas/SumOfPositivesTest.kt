package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: trailing lambda, `it`, and a labeled return to skip an element.
class SumOfPositivesTest {
    @Test fun `adds only positive numbers`() = assertEquals(9, sumOfPositives(listOf(1, -2, 3, -4, 5)))
    @Test fun `all positive sums everything`() = assertEquals(6, sumOfPositives(listOf(1, 2, 3)))
    @Test fun `empty list sums to zero`() = assertEquals(0, sumOfPositives(emptyList()))
}
