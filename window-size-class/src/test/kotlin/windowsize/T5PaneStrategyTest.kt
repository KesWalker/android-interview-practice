package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: pick single-pane vs. list-detail from the width size class.
class T5PaneStrategyTest {
    @Test fun `compact width is always a single pane`() {
        assertEquals(PaneStrategy.SINGLE_PANE, paneStrategy(WindowWidthSizeClass.COMPACT))
    }

    @Test fun `medium width still isn't wide enough for list-detail`() {
        assertEquals(PaneStrategy.SINGLE_PANE, paneStrategy(WindowWidthSizeClass.MEDIUM))
    }

    @Test fun `expanded width unlocks list-detail`() {
        assertEquals(PaneStrategy.LIST_DETAIL, paneStrategy(WindowWidthSizeClass.EXPANDED))
    }
}
