# MeasurePlace

Practice module for the **Layout: measure and place** topic on Android
Interview Prep.

You're modelling Compose's single measure pass in plain Kotlin: constraints
that only ever get tighter as they travel down the tree, sizes that resolve
back up by coercing into whatever their parent allowed, a Row-style
measure-then-place split, a guard against measuring the same child twice in
one pass, and the modifier-order gotcha where `padding().size()` and
`size().padding()` produce different totals for the same two numbers. Each
task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :measure-place:test                             # run everything
./gradlew :measure-place:test --tests "*T1CoerceToConstraintsTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/measureplace/Tasks.kt`.

1. **`coerceToConstraints`** (`T1CoerceToConstraintsTest`) - clamp a requested size into a constraints box.
2. **`childConstraintsForPadding`** (`T2ChildConstraintsForPaddingTest`) - constraints get tighter on the way down through a padding modifier.
3. **`sizeAfterPadding`** (`T3SizeAfterPaddingTest`) - sizes resolve back up: add the padding back, then coerce into the parent's own bounds.
4. **`measureRow`** (`T4MeasureRowTest`) - a Row's own size is the sum of its children's widths and the tallest child's height.
5. **`placeRow`** (`T5PlaceRowTest`) - placement is its own pass: cumulative x offsets, left to right.
6. **`measureChildrenOnce`** (`T6MeasureChildrenOnceTest`) - detect a child measured twice in a single pass, the illegal move that crashes real Compose.
7. **`sizeAfterModifierChain`** (`T7ModifierOrderTest`) - the modifier-order gotcha: `padding().size()` wraps a fixed size in padding, `size().padding()` fixes the outer size no matter what's inside it.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
