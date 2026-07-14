package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 280, heightDp = 160)
@Composable
fun KeyedEffectCounterPreview() {
    MaterialTheme { Surface { KeyedEffectCounter(key = 0, delayMs = 1000L) } }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 160)
@Composable
fun ClickLaunchedCounterPreview() {
    MaterialTheme { Surface { ClickLaunchedCounter(delayMs = 1000L) } }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 100)
@Composable
fun DisposableListenerEffectPreview() {
    MaterialTheme { Surface { DisposableListenerEffect(key = 0, listener = EffectListener()) } }
}

@Preview(showBackground = true, widthDp = 280, heightDp = 100)
@Composable
fun TimeoutNotifierPreview() {
    MaterialTheme { Surface { TimeoutNotifier(delayMs = 1000L, onTimeout = {}) } }
}

@Preview(showBackground = true, widthDp = 280, heightDp = 160)
@Composable
fun SnapshotFlowTextTrackerPreview() {
    MaterialTheme { Surface { SnapshotFlowTextTracker() } }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 100)
@Composable
fun ProducedValueLabelPreview() {
    MaterialTheme {
        Surface { ProducedValueLabel(source = DataSource(result = "Preview value", delayMs = 1000L)) }
    }
}
