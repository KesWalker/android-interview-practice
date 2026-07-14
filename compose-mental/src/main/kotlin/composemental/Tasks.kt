package composemental

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

// TODO(t1): T1StatusLabelTest
// A composable is just a function of its inputs. Render "Online" when isOnline is
// true, "Offline" when it's false. When the caller's state changes, Compose reruns
// this function and the UI follows automatically: you don't push updates yourself.
@Composable
fun StatusLabel(isOnline: Boolean, modifier: Modifier = Modifier) {
    TODO("t1: Text(if (isOnline) \"Online\" else \"Offline\", modifier)")
}

// TODO(t2): T2ScopedCounterTest
// Recomposition is scoped to the smallest function that reads the changed state.
// Give this composable an inner counter (its own remember { mutableStateOf(0) })
// that increments on a button click. onParentRecompose must fire once, on the
// initial composition, and never again when the inner counter changes: clicking
// Increment should only rerun the inner scope, not this outer one.
@Composable
fun ScopedCounter(onParentRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t2: SideEffect { onParentRecompose() } here, then Column(modifier) { Text(\"Parent\"); CounterInner() }")
}

@Composable
private fun CounterInner() {
    var count by remember { mutableStateOf(0) }
    Column {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) { Text("Increment") }
    }
}

// TODO(t3): T3DashboardWithCounterTest
// This is the flip side of scoping: if you read state too high up, you drag
// everything below it into the recomposition, even parts that never use the
// value. Render a Header (which must never see `counter`) above a display of
// `counter.value`. Do not read counter.value in this function's own body; push
// the read down into a child so Header's SideEffect only fires once.
@Composable
fun DashboardWithCounter(
    counter: State<Int>,
    onHeaderRecompose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TODO("t3: Column(modifier) { Header(onHeaderRecompose); CounterDisplay(counter) } and read counter.value only inside CounterDisplay")
}

@Composable
private fun Header(onRecompose: () -> Unit) {
    SideEffect { onRecompose() }
    Text("Dashboard")
}

@Composable
private fun CounterDisplay(counter: State<Int>) {
    Text(text = "Count: ${counter.value}")
}

data class ChecklistItem(val id: Int, val label: String)

// TODO(t4): T4ReorderableChecklistTest
// Each row remembers its own checked state. Without key(), Compose matches rows
// by call position, not by identity, so when the list reorders, checked state
// stays glued to the slot instead of following the item. Wrap each row in
// key(item.id) so state travels with the item, wherever it ends up in the list.
@Composable
fun ReorderableChecklist(items: List<ChecklistItem>, modifier: Modifier = Modifier) {
    TODO("t4: Column(modifier) { items.forEach { item -> key(item.id) { ChecklistRow(item) } } }")
}

@Composable
private fun ChecklistRow(item: ChecklistItem) {
    var checked by remember { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.testTag("checkbox-${item.id}"),
        )
        Text(item.label)
    }
}

// TODO(t5): T5DeferredOffsetBoxTest
// Composition, layout and draw are separate phases. Modifier.offset(x = ...)
// reads its value during composition, so changing it recomposes this function.
// Modifier.offset { IntOffset(...) } (the lambda overload) defers the read to
// the layout phase instead: changing offsetX.value re-runs layout, but this
// composable itself never recomposes. Use the lambda overload.
@Composable
fun DeferredOffsetBox(offsetX: State<Int>, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t5: SideEffect { onRecompose() }; Box(modifier.offset { IntOffset(offsetX.value, 0) }.size(40.dp).background(Color.Blue))")
}

// TODO(t6): T6IdCardBadgeTest
// A composable's body must be idempotent: Compose may call it more than the
// number of times you'd naively expect, so anything with an observable side
// effect (like calling an ID generator) must not live directly in the body.
// Call generateId() exactly once via remember, so the id is stable across any
// number of later recompositions of this composable.
@Composable
fun IdCardBadge(generateId: () -> Int, refreshTrigger: State<Int>, modifier: Modifier = Modifier) {
    TODO("t6: val id = remember { generateId() }; read refreshTrigger.value so this recomposes; Text(\"ID: \$id\", modifier)")
}
