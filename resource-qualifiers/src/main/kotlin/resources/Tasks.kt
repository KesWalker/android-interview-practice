package resources

/**
 * Resource Qualifier Matching practice.
 *
 * Each function below is unwritten and has a matching test in src/test that
 * is currently RED. Your job, one task at a time, is to implement Android's
 * resource-qualifier matching so its test goes GREEN. Run a single test class
 * from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :resource-qualifiers:test
 */

/** One of Android's screen-density buckets, ordered from lowest to highest dpi. */
enum class Density(val dpi: Int) {
    LDPI(120), MDPI(160), HDPI(240), XHDPI(320), XXHDPI(480), XXXHDPI(640)
}

/** The qualifiers attached to a single resource directory, e.g. "drawable-en-night-hdpi". */
data class Qualifiers(
    val locale: String? = null,
    val night: Boolean? = null,
    val density: Density? = null
)

/** The configuration of the device requesting a resource. */
data class DeviceConfig(
    val locale: String,
    val night: Boolean,
    val densityDpi: Int
)

// TODO(t1): T1ParseQualifiersTest
// Parse a resource directory name into its typed Qualifiers. The name is a
// base segment (e.g. "drawable" or "values") followed by zero or more
// "-token" qualifier segments in any order. A token is "night", the lowercase
// name of a Density constant (e.g. "hdpi"), or otherwise a locale code.
fun parseQualifiers(dirName: String): Qualifiers {
    TODO("t1: split dirName on '-', classify each qualifier token, and build a Qualifiers")
}

// TODO(t2): T2IsCompatibleTest
// Decide whether `qualifiers` could ever apply to `device`: a qualifier that
// is set and differs from the device's value is a contradiction, but a
// density qualifier is never a contradiction.
fun isCompatible(qualifiers: Qualifiers, device: DeviceConfig): Boolean {
    TODO("t2: return false only when a set locale/night qualifier contradicts the device")
}

// TODO(t3): T3BestMatchTest
// Given every candidate resource directory's Qualifiers and the requesting
// device, return the single best match: drop contradictions, then narrow by
// qualifier precedence (locale outranks night mode), the way Android's
// resource system picks a winner. Return null when nothing is compatible.
fun bestMatch(candidates: List<Qualifiers>, device: DeviceConfig): Qualifiers? {
    TODO("t3: eliminate contradictions, then narrow by precedence: locale, then night")
}

// TODO(t4): T4ClosestDensityTest
// Density is never eliminated as a contradiction, so pick whichever available
// bucket's dpi is numerically closest to the device's dpi. When two buckets
// are equally close, prefer the higher-dpi one (matching Android's
// preference to scale a bigger asset down rather than a smaller one up).
fun closestDensity(available: List<Density>, deviceDpi: Int): Density {
    TODO("t4: return the available Density whose dpi is nearest deviceDpi, ties -> higher dpi")
}

// TODO(t5): T5IsValidQualifierOrderTest
// Check whether a directory name's qualifier tokens (locale, night, density)
// appear in Android's required precedence order - getting the order wrong
// doesn't crash the build, the directory just silently fails to match at
// runtime.
fun isValidQualifierOrder(dirName: String): Boolean {
    TODO("t5: classify each qualifier token and check their precedence ranks never go backwards")
}

// TODO(t6): T6DensityConversionTest
// Convert a dp or sp value to physical pixels for a given device density,
// using Android's px = dp * (dpi / 160) formula - and make spToPx additionally
// respect the user's font-scale accessibility setting, which dpToPx ignores.
fun dpToPx(dp: Float, densityDpi: Int): Float {
    TODO("t6: scale dp by densityDpi against the 160dpi baseline")
}

fun spToPx(sp: Float, densityDpi: Int, fontScale: Float): Float {
    TODO("t6: scale sp the same way as dp, then multiply by fontScale")
}

/** Android's window size buckets, from smallest to largest. */
enum class WindowSizeClass {
    COMPACT, MEDIUM, EXPANDED
}

// TODO(t7): T7WindowSizeClassTest
// Classify a window's current width and height in dp into Android's
// Compact/Medium/Expanded window size classes - the modern, window-aware
// replacement for static sw600dp-style layout qualifiers.
fun windowWidthSizeClass(widthDp: Int): WindowSizeClass {
    TODO("t7: bucket widthDp using the width breakpoints")
}

fun windowHeightSizeClass(heightDp: Int): WindowSizeClass {
    TODO("t7: bucket heightDp using the height breakpoints, which differ from the width ones")
}
