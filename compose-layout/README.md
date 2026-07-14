# ComposeLayout

Practice module for the **Compose Layout System** topic on Android Interview Prep.

You're building the layout primitives that come up in almost every Compose
interview: picking the right `Arrangement`, understanding why modifier order
changes what gets painted, splitting space with `Modifier.weight`, writing a
custom `Layout` that measures and places children by hand, matching sizes
across siblings with `IntrinsicSize`, and keeping per-row state stable in a
`LazyColumn` when the list reorders. Each task is a composable that's
currently a `TODO()`, with a matching test that's **red**. Make each test
**green**, one at a time, then check the live preview to see it for real.

## How to run it

Open the repo root in Android Studio and use the green run gutter next to a
test class, or from a terminal:

```bash
./gradlew :compose-layout:test                            # run everything
./gradlew :compose-layout:test --tests "*T1ArrangementTest"   # one task
```

To see your work rendered as an image (the same thing the AI tutor looks at):

```bash
./gradlew :compose-layout:recordRoborazziDebug
open compose-layout/build/previews   # PNGs, one per @Preview
```

An unfinished task's preview is skipped rather than failing the build, so
previews appear one by one as you implement each task. You can also just put
your cursor on any `@Preview` function in `Previews.kt` and use Android
Studio's built-in preview pane for the same live view.

## The tasks

All the work is in `src/main/kotlin/composelayout/Tasks.kt`.

1. **`ArrangedRow`** (`T1ArrangementTest`) - pick the `Arrangement` that lands
   three fixed-size chips evenly spaced across a 300dp `Row`, flush with both
   edges.
2. **`ModifierOrderBox`** (`T2ModifierOrderTest`) - chain `.background()` and
   `.padding()` in the right order. Paint-before-inset fills the whole box
   edge to edge; inset-before-paint leaves a transparent margin. Same two
   modifiers, opposite pixels.
3. **`WeightedRow`** (`T3WeightedRowTest`) - split a 300dp `Row` into three
   children with `Modifier.weight(1f / 2f / 1f)` so the widths land at a
   1 : 2 : 1 ratio.
4. **`CascadeLayout`** (`T4CascadeLayoutTest`) - drop down to the low-level
   `Layout` composable and place children by hand: measure each child once,
   then place them in a diagonal cascade, 20dp right and 20dp down per step.
5. **`IntrinsicWidthColumn`** (`T5IntrinsicWidthTest`) - make a divider match
   the width of the text above it without ever giving the divider an
   explicit width, using `IntrinsicSize.Max` on the parent `Column`.
6. **`ToggleList`** (`T6StableKeysTest`) - the one an interviewer would
   actually ask: give each row of a `LazyColumn` its own `remember`ed
   expand/collapse state, keyed by a stable item id via
   `items(list, key = { it.id })`, so a row's state follows its item through
   a reorder instead of sticking to whatever list position gets drawn there.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
