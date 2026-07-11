package delegation

import kotlin.properties.ReadWriteProperty

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
