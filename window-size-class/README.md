# WindowSizeClass

Practice module for the **Material 3** topic on Android Interview Prep.

You're building the pure math behind Material 3's adaptive layouts: bucket
raw dp measurements into width and height size classes using the real
breakpoints, pick a navigation affordance and a pane strategy from the width
class, and derive a tonal-palette color from a seed by tone level. Each task
is a small function that's currently broken or unwritten, with a matching
test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :window-size-class:test                              # run everything
./gradlew :window-size-class:test --tests "*T1WidthSizeClassTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/windowsize/Tasks.kt`.

1. **`widthSizeClass`** (`T1WidthSizeClassTest`) - bucket a raw width in dp into COMPACT, MEDIUM or EXPANDED at the 600dp and 840dp breakpoints.
2. **`heightSizeClass`** (`T2HeightSizeClassTest`) - the same bucketing for height, at the 480dp and 900dp breakpoints.
3. **`windowSizeClass`** (`T3WindowSizeClassTest`) - combine both axes into one `WindowSizeClass`.
4. **`navigationAffordance`** (`T4NavigationAffordanceTest`) - pick a bottom bar, nav rail or drawer from the width class.
5. **`paneStrategy`** (`T5PaneStrategyTest`) - decide when a screen has room for a list-detail layout instead of a single pane.
6. **`tonalColor`** (`T6TonalColorTest`) - derive a color at a given tone level from a seed color, the way a Material 3 tonal palette does.
7. **`layoutFor`** (`T7LayoutForTest`) - put the size class, navigation and pane decisions together into one `AdaptiveLayout`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
