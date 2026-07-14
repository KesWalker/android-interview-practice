# Compose State

Practice module for the **Compose State** topic on Android Interview Prep.

State is the whole reason Compose recomposes at all, and interviewers love to poke at
the edges: what survives recomposition versus what survives process death, who owns a
value versus who just displays it, and which collection types Compose can actually see
changing. This module works through the real state APIs: `remember`, `rememberSaveable`,
hoisting, a state holder class, `derivedStateOf`, and `mutableStateListOf`. Each task is
a small `@Composable` that's currently `TODO()`, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :compose-state:test                       # run everything
./gradlew :compose-state:test --tests "*T1CounterTest" # one task
./gradlew :compose-state:recordRoborazziDebug        # render every @Preview to build/previews/
```

## The tasks

All the work is in `src/main/kotlin/composestate/Tasks.kt`. Each task has a matching
`@Preview` in `Previews.kt` you can render live in Android Studio.

1. **`Counter`** (`T1CounterTest`) — `remember { mutableStateOf(0) }` so a count
   survives recomposition. Skip `remember` and the count resets the moment the click
   that changed it triggers a recomposition.
2. **`SurvivingCounter`** (`T2SurvivingCounterTest`) — `rememberSaveable` so the count
   also survives process recreation (a config change, or the OS killing the activity
   and restoring it), which plain `remember` does not.
3. **`NameField`** (`T3NameFieldTest`) — state hoisting: refactor a text field into a
   stateless `value` + `onValueChange` pair that holds no state of its own, and let
   the caller decide what happens to edits.
4. **`rememberLoginFormState`** (`T4LoginFormStateTest`) — a state holder class groups
   related state and logic behind one object; wrap its construction in `remember` so
   the same instance (and in-progress form data) survives recomposition.
5. **`PasswordStrengthField`** (`T5PasswordStrengthFieldTest`) — `derivedStateOf` so a
   strength badge only recomposes when weak/strong actually flips, not on every
   keystroke.
6. **`TodoListScreen`** (`T6TodoListScreenTest`) — `mutableStateListOf` versus a plain
   `List`: mutating a plain list in place is invisible to Compose's snapshot system, so
   nothing recomposes and the UI goes stale.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
