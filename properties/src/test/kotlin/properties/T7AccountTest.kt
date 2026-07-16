package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 7: private set - readable outside the class, writable only inside it.
// You declare `balance` from scratch in Tasks.kt, and this test actively
// checks there is NO public setter: `private set` is the contract, not a vibe.
class T7AccountTest {
    private fun balanceOf(account: Account): Any? {
        val getter = account.property("getBalance")
            ?: notDeclaredYet(
                "t7: Account.balance",
                "Declare a var `balance: Int` in Account, starting at 0, readable by " +
                    "anyone but assignable only inside Account. A visibility modifier " +
                    "on the setter alone gives you exactly that split.",
            )
        assertTrue(
            account.setter("setBalance", Int::class.javaPrimitiveType!!) == null,
            "t7: balance is publicly WRITABLE - anyone could set it directly, so deposit/withdraw " +
                "would be decoration. Keep the var public to read, but make its setter private.",
        )
        return getter.call(account)
    }

    @Test fun `starts at zero`() {
        val account = Account()
        assertEquals(0, balanceOf(account))
    }

    @Test fun `deposit increases the balance`() {
        val account = Account()
        account.deposit(50)
        assertEquals(50, balanceOf(account))
    }

    @Test fun `withdraw decreases the balance`() {
        val account = Account()
        account.deposit(50)
        account.withdraw(20)
        assertEquals(30, balanceOf(account))
    }

    @Test fun `withdraw clamps at zero rather than going negative`() {
        val account = Account()
        account.deposit(10)
        account.withdraw(100)
        assertEquals(0, balanceOf(account))
    }
}
