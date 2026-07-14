package migrations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: replay a whole migration path against a schema.
class T4ApplyMigrationPathTest {
    private val users = Table("users", listOf(Column("id", "INTEGER", nullable = false)))
    private val schema = Schema(version = 1, tables = mapOf("users" to users))

    @Test fun `applies every migration in the path in order`() {
        val path = listOf(
            Migration(1, 2, listOf(AddColumn("users", Column("email", "TEXT")))),
            Migration(2, 3, listOf(RenameColumn("users", "email", "contactEmail")))
        )

        val result = applyMigrationPath(schema, path)

        assertEquals(3, result.version)
        assertEquals(
            listOf(Column("id", "INTEGER", nullable = false), Column("contactEmail", "TEXT")),
            result.tables.getValue("users").columns
        )
    }

    @Test fun `an empty path leaves the schema unchanged`() {
        assertEquals(schema, applyMigrationPath(schema, emptyList()))
    }
}
