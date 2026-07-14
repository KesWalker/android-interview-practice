# ViewModel & UI State (Real ViewModel)

Practice module for the **ViewModel & UI State** topic on Android Interview Prep,
using the real `androidx.lifecycle.ViewModel` APIs instead of a hand-rolled stand-in.

You're building the plumbing behind a typical screen's ViewModel: state exposed as a
`StateFlow` and mutated through `update {}`, background work that runs in `viewModelScope`
and is torn down automatically when the ViewModel is cleared, a value that survives process
death via `SavedStateHandle`, a `ViewModelProvider.Factory` that injects a constructor
dependency, and a repository `Flow` turned into shared UI state with `stateIn`. Each task is a
small class that's currently broken or unwritten, with a matching test that's **red**. Make
each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :viewmodel-real:test                                # run everything
./gradlew :viewmodel-real:test --tests "*T1CounterViewModelTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/viewmodelreal/Tasks.kt`.

1. **`CounterViewModel`** (`T1CounterViewModelTest`) — expose state as a `StateFlow` and mutate it with `MutableStateFlow.update {}`.
2. **`TickerViewModel`** (`T2TickerViewModelTest`) — launch repeating work in `viewModelScope` and prove it's cancelled the moment the ViewModel is cleared.
3. **`NoteViewModel`** (`T3NoteViewModelTest`) — back a field with `savedStateHandle.getStateFlow` so it survives a simulated process death.
4. **`GreeterViewModelFactory`** (`T4GreeterViewModelFactoryTest`) — a `ViewModelProvider.Factory` that injects a constructor dependency instead of relying on a no-arg constructor.
5. **`ItemsViewModel`** (`T5ItemsViewModelTest`) — turn a repository `Flow` into shared UI state with `stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initial)`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
