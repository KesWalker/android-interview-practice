# LifecycleCollect

Practice module for the **Lifecycle-Aware Components** topic on Android
Interview Prep.

You're building a stripped-down model of `repeatOnLifecycle`, the mechanism
that keeps a screen's Flow collection in step with its lifecycle. There's a
`FakeLifecycleOwner` you drive by hand, a channel-backed "upstream" whose
production you can count, and two competing strategies to implement: one that
really cancels its collector when the screen drops below STARTED, and an
older one that just stops delivering values while quietly leaving the
collection running. The last two tasks model the flip side of the same
coin: a cold flow that restarts its work on every collection, and a shared,
hot version of it that doesn't. Each task is a small function that's
currently broken or unwritten, with a matching test that's **red**. Make
each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :lifecycle-collect:test                       # run everything
./gradlew :lifecycle-collect:test --tests "*T1AtLeastTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/lifecyclecollect/Tasks.kt`.

1. **`atLeast`** (`T1AtLeastTest`) - compare two lifecycle states by their position in CREATED -> STARTED -> RESUMED.
2. **`repeatOnStarted`** (`T2RepeatOnStartedTest`) - collect only while STARTED or above, cancelling the collector outright the moment it drops below, and starting a fresh one on the next STARTED.
3. **`launchWhenStarted`** (`T3LaunchWhenStartedTest`) - the older strategy: collect forever, gating only whether values get delivered, never cancelling the underlying collection.
4. **`coldTicks`** (`T4ColdTicksTest`) - a cold flow whose startup step re-runs from scratch on every independent collection.
5. **`sharedTicks`** (`T5SharedTicksTest`) - the same producer wrapped in `shareIn`, so every collector shares one hot upstream instead of re-triggering it.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
