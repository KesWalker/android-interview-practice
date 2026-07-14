# FragmentLifecycle

Practice module for the **Fragment Lifecycle** topic on Android Interview Prep.

A Fragment actually runs two overlapping state machines: its own lifecycle
(attach through detach) and its view's lifecycle (onCreateView through
onDestroyView), which can be torn down and rebuilt more than once while the
fragment itself stays alive, for example every time it's pushed onto and
popped off the back stack. You're modelling both machines as plain data,
tracking when the view is actually alive, counting how many times it's been
recreated, and reproducing the classic bug where an observer registered
against the fragment's own lifecycle instead of `viewLifecycleOwner` leaks a
second subscription across a view recreation. The last two tasks build a
validator for which callback can legally fire next. Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :fragment-lifecycle:test                                # run everything
./gradlew :fragment-lifecycle:test --tests "*T1FragmentLifecycleStateTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/fragmentlife/Tasks.kt`.

1. **`fragmentLifecycleState`** (`T1FragmentLifecycleStateTest`) - fold a list of callbacks into the fragment's own ATTACHED/CREATED/DESTROYED/DETACHED state, ignoring the view-level ones.
2. **`isViewAlive`** (`T2IsViewAliveTest`) - track the separate view-lifecycle window between onCreateView and onDestroyView, which can open and close more than once.
3. **`viewRecreationCount`** (`T3ViewRecreationCountTest`) - count how many times the view has been rebuilt.
4. **`activeObserverCount`** (`T4ActiveObserverCountTest`) - simulate the leak: an observer registered on the fragment's lifecycle survives a view recreation and doubles up, one registered on the view lifecycle does not.
5. **`legalNextEvent`** (`T5LegalNextEventTest`) - decide whether a callback can legally fire next given the fragment's state and whether its view is alive.
6. **`validateSequence`** (`T6ValidateSequenceTest`) - run that check across a whole callback sequence and report the first illegal one, if any.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
