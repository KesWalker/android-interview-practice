package composeperf

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun RecomposeCounterPreview() {
    MaterialTheme { Surface { RecomposeCounter(value = 3, onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun UnstablePayloadRowPreview() {
    MaterialTheme { Surface { UnstablePayloadRow(payload = UnstablePayload("Ada"), onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun StablePayloadRowPreview() {
    MaterialTheme { Surface { StablePayloadRow(payload = StablePayload("Ada"), onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun UnstableListRowPreview() {
    MaterialTheme { Surface { UnstableListRow(items = listOf("a", "b", "c"), onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun StableListRowPreview() {
    MaterialTheme { Surface { StableListRow(items = persistentListOf("a", "b", "c"), onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun DeferredOffsetBoxPreview() {
    // offset { } shifts where the box is DRAWN, not the space it occupies, so the
    // surrounding Surface needs an explicit size wide enough for the shifted box
    // to actually land inside it, otherwise it's drawn outside the visible frame.
    MaterialTheme {
        Surface(modifier = Modifier.size(width = 120.dp, height = 40.dp)) {
            DeferredOffsetBox(offsetPx = mutableStateOf(40), onRecompose = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun LevelBadgeHostPreview() {
    MaterialTheme { Surface { LevelBadgeHost(points = mutableStateOf(23), onBadgeRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 260, heightDp = 220)
@Composable
fun KeyedTodoListPreview() {
    MaterialTheme {
        Surface {
            KeyedTodoList(
                items = listOf(
                    Todo(1, "Buy milk"),
                    Todo(2, "Write tests"),
                    Todo(3, "Ship it"),
                ),
            )
        }
    }
}
