package framebudget

/**
 * Frame Budget practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :frame-budget:test
 */

/** How one rendered frame's total duration got classified. */
enum class FrameClass { SMOOTH, JANKY, FROZEN }

/** The dashboard-style summary of a run of frames. */
data class FrameStats(
    val jankRatePercent: Double,
    val p50: Double,
    val p90: Double,
    val p99: Double
)

/** One rendered frame, optionally broken down by the phase that produced it. */
data class Frame(val durationMs: Double, val phases: Map<String, Double> = emptyMap())

/** A frame at or above this duration is a full freeze, not just jank. */
const val FROZEN_THRESHOLD_MS = 700.0

// TODO(t1): T1FrameBudgetTest
// Compute the frame budget in milliseconds for a display refreshing at
// `refreshHz` times per second: how long a single frame has to render
// before it's late.
fun frameBudgetMs(refreshHz: Int): Double {
    TODO("t1: 1000.0 / refreshHz")
}

// TODO(t2): T2ClassifyFrameTest
// Classify a single frame's duration against `budgetMs`. FROZEN at
// FROZEN_THRESHOLD_MS or above, regardless of budget. JANKY if it's over
// budget but below the frozen threshold. SMOOTH if it's at or under budget.
fun classifyFrame(durationMs: Double, budgetMs: Double): FrameClass {
    TODO("t2: FROZEN >= 700ms, JANKY if over budget, else SMOOTH")
}

// TODO(t3): T3AnalyzeFramesTest
// Summarize a run of frame durations the way a dashboard would.
// jankRatePercent: the percentage (0-100) of frames strictly over
// `budgetMs` (janky or frozen both count). p50/p90/p99: sort the durations
// ascending, then for percentile p use the nearest-rank method: rank =
// ceil(p / 100.0 * n), clamped to [1, n], and return the value at that rank
// (1-indexed).
fun analyzeFrames(durationsMs: List<Double>, budgetMs: Double): FrameStats {
    TODO("t3: jank rate plus p50/p90/p99 by nearest-rank on the sorted durations")
}

// TODO(t4): T4LongestJankStreakTest
// Find the length of the longest run of *consecutive* frames that are each
// over `budgetMs` (janky or frozen both count as blowing the budget). A
// single dropped frame surrounded by smooth ones is a streak of 1; that's a
// very different user experience from ten in a row, even at the same
// overall jank rate.
fun longestJankStreak(durationsMs: List<Double>, budgetMs: Double): Int {
    TODO("t4: longest consecutive run of frames with durationMs > budgetMs")
}

// TODO(t5): T5AttributeJankTest
// Given one frame's per-phase breakdown, decide which phase to blame. If
// the frame's total durationMs is at or under `budgetMs`, it isn't janky:
// return null. Otherwise return the key of the phase with the largest
// value in `frame.phases`, the one most responsible for blowing the
// budget.
fun attributeJank(frame: Frame, budgetMs: Double): String? {
    TODO("t5: null if not janky, else the phases entry with the largest value")
}
