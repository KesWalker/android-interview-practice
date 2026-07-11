# SavedState

Practice module for the **Config Changes & Process Death** topic on Android
Interview Prep.

You're building the plumbing a screen needs to survive rotation, dark-mode
toggles and the occasional process kill: a filter that keeps saved state
small and serializable, a restore step that fills gaps with defaults, a size
guard that mirrors Android's `TransactionTooLargeException`, and a model of
exactly which piece of state survives which event. Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :saved-state:test                       # run everything
./gradlew :saved-state:test --tests "*T1SaveStateTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/savedstate/Tasks.kt`.

1. **`saveState`** (`T1SaveStateTest`) — keep only the values that can actually go into saved state, drop the rest.
2. **`restoreState`** (`T2RestoreStateTest`) — rebuild a screen's state from what was saved, falling back to defaults for anything missing.
3. **`ensureWithinSizeLimit`** (`T3SizeLimitTest`) — reject saved state that's grown too large to persist.
4. **`recreate`** (`T4RecreateTest`) — model which piece of state survives a config change versus a process death.
5. **`recreateForCause`** (`T5RecreateForCauseTest`) — extend that model to a third cause, task removal, which loses both the ViewModel field and the saved-state field.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
