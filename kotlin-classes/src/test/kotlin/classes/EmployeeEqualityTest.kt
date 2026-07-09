package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 1: which properties a data class's equality actually compares.
class EmployeeEqualityTest {
    @Test fun `same id, different department, still the same record`() {
        val a = Employee("E1").apply { department = "Sales" }
        val b = Employee("E1").apply { department = "Engineering" }
        assertTrue(sameEmployee(a, b))
    }

    @Test fun `different id is a different record`() {
        val a = Employee("E1")
        val b = Employee("E2")
        assertFalse(sameEmployee(a, b))
    }

    @Test fun `same id and same department is still equal`() {
        val a = Employee("E3").apply { department = "Ops" }
        val b = Employee("E3").apply { department = "Ops" }
        assertEquals(true, sameEmployee(a, b))
    }
}
