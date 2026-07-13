package delegation

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegation practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :delegation:test
 */

interface Notifier {
    fun send(message: String): String
    fun ping(): String
}

class SilentNotifier : Notifier {
    override fun send(message: String) = "silent: $message"
    override fun ping() = "pong"
}

// TODO(t1): T1LoudNotifierTest
// Return a Notifier that behaves exactly like `inner` for every member, except
// its `send` result should come back uppercased.
fun loudNotifier(inner: Notifier): Notifier {
    TODO("t1: wrap inner so every member forwards to it except send, which is uppercased")
}

// A distance tracker. Reads/writes go through the delegate below.
class Meter {
    var distance: Int by nonNegative(0)
}

// TODO(t2): T2MeterTest
// Return a property delegate that starts at `initial` and clamps any write
// below zero up to 0 (the stored value should never go negative).
fun nonNegative(initial: Int): ReadWriteProperty<Any?, Int> {
    TODO("t2: build a delegate whose setValue clamps writes below 0 up to 0")
}

// TODO(t3): T3ConfigCacheTest
// Return a Lazy<String> that calls `loader` only the first time its `value` is
// read, then reuses that cached result on every later read.
fun cachedConfig(loader: () -> String): Lazy<String> {
    TODO("t3: build a Lazy that computes from loader once and caches the result")
}

// A game's running high score. Writes go through the delegate below.
class Game {
    var highScore: Int by onlyIncreasing(0)
}

// TODO(t4): T4GameTest
// Return a property delegate that starts at `initial` and rejects any write
// that isn't strictly greater than the current value, silently keeping the
// old value instead.
fun onlyIncreasing(initial: Int): ReadWriteProperty<Any?, Int> {
    TODO("t4: build a delegate that rejects any write that isn't greater than the current value")
}

interface Labeled {
    val label: String
    fun describe(): String
}

class BasicLabeled(override val label: String) : Labeled {
    override fun describe() = "label: $label"
}

// TODO(t5): T5WithDisplayLabelTest
// Return a Labeled that behaves like `inner` for `describe()`, but reports
// `newLabel` from its own `label` property.
fun withDisplayLabel(inner: Labeled, newLabel: String): Labeled {
    TODO("t5: delegate to inner but override label with newLabel")
}

// A setting whose every write is appended to `history`.
class Account(initial: Int, val history: MutableList<String>) {
    var balance: Int by auditedSetting(initial, history)
}

// TODO(t6): T6AuditedSettingTest
// Return a property delegate that starts at `initial` and appends "old->new" to
// `history` every time it's written, after the write already took effect - it
// can never reject a write.
fun auditedSetting(initial: Int, history: MutableList<String>): ReadWriteProperty<Any?, Int> {
    TODO("t6: build a delegate that records \"old->new\" in history after every write")
}

// Two properties that should each read back their own declared name.
class Ledger {
    val label: String by selfNamingProperty("draft")
    val status: String by selfNamingProperty("draft")
}

class SelfNamingProperty(private val initial: String) {
    // TODO(t7): T7SelfNamingPropertyTest
    // Implement `provideDelegate` so a property delegated via
    // `selfNamingProperty(initial)` reads back as "<propertyName>=<initial>",
    // with the property's name captured once when the delegate is bound, not
    // recomputed on every read.
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<Any?, String> {
        TODO("t7: capture the property's own name at bind time and return a delegate reading \"name=initial\"")
    }
}

fun selfNamingProperty(initial: String): SelfNamingProperty = SelfNamingProperty(initial)
