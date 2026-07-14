# AnrDetector

Practice module for the **Performance, ANR & Memory** topic on Android
Interview Prep.

You're building the analysis behind an ANR trace and a heap dump: pinning
the single call to blame for a stall, totalling main-thread time by category
so you know what to move off it, walking a main-thread timeline to decide
whether a blocked input event was fine, a close call, or an actual ANR, then
switching to the heap side to compute what a GC root retains and catch the
classic leak, a long-lived object still holding onto a screen that should
have been garbage collected. Each task is a small function that's currently
broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :anr-detector:test                          # run everything
./gradlew :anr-detector:test --tests "*T1WorstBlockingCallTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/anr/Tasks.kt`.

1. **`worstBlockingCall`** (`T1WorstBlockingCallTest`) - find the single longest-running item in a main-thread trace.
2. **`totalTimeByCategory`** (`T2CategoryTotalsTest`) - total main-thread time grouped by what kind of work it was.
3. **`detectAnr`** (`T3DetectAnrTest`) - decide whether an input event was blocked long enough to be fine, a close call, or an actual ANR.
4. **`reachableFrom`** (`T4ReachableFromTest`) - compute everything a GC root retains, transitively, without looping on a cycle.
5. **`findLeakedScreens`** (`T5FindLeakedScreensTest`) - catch a destroyed screen that's still reachable from a long-lived object.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
