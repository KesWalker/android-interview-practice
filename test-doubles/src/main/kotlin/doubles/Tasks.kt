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

// TODO(t1): InMemoryUserStoreTest
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

// TODO(t2): StubEmailSenderTest
// An EmailSender that always returns the same preconfigured result, no matter what it's asked to send.
class StubEmailSender(private val result: Boolean) : EmailSender {
    override fun send(to: String, subject: String): Boolean {
        TODO("t2: return the preconfigured result, ignoring to/subject")
    }
}

// TODO(t3): SpyEmailSenderTest
// An EmailSender that records every call it receives, then forwards it to a wrapped EmailSender and returns its result.
class SpyEmailSender(private val delegate: EmailSender) : EmailSender {
    val sentMessages = mutableListOf<Pair<String, String>>()

    override fun send(to: String, subject: String): Boolean {
        TODO("t3: record (to, subject), then delegate the call and return its result")
    }
}

const val WELCOME_SUBJECT = "Welcome!"

enum class SignupResult { ALREADY_REGISTERED, SUCCESS, EMAIL_FAILED }

// TODO(t4): SignupServiceTest
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
