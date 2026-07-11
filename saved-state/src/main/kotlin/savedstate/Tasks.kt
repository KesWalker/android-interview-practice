package savedstate

/**
 * SavedState practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :saved-state:test
 */

/** Thrown when a saved-state map is too large to persist. */
class StateTooLargeException(message: String) : RuntimeException(message)

/** A UI state split by where each piece of it lives. */
data class UiState(
    val viewModelField: String?,
    val savedStateField: String?,
    val diskField: String?
)

// TODO(t1): T1SaveStateTest
// Given a raw UI-state map, keep only the entries whose value can actually be
// written into saved state: String, Int, Long, Double, Float, Boolean, null,
// or a List whose elements are all one of those types. Drop every other
// entry.
fun saveState(state: Map<String, Any?>): Map<String, Any?> {
    TODO("t1: keep only entries whose value is a saved-state-safe type")
}

// TODO(t2): T2RestoreStateTest
// Rebuild the current UI state from `defaults` (the full set of fields this
// screen needs) and `saved` (what was recovered from saved state). For every
// key in `defaults`, use the value from `saved` when that key is present
// there, otherwise fall back to the value from `defaults`. Keys in `saved`
// that aren't in `defaults` are ignored.
fun restoreState(saved: Map<String, Any?>, defaults: Map<String, Any?>): Map<String, Any?> {
    TODO("t2: fill in defaults' keys from saved, falling back to defaults")
}

// TODO(t3): T3SizeLimitTest
// Estimate the byte size of `state`: 2 bytes per character in every key and
// every String value, 4 bytes for an Int or Float value, 8 bytes for a Long
// or Double value, 1 byte for a Boolean value, 0 bytes for a null value, and
// for a List value the sum of its elements' byte sizes by those same rules.
// If the total is greater than `maxBytes`, throw StateTooLargeException.
// Otherwise return `state` unchanged.
fun ensureWithinSizeLimit(state: Map<String, Any?>, maxBytes: Int): Map<String, Any?> {
    TODO("t3: throw StateTooLargeException when state's estimated size exceeds maxBytes")
}

// TODO(t4): T4RecreateTest
// Simulate what `state` looks like after Android recreates the screen. When
// `killedProcess` is true, the in-memory `viewModelField` is lost (becomes
// null) while `savedStateField` and `diskField` are unaffected. When
// `killedProcess` is false (a config change with the process still alive),
// `state` comes back completely unchanged.
fun recreate(state: UiState, killedProcess: Boolean): UiState {
    TODO("t4: clear viewModelField only when killedProcess is true")
}
