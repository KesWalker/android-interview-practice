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

// TODO(t1): ParseQualifiersTest
// Parse a resource directory name into its typed Qualifiers. The name is a
// base segment (e.g. "drawable" or "values") followed by zero or more
// "-token" qualifier segments in any order. A token is "night", the lowercase
// name of a Density constant (e.g. "hdpi"), or otherwise a locale code.
fun parseQualifiers(dirName: String): Qualifiers {
    TODO("t1: split dirName on '-', classify each qualifier token, and build a Qualifiers")
}

// TODO(t2): IsCompatibleTest
// Decide whether `qualifiers` could ever apply to `device`: a qualifier that
// is set and differs from the device's value is a contradiction, but a
// density qualifier is never a contradiction.
fun isCompatible(qualifiers: Qualifiers, device: DeviceConfig): Boolean {
    TODO("t2: return false only when a set locale/night qualifier contradicts the device")
}

// TODO(t3): BestMatchTest
// Given every candidate resource directory's Qualifiers and the requesting
// device, return the single best match: drop contradictions, then narrow by
// qualifier precedence (locale outranks night mode), the way Android's
// resource system picks a winner. Return null when nothing is compatible.
fun bestMatch(candidates: List<Qualifiers>, device: DeviceConfig): Qualifiers? {
    TODO("t3: eliminate contradictions, then narrow by precedence: locale, then night")
}

// TODO(t4): ClosestDensityTest
// Density is never eliminated as a contradiction, so pick whichever available
// bucket's dpi is numerically closest to the device's dpi. When two buckets
// are equally close, prefer the higher-dpi one (matching Android's
// preference to scale a bigger asset down rather than a smaller one up).
fun closestDensity(available: List<Density>, deviceDpi: Int): Density {
    TODO("t4: return the available Density whose dpi is nearest deviceDpi, ties -> higher dpi")
}
