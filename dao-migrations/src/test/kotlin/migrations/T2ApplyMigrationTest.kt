package migrations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: apply one migration and bump the schema version.
class T2ApplyMigrationTest {
    private val users = Table("users", listOf(Column("id", "INTEGER", nullable = false)))
    private val schema = Schema(version = 1, tables = mapOf("users" to users))

    @Test fun `applies the operations and bumps the version`() {
        val migration = Migration(from = 1, to = 2, operations = listOf(AddColumn("users", Column("email", "TEXT"))))

        val result = applyMigration(schema, migration)

        assertEquals(2, result.version)
        assertEquals(
            listOf(Column("id", "INTEGER", nullable = false), Column("email", "TEXT")),
            result.tables.getValue("users").columns
        )
    }

    @Test fun `throws when the migration's from version doesn't match the schema`() {
        val migration = Migration(from = 5, to = 6, operations = emptyList())
        assertThrows(IllegalStateException::class.java) {
            applyMigration(schema, migration)
        }
    }
}
