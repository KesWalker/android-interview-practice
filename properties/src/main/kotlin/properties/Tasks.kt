package properties

import kotlin.properties.Delegates

/**
 * Properties, lazy & lateinit practice.
 *
 * Each type below is broken or unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to make the code
 * work as described so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :properties:test
 */

// TODO(t1): T1LazyConfigTest
// `value` should call `loader` to get its result, but only the first time it's
// read, no matter how many times it's read after that.
class LazyConfig(private val loader: () -> String) {
    val value: String
        get() = TODO("t1: return loader()'s result, computed once and reused on every later read")
}

// TODO(t2): T2SessionGreetingTest
// Greet `username` by name once it's been assigned, otherwise greet a stranger.
class UserSession {
    lateinit var username: String

    fun greeting(): String {
        TODO("t2: return \"Hello, <username>!\" once username is set, else \"Hello, stranger!\"")
    }
}

// TODO(t3): T3TrimmedBioTest
// Whatever `bio` is assigned, what's actually stored (and later read back)
// should have its leading and trailing whitespace removed.
class Profile {
    var bio: String = ""
        set(value) {
            TODO("t3: store `value` with its leading/trailing whitespace removed")
        }
}

// TODO(t4): T4TodoListItemsTest
// Expose the items this class holds as a read-only list, while still letting
// this class add to them internally via `add`.
class TodoList {
    private val _items = mutableListOf<String>()

    val items: List<String>
        get() = TODO("t4: expose the stored items")

    fun add(item: String) {
        _items.add(item)
    }
}

class RetryPolicy {
    var maxAttempts: Int by Delegates.notNull()
}

// TODO(t5): T5ConfiguredAttemptsTest
// Return `policy.maxAttempts`. RetryPolicy uses `by Delegates.notNull<Int>()`
// instead of lateinit, since lateinit can't hold a primitive Int.
fun configuredAttempts(policy: RetryPolicy): Int {
    TODO("t5: return the policy's configured maxAttempts")
}

// TODO(t6): T6RectangleAreaTest
// `area` should always reflect the rectangle's *current* width and height -
// implement its getter, with no stored/cached value.
class Rectangle(var width: Int, var height: Int) {
    val area: Int
        get() = TODO("t6: compute the area fresh from the current width and height")
}

// TODO(t7): T7AccountTest
// Implement deposit and withdraw so they adjust balance from inside the class,
// clamping withdrawals at 0. balance's setter is already private, so this is
// the only place allowed to change it.
class Account {
    var balance: Int = 0
        private set

    fun deposit(amount: Int) {
        TODO("t7: add `amount` to the balance")
    }

    fun withdraw(amount: Int) {
        TODO("t7: subtract `amount` from the balance, clamping at 0")
    }
}
