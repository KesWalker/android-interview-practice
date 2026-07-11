package resources

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7
class T7WindowSizeClassTest {
    @Test fun `599dp width is COMPACT`() =
        assertEquals(WindowSizeClass.COMPACT, windowWidthSizeClass(599))

    @Test fun `600dp width is MEDIUM`() =
        assertEquals(WindowSizeClass.MEDIUM, windowWidthSizeClass(600))

    @Test fun `839dp width is MEDIUM`() =
        assertEquals(WindowSizeClass.MEDIUM, windowWidthSizeClass(839))

    @Test fun `840dp width is EXPANDED`() =
        assertEquals(WindowSizeClass.EXPANDED, windowWidthSizeClass(840))

    @Test fun `480dp height is MEDIUM`() =
        assertEquals(WindowSizeClass.MEDIUM, windowHeightSizeClass(480))

    @Test fun `900dp height is EXPANDED`() =
        assertEquals(WindowSizeClass.EXPANDED, windowHeightSizeClass(900))
}
