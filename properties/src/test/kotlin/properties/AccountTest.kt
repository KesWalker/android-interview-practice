package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: private set - readable outside the class, writable only inside it.
class AccountTest {
    @Test fun `starts at zero`() {
        val account = Account()
        assertEquals(0, account.balance)
    }

    @Test fun `deposit increases the balance`() {
        val account = Account()
        account.deposit(50)
        assertEquals(50, account.balance)
    }

    @Test fun `withdraw decreases the balance`() {
        val account = Account()
        account.deposit(50)
        account.withdraw(20)
        assertEquals(30, account.balance)
    }

    @Test fun `withdraw clamps at zero rather than going negative`() {
        val account = Account()
        account.deposit(10)
        account.withdraw(100)
        assertEquals(0, account.balance)
    }
}
