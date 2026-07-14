package windowsize

/**
 * Material 3 window size class and adaptive layout practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :window-size-class:test
 */

/** Material 3's three width buckets, breakpoints at 600dp and 840dp. */
enum class WindowWidthSizeClass { COMPACT, MEDIUM, EXPANDED }

/** Material 3's three height buckets, breakpoints at 480dp and 900dp. */
enum class WindowHeightSizeClass { COMPACT, MEDIUM, EXPANDED }

/** A window's classification on both axes. */
data class WindowSizeClass(
    val width: WindowWidthSizeClass,
    val height: WindowHeightSizeClass
)

/** The primary navigation affordance Material 3 recommends per width class. */
enum class NavigationAffordance { BOTTOM_BAR, RAIL, DRAWER }

/** Whether the screen shows one pane at a time or a list next to a detail. */
enum class PaneStrategy { SINGLE_PANE, LIST_DETAIL }

/** A window size class plus the layout decisions derived from it. */
data class AdaptiveLayout(
    val sizeClass: WindowSizeClass,
    val navigation: NavigationAffordance,
    val panes: PaneStrategy
)

/** A color expressed as hue (0-360), saturation (0.0-1.0) and lightness (0.0-1.0). */
data class HslColor(val hue: Double, val saturation: Double, val lightness: Double)

// TODO(t1): T1WidthSizeClassTest
// Classify `widthDp` using Material 3's real breakpoints: COMPACT below
// 600dp, MEDIUM from 600dp up to (not including) 840dp, EXPANDED from
// 840dp up.
fun widthSizeClass(widthDp: Int): WindowWidthSizeClass {
    TODO("t1: bucket widthDp into COMPACT / MEDIUM / EXPANDED at 600 and 840")
}

// TODO(t2): T2HeightSizeClassTest
// Classify `heightDp` using Material 3's real breakpoints: COMPACT below
// 480dp, MEDIUM from 480dp up to (not including) 900dp, EXPANDED from
// 900dp up.
fun heightSizeClass(heightDp: Int): WindowHeightSizeClass {
    TODO("t2: bucket heightDp into COMPACT / MEDIUM / EXPANDED at 480 and 900")
}

// TODO(t3): T3WindowSizeClassTest
// Combine widthSizeClass and heightSizeClass into one WindowSizeClass.
fun windowSizeClass(widthDp: Int, heightDp: Int): WindowSizeClass {
    TODO("t3: build a WindowSizeClass from widthSizeClass and heightSizeClass")
}

// TODO(t4): T4NavigationAffordanceTest
// Pick the navigation affordance Material 3 recommends for a width class:
// COMPACT gets a bottom bar, MEDIUM gets a nav rail, EXPANDED gets a
// permanent nav drawer.
fun navigationAffordance(width: WindowWidthSizeClass): NavigationAffordance {
    TODO("t4: COMPACT -> BOTTOM_BAR, MEDIUM -> RAIL, EXPANDED -> DRAWER")
}

// TODO(t5): T5PaneStrategyTest
// Pick the pane strategy for a width class: COMPACT and MEDIUM show a
// single pane at a time, only EXPANDED has room for a list-detail layout.
fun paneStrategy(width: WindowWidthSizeClass): PaneStrategy {
    TODO("t5: COMPACT and MEDIUM -> SINGLE_PANE, EXPANDED -> LIST_DETAIL")
}

// TODO(t6): T6TonalColorTest
// Derive the color at a given tone level from a seed color, the way a
// Material 3 tonal palette does: keep the seed's hue, and set lightness to
// `tone` on a 0-100 scale (0 is black, 100 is white). Clamp `tone` to the
// 0..100 range first. At the extremes (tone 0 or tone 100) saturation
// isn't meaningful, so force it to 0.0; otherwise keep the seed's
// saturation.
fun tonalColor(seed: HslColor, tone: Int): HslColor {
    TODO("t6: clamp tone to 0..100, scale to lightness, zero out saturation at the extremes")
}

// TODO(t7): T7LayoutForTest
// Put it all together: given a window's raw dp size, compute its
// WindowSizeClass and the navigation affordance and pane strategy that
// follow from its width class.
fun layoutFor(widthDp: Int, heightDp: Int): AdaptiveLayout {
    TODO("t7: combine windowSizeClass, navigationAffordance and paneStrategy into an AdaptiveLayout")
}
