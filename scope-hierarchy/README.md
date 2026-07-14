# ScopeHierarchy

Practice module for the **Hilt Component Scopes** topic on Android
Interview Prep.

You're modelling the part of Hilt that trips people up in interviews: the
component tree (Singleton, ActivityRetained, Activity, ViewModel, Fragment),
which bindings are visible where, what "scoped" actually caches, why a scope
annotation has to match its component, and the classic leak of a
longer-lived object holding onto a shorter-lived one. Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :scope-hierarchy:test                              # run everything
./gradlew :scope-hierarchy:test --tests "*T1ComponentTreeTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/scopes/Tasks.kt`.

1. **`createChild`** (`T1ComponentTreeTest`) - grow the component tree, rejecting any nesting Hilt itself wouldn't allow.
2. **`BindingProvider.get`** (`T2ScopedCachingTest`) - an unscoped binding builds fresh every time, a scoped one caches per component instance.
3. **`isVisible`** (`T3VisibilityTest`) - a binding is visible to its own component and every descendant, never a sibling or an ancestor.
4. **`validateScope`** (`T4ScopeValidationTest`) - a scope annotation must match the component it's installed in, or it's a compile-time error in real Hilt.
5. **`checkForScopeLeak`** (`T5ScopeLeakTest`) - catch the classic bug of a longer-lived object (say, Singleton) holding a reference to a shorter-lived one (say, Activity).
6. **`resolveNearest`** (`T6NearestBindingTest`) - the capstone: combine visibility with per-instance overrides, the nearest ancestor that binds a key wins.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
