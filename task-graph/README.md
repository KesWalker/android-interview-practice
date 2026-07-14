# Task Graph

Practice module for the **Gradle Fundamentals** topic on Android Interview Prep.

You're building the core of Gradle's task-graph engine: ordering tasks so
dependencies run first, catching a dependency cycle with a clear error instead of
stack-overflowing, deciding whether a task can be skipped as UP-TO-DATE, propagating
invalidation to everything downstream of a task that has to rerun, and modelling the
configuration-vs-execution split that makes heavy work in a configuration block the
classic Gradle performance sin. Each task is a small function that's currently broken
or unwritten, with a matching test that's **red**. Make each test **green**, one at a
time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :task-graph:test                                  # run everything
./gradlew :task-graph:test --tests "*T1TopologicalOrderTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/taskgraph/Tasks.kt`.

1. **`topologicalOrder`** (`T1TopologicalOrderTest`) - order tasks so every dependency runs before its dependents.
2. **`requireAcyclic`** (`T2RequireAcyclicTest`) - detect a dependency cycle and throw an error naming the exact path.
3. **`isUpToDate`** (`T3IsUpToDateTest`) - a task is skippable only when every input and output fingerprint is unchanged since the last run.
4. **`tasksToRun`** (`T4TasksToRunTest`) - a dirty task's dependents are dirty too, transitively.
5. **`runBuild`** (`T5RunBuildTest`) - every task's configuration phase runs regardless of what was requested, only execution is selective.
6. **`incrementalBuildPlan`** (`T6IncrementalBuildPlanTest`) - the full plan: which tasks run, in what order, for a real incremental build.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
