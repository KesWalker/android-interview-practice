# Activity Lifecycle

Practice module for the **Activity Lifecycle** topic on Android Interview Prep.

You're modelling the Activity lifecycle as a pure state machine: a single
step from one state to the next, the exact ordered callback sequence for a
multi-step transition, the split between what fires when the user merely
backgrounds the app versus when the Activity is actually finished, the extra
`ON_RESTART` callback that only fires for a surviving instance coming back
from `STOPPED`, and where a given piece of work legally belongs so it's
acquired and released on the right pair of callbacks. Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :activity-lifecycle:test                            # run everything
./gradlew :activity-lifecycle:test --tests "*T1NextStateTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/activitylife/Tasks.kt`.

1. **`nextState`** (`T1NextStateTest`) - apply a single incoming callback to a state and return the state it lands on, rejecting impossible combinations.
2. **`transitionCallbacks`** (`T2TransitionCallbacksTest`) - return the ordered callbacks Android fires walking forward from one state to another.
3. **`backgroundingCallbacks`** (`T3BackgroundingCallbacksTest`) - what fires when the user backgrounds the app, without finishing it.
4. **`finishingCallbacks`** (`T4FinishingCallbacksTest`) - what fires when the Activity is actually finished, all the way to `ON_DESTROY`.
5. **`resumeFromStop`** (`T5ResumeFromStopTest`) - the `ON_RESTART`-led sequence for a surviving instance versus the `ON_CREATE`-led sequence after process death.
6. **`lifecycleBinding`** (`T6ResourceBindingTest`) - which pair of callbacks should acquire and release a given piece of work.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
