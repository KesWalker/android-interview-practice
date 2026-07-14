package framebudget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a visible stutter is worse than the same jank scattered thin.
class T4LongestJankStreakTest {
    private val budgetMs = 1000.0 / 60

    @Test fun `three janky frames in a row beat two scattered pairs`() {
        val durations = listOf(10.0, 20.0, 22.0, 10.0, 25.0, 26.0, 27.0, 10.0)

        assertEquals(3, longestJankStreak(durations, budgetMs))
    }

    @Test fun `an all-smooth run has no streak`() {
        val durations = listOf(5.0, 6.0, 7.0)

        assertEquals(0, longestJankStreak(durations, budgetMs))
    }

    @Test fun `a frozen frame still counts toward the streak`() {
        val durations = listOf(20.0, 22.0, 750.0, 10.0)

        assertEquals(3, longestJankStreak(durations, budgetMs))
    }

    @Test fun `every frame janky means the streak is the whole run`() {
        val durations = listOf(20.0, 22.0, 25.0)

        assertEquals(3, longestJankStreak(durations, budgetMs))
    }
}
