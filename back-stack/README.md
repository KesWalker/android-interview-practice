# BackStack

Practice module for the **Navigation** topic on Android Interview Prep.

You're building the list logic behind Jetpack Navigation's back stack: pushing
and popping destinations, popping back to a named route, avoiding a duplicate
top entry with `launchSingleTop`, keeping a nested graph's shared state alive
only while it's actually on the stack, and returning a result to whoever
launched a screen before it pops away. Each task is a small pure function
that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :back-stack:test                          # run everything
./gradlew :back-stack:test --tests "*T1NavigateTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/backstack/Tasks.kt`.

1. **`navigate`** (`T1NavigateTest`) - push a new entry onto the top of the stack.
2. **`popBackStack`** (`T2PopBackStackTest`) - pop the top entry, but never below the start destination.
3. **`popUpTo`** (`T3PopUpToTest`) - pop back to a named route, inclusive or not, using the occurrence nearest the top.
4. **`navigateSingleTop`** (`T4NavigateSingleTopTest`) - skip pushing a duplicate when the target route is already on top.
5. **`popBackStackClearingGraphState`** (`T5GraphScopedStateTest`) - drop a nested graph's shared state once none of its entries are left on the stack.
6. **`popBackStackWithResult`** (`T6PopWithResultTest`) - write a result into the previous entry's saved state before popping back to it.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
