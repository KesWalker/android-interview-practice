package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: pick a navigation affordance from the width size class.
class T4NavigationAffordanceTest {
    @Test fun `compact width gets a bottom bar`() {
        assertEquals(NavigationAffordance.BOTTOM_BAR, navigationAffordance(WindowWidthSizeClass.COMPACT))
    }

    @Test fun `medium width gets a nav rail`() {
        assertEquals(NavigationAffordance.RAIL, navigationAffordance(WindowWidthSizeClass.MEDIUM))
    }

    @Test fun `expanded width gets a permanent drawer`() {
        assertEquals(NavigationAffordance.DRAWER, navigationAffordance(WindowWidthSizeClass.EXPANDED))
    }
}
