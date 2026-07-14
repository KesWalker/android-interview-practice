# VariantMatrix

Practice module for the **Build Variants** topic on Android Interview Prep.

You're building the engine behind the Android Gradle Plugin's variant system:
crossing build types and product flavor dimensions into the full variant
matrix and their camelCase names, resolving a source set's merged files and
manifest/resource values with the right precedence, and picking the signing
config and buildConfigField a given variant actually gets, failure modes
included. Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :variant-matrix:test                              # run everything
./gradlew :variant-matrix:test --tests "*T1VariantNameTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/variants/Tasks.kt`.

1. **`variantName`** (`T1VariantNameTest`) - build the camelCase variant name from an ordered list of flavors and a build type.
2. **`computeVariantMatrix`** (`T2ComputeVariantMatrixTest`) - cross every flavor dimension with every build type into the full variant matrix.
3. **`mergeResourceValues`** (`T3MergeResourceValuesTest`) - fold precedence-ordered layers so a later layer's keys win.
4. **`mergedSourceSetFiles`** (`T4MergedSourceSetFilesTest`) - resolve a variant's merged files using the real precedence: variant > flavor > buildType > main.
5. **`resolveBuildConfigField`** (`T5ResolveBuildConfigFieldTest`) - search that same precedence order for a single buildConfigField key.
6. **`resolveSigningConfig`** (`T6ResolveSigningConfigTest`) - resolve which signing config a variant uses, and fail loudly when a release variant has none.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
