package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: standard observable delegate — after-the-fact notification, never rejects.
class AuditedSettingTest {
    @Test fun `starts at the initial value with empty history`() {
        val account = Account(10, mutableListOf())
        assertEquals(10, account.balance)
        assertEquals(emptyList<String>(), account.history)
    }

    @Test fun `records old to new for every write`() {
        val account = Account(10, mutableListOf())
        account.balance = 20
        account.balance = 5
        assertEquals(listOf("10->20", "20->5"), account.history)
        assertEquals(5, account.balance)
    }

    @Test fun `never rejects a write, even repeating the current value`() {
        val account = Account(10, mutableListOf())
        account.balance = 10
        assertEquals(listOf("10->10"), account.history)
        assertEquals(10, account.balance)
    }
}
