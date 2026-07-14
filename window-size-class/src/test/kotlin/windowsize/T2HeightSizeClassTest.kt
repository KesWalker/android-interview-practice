package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: bucket a raw height in dp into Material 3's height size class.
class T2HeightSizeClassTest {
    @Test fun `short heights are compact`() {
        assertEquals(WindowHeightSizeClass.COMPACT, heightSizeClass(320))
        assertEquals(WindowHeightSizeClass.COMPACT, heightSizeClass(479))
    }

    @Test fun `480dp is the start of medium, 899dp is still medium`() {
        assertEquals(WindowHeightSizeClass.MEDIUM, heightSizeClass(480))
        assertEquals(WindowHeightSizeClass.MEDIUM, heightSizeClass(700))
        assertEquals(WindowHeightSizeClass.MEDIUM, heightSizeClass(899))
    }

    @Test fun `900dp and above is expanded`() {
        assertEquals(WindowHeightSizeClass.EXPANDED, heightSizeClass(900))
        assertEquals(WindowHeightSizeClass.EXPANDED, heightSizeClass(1400))
    }
}
