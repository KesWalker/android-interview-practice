# MVVM & Unidirectional Data Flow

Practice module for the **MVVM & Unidirectional Data Flow** topic on Android Interview
Prep.

You're building the pieces of a couple of screens the plain-Kotlin way a `ViewModel`
would: one immutable state object per screen, business logic kept separate from how
it's displayed, and state that only its owner is allowed to change. Each task is a
small function or class that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time, and you'll have written the
idioms that come up whenever an interviewer asks "how would you structure this
screen?"

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :mvvm-udf:test                            # run everything
./gradlew :mvvm-udf:test --tests "*ScreenStatusTest"    # one task
```

## The tasks

All the work is in `src/main/kotlin/mvvm/Tasks.kt`. Work out the idiom yourself, or
pair with the tutor and let it nudge you toward it.

1. **`screenStatus`** (`ScreenStatusTest`) — collapse a screen's loading/error/items
   fields into the one status it actually represents.
2. **`cartUiState`** (`CartUiStateTest`) — turn already-correct business logic into a
   display-ready screen state.
3. **`FavoritesViewModel.toggleFavorite`** (`FavoritesViewModelTest`) — the only way
   the state it exposes is ever allowed to change, safe under concurrent callers.
4. **`searchUiState`** (`SearchUiStateTest`) — one screen state, recomputed whenever
   any of its inputs change.
5. **`newsUiState`** (`NewsUiStateTest`) — turn a cold Flow into a hot StateFlow that
   outlives rotation but not backgrounding.
6. **`ItemListHolder`** (`ItemListHolderTest`) — model a one-off navigation event as
   consume-once state.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
