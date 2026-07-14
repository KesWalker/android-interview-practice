package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: bucket a raw width in dp into Material 3's width size class.
class T1WidthSizeClassTest {
    @Test fun `narrow widths are compact`() {
        assertEquals(WindowWidthSizeClass.COMPACT, widthSizeClass(360))
        assertEquals(WindowWidthSizeClass.COMPACT, widthSizeClass(599))
    }

    @Test fun `600dp is the start of medium, 839dp is still medium`() {
        assertEquals(WindowWidthSizeClass.MEDIUM, widthSizeClass(600))
        assertEquals(WindowWidthSizeClass.MEDIUM, widthSizeClass(700))
        assertEquals(WindowWidthSizeClass.MEDIUM, widthSizeClass(839))
    }

    @Test fun `840dp and above is expanded`() {
        assertEquals(WindowWidthSizeClass.EXPANDED, widthSizeClass(840))
        assertEquals(WindowWidthSizeClass.EXPANDED, widthSizeClass(1200))
    }
}
