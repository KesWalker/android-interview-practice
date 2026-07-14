package composemental

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun StatusLabelPreview() {
    MaterialTheme { Surface { StatusLabel(isOnline = true) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 160)
@Composable
fun ScopedCounterPreview() {
    MaterialTheme { Surface { ScopedCounter(onParentRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 140)
@Composable
fun DashboardWithCounterPreview() {
    MaterialTheme { Surface { DashboardWithCounter(counter = mutableStateOf(3), onHeaderRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 200)
@Composable
fun ReorderableChecklistPreview() {
    MaterialTheme {
        Surface {
            ReorderableChecklist(
                items = listOf(ChecklistItem(1, "Buy milk"), ChecklistItem(2, "Walk the dog")),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun DeferredOffsetBoxPreview() {
    MaterialTheme { Surface { DeferredOffsetBox(offsetX = mutableStateOf(20), onRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun IdCardBadgePreview() {
    var next = 42
    MaterialTheme {
        Surface {
            IdCardBadge(generateId = { next++ }, refreshTrigger = mutableStateOf(0))
        }
    }
}
