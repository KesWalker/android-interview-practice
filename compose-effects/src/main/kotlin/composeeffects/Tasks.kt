package composeeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Real Compose side-effect APIs.
 *
 * Every function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically so
 * its test goes GREEN, then check the live preview in Android Studio. Run a single
 * test class from the gutter, or from a terminal:
 *
 *     ./gradlew :compose-effects:test
 */

// TODO(t1): T1KeyedEffectCounterTest
// Launch a coroutine keyed on `key`. It should bump `started` the instant it
// launches, then delay(delayMs) and bump `completed`. Because it's keyed, a new
// value of `key` must cancel whatever effect was still delaying and start a fresh
// one, so completed only ever reflects the effect that actually finished.
@Composable
fun KeyedEffectCounter(key: Int, delayMs: Long, modifier: Modifier = Modifier) {
    TODO("t1: LaunchedEffect(key) { started++; delay(delayMs); completed++ }, render both counters")
}

// TODO(t2): T2ClickLaunchedCounterTest
// A Button whose onClick launches a coroutine (via rememberCoroutineScope) that
// delays and then increments count. LaunchedEffect can't do this: it has no
// click to key off, it only fires on composition/key-change. Each click should
// start its own independent coroutine.
@Composable
fun ClickLaunchedCounter(delayMs: Long, modifier: Modifier = Modifier) {
    TODO("t2: rememberCoroutineScope(), Button(onClick = { scope.launch { delay(delayMs); count++ } })")
}

/** A plain, non-Compose object a DisposableEffect can register itself with. */
class EffectListener {
    var registered: Int = 0
    var disposed: Int = 0
}

// TODO(t3): T3DisposableListenerEffectTest
// DisposableEffect(key) that bumps listener.registered when it enters, and bumps
// listener.disposed from onDispose. onDispose must run both when `key` changes
// (the old effect is torn down before the new one registers) and when this
// composable leaves composition entirely.
@Composable
fun DisposableListenerEffect(key: Int, listener: EffectListener, modifier: Modifier = Modifier) {
    TODO("t3: DisposableEffect(key) { listener.registered++; onDispose { listener.disposed++ } }")
}

// TODO(t4): T4TimeoutNotifierTest
// After delayMs, call onTimeout(). The catch: onTimeout can change across
// recompositions (a new lambda instance), but LaunchedEffect(Unit) only launches
// once and never restarts. Closing over the `onTimeout` parameter directly inside
// the coroutine freezes it to whichever lambda existed when the effect first
// launched. rememberUpdatedState(onTimeout) gives you a value that always reads
// the latest lambda, even from a coroutine that started long ago.
@Composable
fun TimeoutNotifier(delayMs: Long, onTimeout: () -> Unit) {
    TODO("t4: val latest by rememberUpdatedState(onTimeout); LaunchedEffect(Unit) { delay(delayMs); latest() }")
}

// TODO(t5): T5SnapshotFlowTextTrackerTest
// Hold `text` in remember, wire it to a TextField (use Modifier.testTag("snapshotFlowInput")
// so the test can find it), and use LaunchedEffect(Unit) { snapshotFlow { text }.collect { ... } }
// to turn every change to `text` into a Flow emission. Track how many values the
// flow has emitted (snapshotFlow emits once immediately with the current value,
// then again on every later change) and render it as "Emitted: <n>".
@Composable
fun SnapshotFlowTextTracker(modifier: Modifier = Modifier) {
    TODO("t5: snapshotFlow { text }.collect { emissionCount++ } inside LaunchedEffect(Unit)")
}

/** A fake data source: after a delay, `fetch` returns a fixed value. */
class DataSource(private val result: String, private val delayMs: Long) {
    suspend fun fetch(): String {
        delay(delayMs)
        return result
    }
}

// TODO(t6): T6ProducedValueLabelTest
// Turn source.fetch() (a plain suspend function) into Compose State with
// produceState(initialValue = "Loading", key1 = source) { value = source.fetch() },
// and render the resulting state as text: "Loading" until the fetch completes,
// then the fetched value.
@Composable
fun ProducedValueLabel(source: DataSource, modifier: Modifier = Modifier) {
    TODO("t6: val state by produceState(initialValue = \"Loading\", source) { value = source.fetch() }")
}
