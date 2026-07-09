package properties

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

// TODO(t1): LazyConfigTest
// `value` should call `loader` to get its result, but only the first time it's
// read, no matter how many times it's read after that.
class LazyConfig(private val loader: () -> String) {
    val value: String
        get() = TODO("t1: return loader()'s result, computed once and reused on every later read")
}

// TODO(t2): SessionGreetingTest
// Greet `username` by name once it's been assigned, otherwise greet a stranger.
class UserSession {
    lateinit var username: String

    fun greeting(): String {
        TODO("t2: return \"Hello, <username>!\" once username is set, else \"Hello, stranger!\"")
    }
}

// TODO(t3): TrimmedBioTest
// Whatever `bio` is assigned, what's actually stored (and later read back)
// should have its leading and trailing whitespace removed.
class Profile {
    var bio: String = ""
        set(value) {
            TODO("t3: store `value` with its leading/trailing whitespace removed")
        }
}

// TODO(t4): TodoListItemsTest
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
