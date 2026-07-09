# StateFlow & SharedFlow

Practice module for the **StateFlow & SharedFlow** topic on Android Interview Prep.

You're building a small set of hot-flow helpers that stand in for the state and
event plumbing that shows up in real ViewModels: a counter that must stay
correct under concurrent updates, a login flow that reports both ongoing state
and a one-off failure, a notification stream that never blocks its publisher,
and a way to share one upstream flow across many collectors. Each task is
something broken or unwritten, with a matching test that's **red**. Make each
test **green**, one at a time, and you'll have written the StateFlow/SharedFlow
idioms that come up constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :stateflow-sharedflow:test                          # run everything
./gradlew :stateflow-sharedflow:test --tests "*CounterTest"    # one task
```

## The tasks

All the work is in `src/main/kotlin/stateflow/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`Counter.increment`** (`CounterTest`) — bump a `StateFlow`-backed count by
   one, safely even when many callers do it at once.
2. **`LoginController.login`** (`LoginControllerTest`) — report an in-progress
   check as ongoing state, and a blank-password failure as a one-off event.
3. **`NotificationCenter`** (`NotificationCenterTest`) — keep the last two
   messages for late subscribers, and never make a publisher wait.
4. **`toHotStateFlow`** (`ToHotStateFlowTest`) — share one upstream flow across
   every collector, starting only once something is actually collecting.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
