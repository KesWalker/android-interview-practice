# FrameBudget

Practice module for the **Jank & Rendering Performance** topic on Android
Interview Prep.

You're building the pure-math core of a frame-timing dashboard: given a
list of frame durations and a refresh rate, work out how much time a frame
actually has, sort each one into smooth, janky or frozen, roll that up into
the jank rate and the p50/p90/p99 percentiles a real dashboard reports,
find the longest run of consecutive dropped frames (the stutter a user
actually notices, not just the average), and attribute a slow frame to
whichever phase of work blew its budget. Each task is a small function
that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :frame-budget:test                              # run everything
./gradlew :frame-budget:test --tests "*T1FrameBudgetTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/framebudget/Tasks.kt`.

1. **`frameBudgetMs`** (`T1FrameBudgetTest`) - convert a refresh rate into the milliseconds a single frame has to render.
2. **`classifyFrame`** (`T2ClassifyFrameTest`) - sort a frame duration into smooth, janky or frozen.
3. **`analyzeFrames`** (`T3AnalyzeFramesTest`) - compute the jank rate and the p50/p90/p99 percentiles over a run of frames.
4. **`longestJankStreak`** (`T4LongestJankStreakTest`) - find the longest run of consecutive dropped frames, the stutter that's actually visible.
5. **`attributeJank`** (`T5AttributeJankTest`) - blame a janky frame on whichever phase in its breakdown blew the budget.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
