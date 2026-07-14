# Compose Effects

Practice module for the **Side Effects** topic on Android Interview Prep.

Regular composable code runs during composition, and Compose is free to run it
again, drop it, or replay it out of order. Side effects (coroutines, listener
registration, anything that touches the outside world) need their own APIs so
they run at the right time and get cleaned up correctly. This module walks you
through the ones you'll actually reach for: `LaunchedEffect`,
`rememberCoroutineScope`, `DisposableEffect`, `rememberUpdatedState`,
`snapshotFlow`, and `produceState`.

Each task is a small composable that's currently a `TODO()`, with a matching
test that's **red**. Implement the composable so its test goes **green**, then
open the matching preview in Android Studio to see it live.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run
gutter next to a test class, or from a terminal:

```bash
./gradlew :compose-effects:test                              # run everything
./gradlew :compose-effects:test --tests "*T1KeyedEffectCounterTest"   # one task
./gradlew :compose-effects:recordRoborazziDebug              # render every @Preview to build/previews/
```

## The tasks

All the work is in `src/main/kotlin/composeeffects/Tasks.kt`. Previews live in
`Previews.kt`.

1. **`KeyedEffectCounter`** (`T1KeyedEffectCounterTest`) — `LaunchedEffect(key)`: launch a
   coroutine keyed on a value, and prove that changing the key cancels whatever
   was still in flight instead of letting it finish.
2. **`ClickLaunchedCounter`** (`T2ClickLaunchedCounterTest`) — `rememberCoroutineScope()`:
   launch a coroutine from a button click, the one thing `LaunchedEffect` can't
   do because it has no event to key off.
3. **`DisposableListenerEffect`** (`T3DisposableListenerEffectTest`) — `DisposableEffect`:
   register something on entering composition, and guarantee `onDispose` runs
   both on key change and when the composable leaves composition for good.
4. **`TimeoutNotifier`** (`T4TimeoutNotifierTest`) — `rememberUpdatedState`: make a
   long-running effect call the *latest* version of a callback, not the one it
   closed over when it first launched.
5. **`SnapshotFlowTextTracker`** (`T5SnapshotFlowTextTrackerTest`) — `snapshotFlow`: turn
   reads of Compose `State` into a cold `Flow` you can `collect`.
6. **`ProducedValueLabel`** (`T6ProducedValueLabelTest`) — `produceState`: turn a plain
   suspend function into Compose `State`, showing a loading value until it
   resolves.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
