# EffectLifecycle

Practice module for the **Side Effects** topic on Android Interview Prep.

You're building plain-Kotlin models of Compose's effect APIs: a
`LaunchedEffect`-alike that relaunches its coroutine when its keys change
and cancels it on leaving, a `DisposableEffect`-alike that disposes the
previous effect before running a new one (and on leaving), a
`rememberUpdatedState`-alike so a long-running effect always reads the
latest captured value, and a `SideEffect`-alike that only fires after a
commit actually succeeds. Each task is a small function that's currently
broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :effect-lifecycle:test                             # run everything
./gradlew :effect-lifecycle:test --tests "*T1LaunchedEffectTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/effects/Tasks.kt`.

1. **`LaunchedEffectRunner.launch`** (`T1LaunchedEffectTest`) - cancel and relaunch the running job only when its keys change.
2. **`LaunchedEffectRunner.leave`** (`T2CancelOnLeaveTest`) - cancel the running job when the composable leaves.
3. **`DisposableEffectRunner.run`** (`T3DisposableEffectTest`) - dispose the previous effect before running a new one on a key change.
4. **`DisposableEffectRunner.leave`** (`T4DisposableLeaveTest`) - dispose the current effect when the composable leaves.
5. **`UpdatedStateRef.update`** (`T5RememberUpdatedStateTest`) - keep a slot up to date so a long-running effect reads the latest value, not the one it captured at launch.
6. **`commitThenPublish`** (`T6SideEffectTest`) - only publish a side effect after the commit it depends on actually succeeds.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
