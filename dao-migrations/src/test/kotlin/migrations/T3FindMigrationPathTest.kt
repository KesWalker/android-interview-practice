package migrations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: chain migrations into a path, or fail when there isn't one.
class T3FindMigrationPathTest {
    private val m12 = Migration(1, 2, emptyList())
    private val m24 = Migration(2, 4, emptyList())
    private val m23 = Migration(2, 3, emptyList())
    private val m35 = Migration(3, 5, emptyList())
    private val migrations = listOf(m12, m24, m23, m35)

    @Test fun `returns a direct migration unchanged`() {
        assertEquals(listOf(m12), findMigrationPath(migrations, from = 1, to = 2))
    }

    @Test fun `chains through an intermediate version when there's no direct migration`() {
        assertEquals(listOf(m12, m24), findMigrationPath(migrations, from = 1, to = 4))
    }

    @Test fun `chains through multiple intermediate versions`() {
        assertEquals(listOf(m12, m23, m35), findMigrationPath(migrations, from = 1, to = 5))
    }

    @Test fun `throws when no chain reaches the target version`() {
        assertThrows(IllegalStateException::class.java) {
            findMigrationPath(migrations, from = 1, to = 9)
        }
    }
}
