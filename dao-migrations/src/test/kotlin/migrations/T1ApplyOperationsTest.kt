package migrations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 1: apply schema operations in order.
class T1ApplyOperationsTest {
    private val users = Table(
        name = "users",
        columns = listOf(Column("id", "INTEGER", nullable = false), Column("name", "TEXT"))
    )
    private val schema = Schema(version = 1, tables = mapOf("users" to users))

    @Test fun `adds a column to an existing table`() {
        val result = applyOperations(schema, listOf(AddColumn("users", Column("email", "TEXT"))))
        assertEquals(
            listOf(Column("id", "INTEGER", nullable = false), Column("name", "TEXT"), Column("email", "TEXT")),
            result.tables.getValue("users").columns
        )
    }

    @Test fun `drops a column, renames another, and adds a new table`() {
        val ops = listOf(
            DropColumn("users", "name"),
            RenameColumn("users", "id", "userId"),
            AddTable(Table("posts", listOf(Column("id", "INTEGER", nullable = false))))
        )

        val result = applyOperations(schema, ops)

        assertEquals(
            listOf(Column("userId", "INTEGER", nullable = false)),
            result.tables.getValue("users").columns
        )
        assertEquals(setOf("users", "posts"), result.tables.keys)
    }

    @Test fun `dropping a table removes it`() {
        val result = applyOperations(schema, listOf(DropTable("users")))
        assertEquals(emptyMap<String, Table>(), result.tables)
    }

    @Test fun `an operation naming an unknown table throws`() {
        assertThrows(IllegalStateException::class.java) {
            applyOperations(schema, listOf(AddColumn("ghost", Column("x", "TEXT"))))
        }
    }
}
