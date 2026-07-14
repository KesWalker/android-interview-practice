# ModuleGraph

Practice module for the **Modularization** topic on Android Interview Prep.

You're building the analysis a build engineer runs over a multi-module
Gradle project: computing what's actually visible on a module's compile
classpath given the `api`/`implementation` split, catching a dependency
cycle before it wrecks the build, ordering modules for a build, working out
the rebuild blast radius when one module changes (and how `implementation`
shrinks it), and catching a layering violation where a foundational module
reaches up into a feature. Each task is a small function that's currently
broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :module-graph:test                          # run everything
./gradlew :module-graph:test --tests "*T1DirectDependenciesTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/modulegraph/Tasks.kt`.

1. **`directDependenciesOfType`** (`T1DirectDependenciesTest`) - filter a module's dependencies down to one declared type.
2. **`compileClasspath`** (`T2CompileClasspathTest`) - compute what's actually visible on a module's compile classpath: direct deps always count, `api` deps of dependencies leak further, `implementation` deps don't.
3. **`findCycle`** (`T3FindCycleTest`) - detect a dependency cycle and report the exact cycle path.
4. **`buildOrder`** (`T4BuildOrderTest`) - topologically order modules so each one builds after everything it depends on.
5. **`modulesToRebuild`** (`T5ModulesToRebuildTest`) - work out which modules must rebuild when one changes, and see how an `implementation` edge shields consumers further up the graph.
6. **`findLayeringViolations`** (`T6LayeringViolationsTest`) - flag a dependency that reaches from a lower layer up into a higher one.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
