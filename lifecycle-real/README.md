# Lifecycle-Aware Collection (Real Lifecycle)

Practice module for the **Lifecycle-Aware Data Loading** topic on Android Interview Prep,
using the real `androidx.lifecycle` APIs instead of a hand-rolled stand-in, driven by a
`TestLifecycleOwner` you move through states by hand.

You're building the observation plumbing a screen needs to stop wasting work in the
background: a `DefaultLifecycleObserver` that reacts to the right callbacks, a
`repeatOnLifecycle(STARTED)` collector that actually cancels and restarts its upstream (not
just its delivery), a plain `lifecycleScope.launch` collector that shows what happens when you
skip that guard, the `flowWithLifecycle` operator form of the same idea, and a look at how
`LiveData` gets lifecycle-awareness for free. Each task is a small function or class that's
currently broken or unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :lifecycle-real:test                                 # run everything
./gradlew :lifecycle-real:test --tests "*T1RecordingObserverTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/lifecyclereal/Tasks.kt`.

1. **`RecordingObserver`** (`T1RecordingObserverTest`) — a `DefaultLifecycleObserver` that records the right callback as the owner moves through its states.
2. **`collectWhileStarted`** (`T2RepeatOnLifecycleTest`) — `repeatOnLifecycle(STARTED)` cancels collection below STARTED and restarts it on the way back up, proven by counting upstream emissions, not just delivered ones.
3. **`collectAlways`** (`T3PlainLifecycleScopeTest`) — a plain `lifecycleScope.launch` collector, for contrast: it keeps collecting in the background even while stopped.
4. **`collectWithFlowWithLifecycle`** (`T4FlowWithLifecycleTest`) — the same start/stop behaviour as task 2, through the `flowWithLifecycle` operator instead of calling `repeatOnLifecycle` directly.
5. **`observeWhileActive`** (`T5LiveDataObservationTest`) — `LiveData.observe()` is lifecycle-aware out of the box, no `repeatOnLifecycle` wrapping required.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
