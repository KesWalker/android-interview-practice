package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: put the size class, navigation and pane decisions together.
class T7LayoutForTest {
    @Test fun `a phone gets a bottom bar and a single pane`() {
        val layout = layoutFor(widthDp = 360, heightDp = 800)
        assertEquals(
            AdaptiveLayout(
                sizeClass = WindowSizeClass(WindowWidthSizeClass.COMPACT, WindowHeightSizeClass.EXPANDED),
                navigation = NavigationAffordance.BOTTOM_BAR,
                panes = PaneStrategy.SINGLE_PANE
            ),
            layout
        )
    }

    @Test fun `a foldable unfolded gets a rail and a single pane`() {
        val layout = layoutFor(widthDp = 700, heightDp = 600)
        assertEquals(
            AdaptiveLayout(
                sizeClass = WindowSizeClass(WindowWidthSizeClass.MEDIUM, WindowHeightSizeClass.MEDIUM),
                navigation = NavigationAffordance.RAIL,
                panes = PaneStrategy.SINGLE_PANE
            ),
            layout
        )
    }

    @Test fun `a tablet gets a drawer and list-detail`() {
        val layout = layoutFor(widthDp = 1200, heightDp = 900)
        assertEquals(
            AdaptiveLayout(
                sizeClass = WindowSizeClass(WindowWidthSizeClass.EXPANDED, WindowHeightSizeClass.EXPANDED),
                navigation = NavigationAffordance.DRAWER,
                panes = PaneStrategy.LIST_DETAIL
            ),
            layout
        )
    }
}
