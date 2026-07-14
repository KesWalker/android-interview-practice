package paging

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 2: null prevKey/nextKey means no more data in that direction.
class T2DeriveKeysTest {
    @Test fun `a page in the middle of the data has both keys`() {
        val (prevKey, nextKey) = deriveKeys(start = 20, itemCount = 10, totalCount = 100)

        assertEquals(20, prevKey)
        assertEquals(30, nextKey)
    }

    @Test fun `a page starting at 0 has no prevKey`() {
        val (prevKey, nextKey) = deriveKeys(start = 0, itemCount = 10, totalCount = 100)

        assertNull(prevKey)
        assertEquals(10, nextKey)
    }

    @Test fun `a page reaching the end of the data has no nextKey`() {
        val (prevKey, nextKey) = deriveKeys(start = 90, itemCount = 10, totalCount = 100)

        assertEquals(90, prevKey)
        assertNull(nextKey)
    }

    @Test fun `a page that is the entire data set has neither key`() {
        val (prevKey, nextKey) = deriveKeys(start = 0, itemCount = 100, totalCount = 100)

        assertNull(prevKey)
        assertNull(nextKey)
    }
}
