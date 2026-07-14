package migrations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: the real interview question, migrate or fall back to destructive.
class T6ResolveMigrationTest {
    private val schema = Schema(version = 1, tables = emptyMap())
    private val m12 = Migration(1, 2, emptyList())

    @Test fun `returns Migrate with the found path`() {
        val plan = resolveMigration(schema, targetVersion = 2, migrations = listOf(m12), fallbackToDestructiveMigration = false)
        assertEquals(Migrate(listOf(m12)), plan)
    }

    @Test fun `falls back to Destructive when no path exists and fallback is allowed`() {
        val plan = resolveMigration(schema, targetVersion = 9, migrations = listOf(m12), fallbackToDestructiveMigration = true)
        assertEquals(Destructive, plan)
    }

    @Test fun `throws when no path exists and fallback is not allowed`() {
        assertThrows(IllegalStateException::class.java) {
            resolveMigration(schema, targetVersion = 9, migrations = listOf(m12), fallbackToDestructiveMigration = false)
        }
    }
}
