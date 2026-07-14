package variants

/**
 * Build variants practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :variant-matrix:test
 */

/** Thrown when a release variant resolves to no signing config at all. */
class MissingSigningConfigException(message: String) : RuntimeException(message)

// TODO(t1): T1VariantNameTest
// Build the camelCase variant name AGP would generate: the first flavor
// lowercase as given, every later flavor capitalized, then the build type
// capitalized, all concatenated with no separator. If `flavors` is empty,
// the variant name is just `buildType` unchanged.
fun variantName(flavors: List<String>, buildType: String): String {
    TODO("t1: join flavors (first lowercase, rest capitalized) then capitalized buildType")
}

// TODO(t2): T2ComputeVariantMatrixTest
// Compute every variant AGP would build. `dimensions` is a list of flavor
// dimensions in declaration order, each a list of flavor names in that
// dimension. Cross every dimension (dimension 0 varies slowest, the last
// dimension varies fastest) to get one flavor combo per dimension, then
// cross each combo with every build type, turning each (combo, buildType)
// pair into a name with variantName. If `dimensions` is empty, the result
// is just `buildTypes` unchanged.
fun computeVariantMatrix(dimensions: List<List<String>>, buildTypes: List<String>): List<String> {
    TODO("t2: cross dimensions into flavor combos, then cross combos with buildTypes via variantName")
}

// TODO(t3): T3MergeResourceValuesTest
// AGP resolves a manifest placeholder or resValue the same way for every
// source set layer: `layers` is already ordered from lowest precedence to
// highest. Fold them left to right so a key defined in a later layer
// overwrites the same key from an earlier layer, while a key that only
// appears in one layer survives untouched.
fun mergeResourceValues(layers: List<Map<String, String>>): Map<String, String> {
    TODO("t3: fold layers left to right, later layer's keys win")
}

// TODO(t4): T4MergedSourceSetFilesTest
// Resolve the merged file set for a variant. Build the precedence order
// lowest to highest: "main", then buildType, then each entry of `flavors`
// in the order given (a later flavor in the list is a HIGHER-priority
// dimension and overrides an earlier one on a tie), then finally the full
// variant name from variantName(flavors, buildType). Look up each of those
// layer names in `sourceSets` (skip any that aren't present), and merge
// the layers you find with mergeResourceValues.
fun mergedSourceSetFiles(
    sourceSets: Map<String, Map<String, String>>,
    flavors: List<String>,
    buildType: String
): Map<String, String> {
    TODO("t4: build the layer order, look up present layers, delegate to mergeResourceValues")
}

// TODO(t5): T5ResolveBuildConfigFieldTest
// Resolve a single buildConfigField key for a variant by searching source
// sets from MOST specific to LEAST: the full variant name first, then
// `flavors` reversed (last-declared flavor first), then buildType, then
// "main" last. Return the value from the first layer that defines `key`,
// or null if no layer defines it.
fun resolveBuildConfigField(
    key: String,
    fieldsBySourceSet: Map<String, Map<String, String>>,
    flavors: List<String>,
    buildType: String
): String? {
    TODO("t5: search most-specific to least-specific layer, return first match for key")
}

// TODO(t6): T6ResolveSigningConfigTest
// Resolve which signing config a variant uses. A flavor's signing config
// (searching `flavors` reversed, last-declared first) wins if any flavor
// in the list has one. Otherwise fall back to the build type's signing
// config. If neither source has one: a "release" build type has no
// implicit default, so throw MissingSigningConfigException; any other
// build type implicitly falls back to "debug".
fun resolveSigningConfig(
    buildType: String,
    flavors: List<String>,
    buildTypeSigningConfigs: Map<String, String>,
    flavorSigningConfigs: Map<String, String>
): String {
    TODO("t6: flavor override, else buildType, else debug default or MissingSigningConfigException for release")
}
