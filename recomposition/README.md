# Recomposition

Practice module for the **Compose Mental Model** topic on Android Interview
Prep.

There's no Compose dependency here on purpose: you build a tiny model of it
by hand. A node tree where each node declares which state keys it reads, a
three-phase model (composition, layout, draw) for deciding what a state
read actually invalidates, and the positional-memoization rule that
`key()` exists to fix when a list gets reordered. Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :recomposition:test                              # run everything
./gradlew :recomposition:test --tests "*T1NodeRecomposesTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/recomposition/Tasks.kt`.

1. **`nodeRecomposes`** (`T1NodeRecomposesTest`) - a single node's skip rule: does it read any of the changed state?
2. **`invalidatedPhase`** (`T2InvalidatedPhaseTest`) - which of composition, layout or draw a given state read invalidates.
3. **`recomposingNodeIds`** (`T3RecomposingNodeIdsTest`) - apply the skip rule across a whole tree, independently per node.
4. **`recomposeWithoutKey`** (`T4RecomposeWithoutKeyTest`) - model the positional-memoization bug: state stays glued to a list slot across a reorder.
5. **`recomposeWithKey`** (`T5RecomposeWithKeyTest`) - fix it: state follows the item's identity instead.
6. **`invalidationFor`** (`T6InvalidationForTest`) - combine the phase model with a node's reads to find the priciest real invalidation a change causes.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
