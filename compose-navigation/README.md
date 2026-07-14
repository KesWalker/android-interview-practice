# Compose Navigation

Practice module for the **Navigation Compose** topic on Android Interview Prep.

You're building the routing layer of a small app: a `NavHost` steered by a
`NavController`, a route that carries a real argument, a back stack you
deliberately reshape with `popUpTo`, a screen you protect from duplicate
entries with `launchSingleTop`, a result handed back through a
`SavedStateHandle`, and a nested graph with a `ViewModel` shared by two
screens. Each task is a `NavHost` composable that's currently a `TODO()`
stub, with a matching Robolectric test that's **red**. Make each test
**green**, one at a time, and watch the `@Preview` for that task come alive
in Android Studio as you go.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :compose-navigation:test                       # run everything
./gradlew :compose-navigation:test --tests "*T1HomeDetailsNavGraphTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/composenav/Tasks.kt`.

1. **`HomeDetailsNavGraph`** (`T1HomeDetailsNavGraphTest`): a `NavHost` with two destinations, wired together by a `navController.navigate(...)` call from a button.
2. **`ItemDetailsNavGraph`** (`T2ItemDetailsNavGraphTest`): a route that carries a typed argument through its route pattern, read back with `navArgument`.
3. **`OnboardingNavGraph`** (`T3OnboardingNavGraphTest`): a three-screen flow where `popUpTo(...) { inclusive = true }` permanently clears earlier screens off the back stack.
4. **`SingleTopNavGraph`** (`T4SingleTopNavGraphTest`): `launchSingleTop = true` so re-navigating to the screen you're already on doesn't stack a duplicate.
5. **`ColorPickerNavGraph`** (`T5ColorPickerNavGraphTest`): a screen hands a result back to its caller through `previousBackStackEntry.savedStateHandle`.
6. **`SharedCounterNavGraph`** (`T6SharedCounterNavGraphTest`): a nested graph where two destinations share one `ViewModel` scoped to the graph's own back stack entry, not to either screen.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
