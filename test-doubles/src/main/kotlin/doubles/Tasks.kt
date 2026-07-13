package doubles

/**
 * Test Doubles practice.
 *
 * Each type below is broken or unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to hand-roll a small
 * test double and make its test go GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :test-doubles:test
 */

data class User(val email: String, val name: String)

interface UserStore {
    fun exists(email: String): Boolean
    fun save(user: User)
    fun all(): List<User>
}

// TODO(t1): T1InMemoryUserStoreTest
// A working in-memory UserStore that actually remembers what's saved and reports on it accurately.
class InMemoryUserStore : UserStore {
    override fun exists(email: String): Boolean {
        TODO("t1: report whether a user with this email has been saved")
    }

    override fun save(user: User) {
        TODO("t1: remember this user")
    }

    override fun all(): List<User> {
        TODO("t1: return every user saved so far, in the order they were saved")
    }
}

interface EmailSender {
    fun send(to: String, subject: String): Boolean
}

// TODO(t2): T2StubEmailSenderTest
// An EmailSender that always returns the same preconfigured result, no matter what it's asked to send.
class StubEmailSender(private val result: Boolean) : EmailSender {
    override fun send(to: String, subject: String): Boolean {
        TODO("t2: return the preconfigured result, ignoring to/subject")
    }
}

// TODO(t3): T3SpyEmailSenderTest
// An EmailSender that records every call it receives, then forwards it to a wrapped EmailSender and returns its result.
class SpyEmailSender(private val delegate: EmailSender) : EmailSender {
    val sentMessages = mutableListOf<Pair<String, String>>()

    override fun send(to: String, subject: String): Boolean {
        TODO("t3: record (to, subject), then delegate the call and return its result")
    }
}

const val WELCOME_SUBJECT = "Welcome!"

enum class SignupResult { ALREADY_REGISTERED, SUCCESS, EMAIL_FAILED }

// TODO(t4): T4SignupServiceTest
// A service that registers a new email, skips duplicates, and reports whether the welcome email went out.
class SignupService(private val userStore: UserStore, private val emailSender: EmailSender) {
    fun signUp(email: String, name: String): SignupResult {
        TODO(
            "t4: reject an already-registered email with ALREADY_REGISTERED; " +
                "otherwise save the user, send a welcome email with WELCOME_SUBJECT, " +
                "and report SUCCESS or EMAIL_FAILED based on whether it sent"
        )
    }
}

// TODO(t5): T5ScriptedEmailSenderTest
// An EmailSender that must be explicitly stubbed before use; calling send()
// without stubbing it first should fail loudly instead of silently returning a
// default.
class ScriptedEmailSender : EmailSender {
    fun stub(result: Boolean) {
        TODO("t5: remember the result that subsequent send() calls should return")
    }

    override fun send(to: String, subject: String): Boolean {
        TODO("t5: return the stubbed result, or fail loudly if nothing was stubbed yet")
    }
}

// TODO(t6): T6ValidateSignupEmailTest
// Reject an obviously invalid email before it ever reaches the store or the
// email sender.
fun validateSignupEmail(email: String): String {
    TODO("t6: return email unchanged, but reject a blank one or one with no '@'")
}
