package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: combine width and height into one WindowSizeClass.
class T3WindowSizeClassTest {
    @Test fun `a phone in portrait is compact width, expanded height`() {
        val result = windowSizeClass(widthDp = 360, heightDp = 800)
        assertEquals(WindowSizeClass(WindowWidthSizeClass.COMPACT, WindowHeightSizeClass.EXPANDED), result)
    }

    @Test fun `a foldable unfolded is medium width, medium height`() {
        val result = windowSizeClass(widthDp = 700, heightDp = 600)
        assertEquals(WindowSizeClass(WindowWidthSizeClass.MEDIUM, WindowHeightSizeClass.MEDIUM), result)
    }

    @Test fun `a tablet in landscape is expanded on both axes`() {
        val result = windowSizeClass(widthDp = 1200, heightDp = 900)
        assertEquals(WindowSizeClass(WindowWidthSizeClass.EXPANDED, WindowHeightSizeClass.EXPANDED), result)
    }
}
