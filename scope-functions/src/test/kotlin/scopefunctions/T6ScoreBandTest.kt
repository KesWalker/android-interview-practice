package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: non-extension run for scoping a local val before returning.
class T6ScoreBandTest {
    @Test fun `bands an A score`() = assertEquals("A (95)", scoreBand(95))

    @Test fun `bands a B score`() = assertEquals("B (85)", scoreBand(85))

    @Test fun `bands a C score`() = assertEquals("C (75)", scoreBand(75))

    @Test fun `bands a failing score`() = assertEquals("F (50)", scoreBand(50))

    @Test fun `cutoffs are inclusive at the boundary`() = assertEquals("A (90)", scoreBand(90))
}
