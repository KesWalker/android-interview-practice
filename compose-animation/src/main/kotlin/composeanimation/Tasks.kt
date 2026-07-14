package composeanimation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

// TODO(t1): T1PulseDotTest
// Animate `size` toward 96.dp (expanded) or 32.dp (collapsed) with
// animateDpAsState, and `color` toward Color(0xFF2563EB) (expanded) or
// Color(0xFF9CA3AF) (collapsed) with animateColorAsState. Give BOTH
// animationSpec = tween(durationMillis = 400) so the timing is predictable.
// Render a circular Box with that size and color, tagged "dot".
@Composable
fun PulseDot(expanded: Boolean, modifier: Modifier = Modifier) {
    TODO(
        "t1: val size by animateDpAsState(if (expanded) 96.dp else 32.dp, tween(400)); " +
            "val color by animateColorAsState(if (expanded) Color(0xFF2563EB) else Color(0xFF9CA3AF), tween(400)); " +
            "Box(modifier.testTag(\"dot\").size(size).background(color, CircleShape))",
    )
}

// TODO(t2): T2ToggleBannerTest
// Wrap the banner text in AnimatedVisibility, keyed on `visible`. Fade and
// expand in, fade and shrink out, both over 300ms. Tag the banner text "banner"
// so tests can find it.
@Composable
fun ToggleBanner(visible: Boolean, modifier: Modifier = Modifier) {
    TODO(
        "t2: AnimatedVisibility(visible, modifier, enter = fadeIn(tween(300)) + expandVertically(tween(300)), " +
            "exit = fadeOut(tween(300)) + shrinkVertically(tween(300))) { Text(\"Saved!\", Modifier.testTag(\"banner\")) }",
    )
}

enum class CardState { Collapsed, Expanded }

// TODO(t3): T3ExpandableCardTest
// Use updateTransition(state, label = "card") to drive BOTH `width` and
// `height` off the same CardState with transition.animateDp, each over 400ms.
// Collapsed = 80.dp x 40.dp, Expanded = 200.dp x 120.dp. Render a Box sized
// to (width, height), tagged "card".
@Composable
fun ExpandableCard(state: CardState, modifier: Modifier = Modifier) {
    TODO(
        "t3: val transition = updateTransition(state, label = \"card\"); " +
            "val width by transition.animateDp(transitionSpec = { tween(400) }, label = \"width\") { if (it == CardState.Expanded) 200.dp else 80.dp }; " +
            "val height by transition.animateDp(transitionSpec = { tween(400) }, label = \"height\") { if (it == CardState.Expanded) 120.dp else 40.dp }; " +
            "Box(modifier.testTag(\"card\").size(width, height).background(Color(0xFF10B981)))",
    )
}

// TODO(t4): T4SlidingChipTest
// Hold an Animatable(0f) for the x offset. A "Right" button animates it to
// 240f with tween(600); a "Left" button animates it to 0f with spring(). Read
// the value in the LAYOUT offset lambda (Modifier.offset { IntOffset(...) })
// so the chip actually moves without recomposing. Also show the rounded value
// as Text tagged "chipValue" so its progress is easy to verify. Calling
// animateTo again while one is already running retargets it mid-flight (the
// old animation is cancelled).
@Composable
fun SlidingChip(modifier: Modifier = Modifier) {
    TODO(
        "t4: val offsetX = remember { Animatable(0f) }; val scope = rememberCoroutineScope(); " +
            "Box(Modifier.testTag(\"chip\").offset { IntOffset(offsetX.value.roundToInt(), 0) }.size(32.dp).background(Color(0xFFEF4444))); " +
            "Text(\"Offset: \${offsetX.value.roundToInt()}\", Modifier.testTag(\"chipValue\")); " +
            "Button(onClick = { scope.launch { offsetX.animateTo(240f, tween(600)) } }) { Text(\"Right\") }; " +
            "Button(onClick = { scope.launch { offsetX.animateTo(0f, spring()) } }) { Text(\"Left\") }",
    )
}

// TODO(t5): T5ExpandableListTest
// Put animateContentSize(tween(400)) on the Column so it grows and shrinks
// smoothly whenever the number of children changes, instead of snapping.
// Tag the Column "list".
@Composable
fun ExpandableList(expanded: Boolean, modifier: Modifier = Modifier) {
    TODO(
        "t5: Column(modifier.testTag(\"list\").animateContentSize(tween(400)).padding(8.dp)) { " +
            "Text(\"Item 1\"); if (expanded) { Text(\"Item 2\"); Text(\"Item 3\") } }",
    )
}

enum class LoadState { Loading, Loaded }

// TODO(t6): T6StatusPanelTest
// Use AnimatedContent(targetState = state) with a transitionSpec that
// crossfades (fadeIn togetherWith fadeOut, both 300ms). Show "Loading..." for
// LoadState.Loading and "Done!" for LoadState.Loaded. Tag the AnimatedContent
// itself "panel".
@Composable
fun StatusPanel(state: LoadState, modifier: Modifier = Modifier) {
    TODO(
        "t6: AnimatedContent(state, modifier.testTag(\"panel\"), transitionSpec = { fadeIn(tween(300)) togetherWith fadeOut(tween(300)) }) { s -> " +
            "when (s) { LoadState.Loading -> Text(\"Loading...\"); LoadState.Loaded -> Text(\"Done!\") } }",
    )
}
