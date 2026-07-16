package properties

/**
 * Properties, lazy & lateinit practice.
 *
 * Every property in this module is DECLARED BY YOU, from scratch: the whole
 * topic is how a property is declared (a delegate, a modifier, a custom
 * accessor), so none of them are stubbed. Each task's test finds your
 * declaration by reflection once it exists, and until then it fails with a
 * pointer to what's missing. The classes and plain functions around them are
 * given - they're scenario, not the lesson.
 *
 * Run a single test class from the gutter in Android Studio, or all of them:
 *
 *     ./gradlew :properties:test
 */

// TODO(t1): T1LazyConfigTest
// Declare a val `value: String` in LazyConfig whose result comes from calling
// `loader()` - but computed only on the FIRST read, then reused forever. No
// ordinary getter can do that without hand-rolling a cache; Kotlin ships a
// delegate for exactly this.
class LazyConfig(private val loader: () -> String)

// TODO(t2): T2SessionGreetingTest
// Declare a `username: String` property in UserSession that is assigned some
// time after construction, is NOT nullable, and throws if read before being
// set. Then implement greeting(): "Hello, <username>!" once username is set,
// else "Hello, stranger!" - there's a way to ask whether it's been set yet.
class UserSession {
    fun greeting(): String {
        TODO("t2: greet by name once username is set, else greet a stranger")
    }
}

// TODO(t3): T3TrimmedBioTest
// Declare a var `bio: String` in Profile (starting as "") where whatever is
// assigned gets stored with its leading/trailing whitespace removed. The
// transformation happens AT assignment - a custom setter, writing through the
// backing field.
class Profile

// TODO(t4): T4TodoListItemsTest
// Declare a val `items: List<String>` in TodoList exposing `_items` read-only
// to the outside while `add` keeps mutating the same list underneath - the
// backing-property pattern. The test checks it's a live view, not a copy.
class TodoList {
    private val _items = mutableListOf<String>()

    fun add(item: String) {
        _items.add(item)
    }
}

// TODO(t5): T5ConfiguredAttemptsTest
// Declare a var `maxAttempts: Int` in RetryPolicy that is assigned after
// construction and throws IllegalStateException if read first. lateinit can't
// do it - it doesn't work for primitive types like Int. The standard library
// has a delegate for this one too.
class RetryPolicy

// TODO(t6): T6RectangleAreaTest
// Declare a val `area: Int` in Rectangle that always reflects the CURRENT
// width and height - no stored value, no cache, recomputed on every read.
class Rectangle(var width: Int, var height: Int)

// TODO(t7): T7AccountTest
// Declare a var `balance: Int` in Account, starting at 0, that anyone can
// READ but only Account itself can CHANGE - the test fails if it finds a
// public setter. Then implement deposit and withdraw (clamping at 0), the
// only code allowed to touch it.
class Account {
    fun deposit(amount: Int) {
        TODO("t7: add `amount` to the balance")
    }

    fun withdraw(amount: Int) {
        TODO("t7: subtract `amount` from the balance, clamping at 0")
    }
}
