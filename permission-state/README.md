# Runtime Permission State Machine

Practice module for the **Runtime Permissions** topic on Android Interview Prep.

You're modelling the state a runtime permission moves through: from never asked, to
granted, to denied (with a rationale still worth showing), to permanently denied
(where the only sane move is routing to Settings). Each task is a small function
that's currently broken or unwritten, with a matching test that's **red**. Make each
test **green**, one at a time, and you'll have written the decision tree interviewers
expect for `checkSelfPermission` / `registerForActivityResult` flows.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :permission-state:test                          # run everything
./gradlew :permission-state:test --tests "*T1NextStateTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/permissions/Tasks.kt`.

1. **`nextState`** (`T1NextStateTest`) — the state that follows a request result.
2. **`shouldShowRationale`** (`T2ShouldShowRationaleTest`) — whether to explain before re-asking.
3. **`actionFor`** (`T3ActionForTest`) — what the UI should do next for a given state.
4. **`finalState`** (`T4FinalStateTest`) — where a permission ends up after a sequence of results.
5. **`locationAccess`** (`T5LocationAccessTest`) — the precision (fine/coarse/denied) actually granted from a foreground-location request result.
6. **`canRequestBackgroundLocation`** (`T6CanRequestBackgroundLocationTest`) — whether background location can be requested yet, given the foreground state.
7. **`afterBackgrounding`** (`T7AfterBackgroundingTest`) — what a location grant mode becomes after the app spends time in the background.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
