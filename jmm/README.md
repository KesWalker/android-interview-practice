# JVM Memory Model

Practice module for the **JVM Memory Model: atomics, volatile, synchronized** topic on
Android Interview Prep.

You're building a small set of concurrency helpers that stand in for the kind of
shared-state plumbing that shows up in real apps: a counter several threads
update at once, a tracker that must keep the true maximum under a race, a value
that should only ever be computed once, and a way to hand work off to a
background thread and safely read its result back. Each task is a small
type that's currently unwritten, with a matching test that's **red**. Make each
test **green**, one at a time, and you'll have written the memory-model idioms
that come up constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :jmm:test                                # run everything
./gradlew :jmm:test --tests "*T1SafeCounterTest"      # one task
```

## The tasks

All the work is in `src/main/kotlin/jmm/Tasks.kt`. Work out the idiom yourself,
or pair with the tutor and let it nudge you toward it.

1. **`SafeCounter`** (`T1SafeCounterTest`) — a counter many threads can increment at once without losing updates.
2. **`HighWaterMark`** (`T2HighWaterMarkTest`) — remembers the highest value seen, even when many threads race to record.
3. **`LazyOnce`** (`T3LazyOnceTest`) — computes its value once and reuses it, even if several threads ask before it's ready.
4. **`runOnBackgroundThread`** (`T4RunOnBackgroundThreadTest`) — runs work on its own thread and hands the result back safely.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
