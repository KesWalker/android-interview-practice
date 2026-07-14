# Compose Perf

Practice module for the **Compose Performance** topic on Android Interview Prep.

Recomposition is cheap, but it isn't free, and interviewers love asking "why does this
recompose, and how would you stop it?" This module works through the real stability and
performance APIs: counting recompositions with a `SideEffect`, why an unstable parameter
forces recomposition while a stable one lets Compose skip it, `@Immutable`, `ImmutableList`
from `kotlinx.collections.immutable`, deferring a hot state read into the layout phase with
`Modifier.offset { }`, `derivedStateOf` for collapsing many updates into few, and `key` /
`contentType` in `LazyColumn`. Each task is a small `@Composable` that's currently `TODO()`,
with a matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :compose-perf:test                             # run everything
./gradlew :compose-perf:test --tests "*T1RecomposeCounterTest" # one task
./gradlew :compose-perf:recordRoborazziDebug              # render every @Preview to build/previews/
```

## The tasks

All the work is in `src/main/kotlin/composeperf/Tasks.kt`. Each task has a matching
`@Preview` in `Previews.kt` you can render live in Android Studio.

1. **`RecomposeCounter`** (`T1RecomposeCounterTest`) — the technique every other task in
   this module leans on: a `SideEffect` block runs once per successful recomposition, so
   a test can count how many times a composable actually ran.
2. **`UnstablePayloadRow`** (`T2UnstablePayloadRowTest`) — `UnstablePayload` has a `var`,
   which the Compose compiler infers as UNSTABLE. A composable taking one as a parameter
   must recompose every time its caller recomposes, even when handed a brand new instance
   with identical data.
3. **`StablePayloadRow`** (`T3StablePayloadRowTest`) — same shape, but `StablePayload` is
   a `val`-only `@Immutable` data class. Compose can trust content-equal instances are
   interchangeable and skips recomposing this row when nothing actually changed.
4. **`UnstableListRow`** (`T4UnstableListRowTest`) — `List<String>` is a read-only view,
   not a provably immutable structure, so the compiler treats it as unstable too: forces
   recomposition even for a content-equal list.
5. **`StableListRow`** (`T5StableListRowTest`) — swap `List` for `kotlinx.collections.immutable`'s
   `ImmutableList`, which the compiler recognizes as genuinely stable, and the same
   content-equal case gets skipped.
6. **`DeferredOffsetBox`** (`T6DeferredOffsetBoxTest`) — `Modifier.offset { }` reads its
   state during the layout (placement) phase instead of composition, so a box can be
   dragged around continuously with **zero** recompositions of the composable itself.
7. **`LevelBadgeHost`** (`T7LevelBadgeHostTest`) — `derivedStateOf` collapses many
   upstream state changes (every point scored) into far fewer downstream recompositions
   (only when the derived level actually changes).
8. **`KeyedTodoList`** (`T8KeyedTodoListTest`) — without `key`, `LazyColumn` ties each
   item's remembered state to its position, not its identity; reordering the list can
   leave a checkbox's checked state attached to the wrong item. `key = { it.id }` fixes
   it; `contentType` lets `LazyColumn` reuse layout nodes between compatible items.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
