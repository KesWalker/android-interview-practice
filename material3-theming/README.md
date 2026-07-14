# Material 3 Theming

Practice module for the **Material 3 Theming** topic on Android Interview Prep.

This one uses real Jetpack Compose and real Material 3 (Robolectric renders it
off-device, no emulator needed). You're building the plumbing every themed screen
needs: a `ColorScheme` and `Typography` that a composable actually reads instead of
hardcoding, a light/dark switch, a small real screen built from `Scaffold` +
`TopAppBar` + `Card` + `Button`, the classic "you hardcoded a colour" bug you'll be
asked to spot in review, and an adaptive layout that switches between a bottom bar
and a navigation rail as the window gets wider. Each task is a composable that's
currently a `TODO()`, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :material3-theming:test                          # run everything
./gradlew :material3-theming:test --tests "*T1ThemedBannerTest"  # one task

# render every @Preview in this module to build/previews/<name>.png
./gradlew :material3-theming:recordRoborazziDebug
```

## The tasks

All the work is in `src/main/kotlin/m3theming/Tasks.kt`.

1. **`ThemedBanner`** (`T1ThemedBannerTest`) — a Surface that reads `MaterialTheme.colorScheme.primary`/`onPrimary` and `MaterialTheme.typography.titleLarge` instead of declaring its own theme.
2. **`ThemedStatusPill`** (`T2ThemedStatusPillTest`) — pick a light or dark `ColorScheme` and wrap the content in a `MaterialTheme` built from it.
3. **`ProfileScreen`** (`T3ProfileScreenTest`) — a real small screen: `Scaffold` + `TopAppBar` + `Card` + `Button`.
4. **`WarningLabel`** (`T4WarningLabelTest`) — the lesson task: read `MaterialTheme.colorScheme.error`, never hardcode a `Color`.
5. **`AdaptiveHomeScreen`** (`T5AdaptiveHomeScreenTest`) — a bottom `NavigationBar` below the `COMPACT` width breakpoint, a side `NavigationRail` at `MEDIUM`/`EXPANDED` widths.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
