# The Compose Mental Model

Practice module for the **Compose Mental Model** topic on Android Interview Prep.

Compose isn't a templating language bolted onto Kotlin: a `@Composable` function is a
function of its inputs, and Compose decides when to rerun it. This module makes that
model concrete with the actual recomposition machinery: `SideEffect` counters that
prove exactly when (and when not) a function reruns, `key()` for identity across
reorders, and the deferred-read trick that turns a recomposition into a cheap layout
pass. Each task is a small `@Composable` that's currently `TODO()`, with a matching
test that's **red**. Make each test **green**, one at a time, and you'll have the
mental model interviewers actually probe for.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :compose-mental:test                              # run everything
./gradlew :compose-mental:test --tests "*T1StatusLabelTest"   # one task
./gradlew :compose-mental:recordRoborazziDebug               # render every @Preview to build/previews/
```

## The tasks

All the work is in `src/main/kotlin/composemental/Tasks.kt`. Each task has a matching
`@Preview` in `Previews.kt` you can render live in Android Studio.

1. **`StatusLabel`** (`T1StatusLabelTest`) — a composable is a function of state: render
   "Online"/"Offline" from a boolean, and watch it update automatically when the
   caller's state changes.
2. **`ScopedCounter`** (`T2ScopedCounterTest`) — recomposition is scoped to the
   smallest function that reads the changed state. Give the inner counter its own
   `remember`, and prove the outer function never reruns when it's clicked.
3. **`DashboardWithCounter`** (`T3DashboardWithCounterTest`) — the flip side: reading
   state too high up drags everything below it into the recomposition. Push the read
   down so an unrelated header is left alone.
4. **`ReorderableChecklist`** (`T4ReorderableChecklistTest`) — `key()` preserves
   identity when a list reorders, so per-row state (like a checkbox) follows the item
   instead of sticking to its old position.
5. **`DeferredOffsetBox`** (`T5DeferredOffsetBoxTest`) — composition, layout and draw
   are separate phases. The lambda overload of `Modifier.offset` reads state during
   layout instead of composition, so the composable itself never recomposes.
6. **`IdCardBadge`** (`T6IdCardBadgeTest`) — a composable's body must be idempotent.
   Compose can call it more times than you'd expect, so a side effect like generating
   an ID has to be wrapped in `remember`, not left loose in the body.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
