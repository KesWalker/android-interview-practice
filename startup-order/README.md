# StartupOrder

Practice module for the **App Startup** topic on Android Interview Prep.

You're building the engine behind Jetpack App Startup: a dependency graph of
initializers that has to run every component after the things it needs,
notice when that's impossible because of a cycle, avoid double-initializing
a component several others share, and decide what's cheap enough to skip at
launch and defer until it's actually demanded. Along the way you'll also
model the two numbers engineers actually argue about: which kind of launch
this is (cold, warm or hot) and how long it took to get something on
screen. Each task is a small function that's currently broken or unwritten,
with a matching test that's **red**. Make each test **green**, one at a
time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :startup-order:test                              # run everything
./gradlew :startup-order:test --tests "*T1ClassifyStartupTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/startup/Tasks.kt`.

1. **`classifyStartup`** (`T1ClassifyStartupTest`) - decide cold, warm or hot from whether the process and the activity already exist.
2. **`timeToInitialDisplay`** (`T2TimeToInitialDisplayTest`) - pull time-to-initial-display out of an unordered startup trace.
3. **`topologicalOrder`** (`T3TopologicalOrderTest`) - order initializers so every dependency runs first, with a shared one appearing only once.
4. **`findCycle`** (`T4FindCycleTest`) - detect a cycle in the dependency graph instead of letting it hang or crash.
5. **`initializeAll`** (`T5InitializeAllTest`) - simulate running the graph and prove a shared dependency only initializes once.
6. **`planStartup`** (`T6PlanStartupTest`) - split initializers into eager and lazy/deferred, and total up the cold-start cost deferring them saves.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
