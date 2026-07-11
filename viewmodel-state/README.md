# UI State Modelling (Loading / Content / Error)

Practice module for the **ViewModel & UI State** topic on Android Interview Prep.

You're building the plain-Kotlin plumbing behind a typical screen: a closed set of
states a screen can be in, a holder that publishes those states safely, a way to
tear down background work in one shot, and a guard against firing the same fetch
twice. Each task is a small type that's currently unwritten, with a matching test
that's **red**. Make each test **green**, one at a time, and you'll have written the
state-modelling idioms that come up constantly in ViewModel interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :viewmodel-state:test                            # run everything
./gradlew :viewmodel-state:test --tests "*T1ToUiStateTest"    # one task
```

## The tasks

All the work is in `src/main/kotlin/statelogic/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`toUiState`** (`T1ToUiStateTest`) — turn a `Result` into a Loading/Content/Error state.
2. **`UiStateHolder`** (`T2UiStateHolderTest`) — publish state safely as a screen loads.
3. **`ClearableHolder`** (`T3ClearableHolderTest`) — shut down all background work at once.
4. **`SingleFlightLoader`** (`T4SingleFlightLoaderTest`) — share one in-flight fetch instead of duplicating it.
5. **`FakeSavedStateHandle`** (`T5FakeSavedStateHandleTest`) — an observable, saved-state-backed value, the `savedStateHandle.getStateFlow` idiom.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
