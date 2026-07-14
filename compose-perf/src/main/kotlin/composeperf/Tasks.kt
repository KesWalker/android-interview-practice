package composeperf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

// TODO(t1): T1RecomposeCounterTest
// Every recomposition-counting test in this module leans on the same trick: a
// SideEffect block runs once per successful (not skipped) recomposition, after
// the composable's changes have been applied. Call onRecompose() from a
// SideEffect, then render the value so the test can see it too.
@Composable
fun RecomposeCounter(value: Int, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t1: SideEffect { onRecompose() } then Text(\"Value: \$value\", modifier)")
}

// A class with a `var` property is inferred UNSTABLE by the Compose compiler: it
// can't prove the property won't change out from under it later, so a composable
// that takes one as a parameter must recompose every time its caller recomposes,
// even when two instances hold identical data.
class UnstablePayload(var label: String)

// TODO(t2): T2UnstablePayloadRowTest
// Render payload.label and count recompositions. Because UnstablePayload is
// unstable, this will recompose on every parent recomposition, even when the
// caller passes a new instance with the exact same label.
@Composable
fun UnstablePayloadRow(payload: UnstablePayload, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t2: SideEffect { onRecompose() } then Text(payload.label, modifier)")
}

// @Immutable is a promise to the compiler: once built, this instance's public
// properties never change. Paired with a `val`-only data class (content equals),
// Compose can now trust that two content-equal instances are interchangeable,
// and skip recomposing a consumer that receives one it's already seen.
@Immutable
data class StablePayload(val label: String)

// TODO(t3): T3StablePayloadRowTest
// Same shape as UnstablePayloadRow, but the parameter is stable this time. Render
// payload.label and count recompositions like before.
@Composable
fun StablePayloadRow(payload: StablePayload, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t3: SideEffect { onRecompose() } then Text(payload.label, modifier)")
}

// TODO(t4): T4UnstableListRowTest
// List<String> is a plain read-only view, not a deeply immutable structure: the
// backing object could still be mutated elsewhere without Compose knowing, so the
// compiler treats it as UNSTABLE. Render items.size and count recompositions.
@Composable
fun UnstableListRow(items: List<String>, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t4: SideEffect { onRecompose() } then Text(\"Items: \${items.size}\", modifier)")
}

// TODO(t5): T5StableListRowTest
// Same shape as UnstableListRow, but items is typed as kotlinx.collections.immutable's
// ImmutableList, which the Compose compiler recognizes as genuinely stable. Render
// items.size and count recompositions like before.
@Composable
fun StableListRow(items: ImmutableList<String>, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t5: SideEffect { onRecompose() } then Text(\"Items: \${items.size}\", modifier)")
}

// TODO(t6): T6DeferredOffsetBoxTest
// offsetPx changes on every drag/scroll tick, way too often to recompose over.
// Modifier.offset { } takes a lambda that reads its state during the LAYOUT
// (placement) phase instead of composition, so reading offsetPx.value here never
// triggers a recomposition of this composable, only a re-placement. Count
// recompositions with a SideEffect like the other tasks, then render a small box
// whose position is driven by Modifier.offset { IntOffset(offsetPx.value, 0) }.
@Composable
fun DeferredOffsetBox(offsetPx: State<Int>, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t6: SideEffect { onRecompose() } then Box(modifier.offset { IntOffset(offsetPx.value, 0) }.size(24.dp).background(Color.Red))")
}

// TODO(t7): T7LevelBadgeHostTest
// points changes on every single point scored, but the badge should only actually
// recompose when the LEVEL (points / 10) changes. Wrap the derived value in
// remember { derivedStateOf { } } so reads of `level` only invalidate downstream
// readers when the divided value flips, not on every points change, then hand it
// to LevelText.
@Composable
fun LevelBadgeHost(points: State<Int>, onBadgeRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t7: val level by remember { derivedStateOf { points.value / 10 } }; then LevelText(level, onBadgeRecompose, modifier)")
}

@Composable
private fun LevelText(level: Int, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    SideEffect { onRecompose() }
    Text(text = "Level: $level", modifier = modifier)
}

data class Todo(val id: Int, val title: String)

@Composable
private fun TodoRow(todo: Todo, modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    Row(modifier = modifier) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.testTag("checkbox-${todo.id}"),
        )
        Text(text = todo.title, modifier = Modifier.testTag("title-${todo.id}"))
    }
}

// TODO(t8): T8KeyedTodoListTest
// Without an explicit key, LazyColumn ties each item's remembered state to its
// POSITION in the list, not its identity: reorder the list and a checkbox's
// checked state can end up attached to the wrong todo. Pass key = { it.id } so
// Compose moves each item's composition (and its state) along with its id when
// the list reorders. contentType groups items so LazyColumn only reuses layout
// nodes between items of a compatible shape; every row here is the same shape,
// so a constant works.
@Composable
fun KeyedTodoList(items: List<Todo>, modifier: Modifier = Modifier) {
    TODO("t8: LazyColumn(modifier) { items(items, key = { it.id }, contentType = { \"todo\" }) { todo -> TodoRow(todo) } }")
}
