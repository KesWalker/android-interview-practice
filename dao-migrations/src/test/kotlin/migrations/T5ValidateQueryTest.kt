package migrations

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: compile-time-style validation of a @Query's referenced columns.
class T5ValidateQueryTest {
    private val users = Table("users", listOf(Column("id", "INTEGER", nullable = false), Column("name", "TEXT")))
    private val schema = Schema(version = 1, tables = mapOf("users" to users))

    @Test fun `a query over known columns is valid`() {
        assertDoesNotThrow {
            validateQuery(schema, "users", listOf("id", "name"))
        }
    }

    @Test fun `an unknown column throws`() {
        assertThrows(IllegalStateException::class.java) {
            validateQuery(schema, "users", listOf("id", "email"))
        }
    }

    @Test fun `an unknown table throws`() {
        assertThrows(IllegalStateException::class.java) {
            validateQuery(schema, "ghost", listOf("id"))
        }
    }
}
