# Resource Qualifier Matching

Practice module for the **Resources & Configuration** topic on Android Interview Prep.

You're rebuilding the core of Android's resource-selection algorithm: given a device's
locale, night mode, and screen density, work out which of several qualified resource
directories actually wins. Each task is a small function that's currently unwritten,
with a matching test that's **red**. Make each test **green**, one at a time, and
you'll have implemented the "best config wins" logic the way it comes up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :resource-qualifiers:test                        # run everything
./gradlew :resource-qualifiers:test --tests "*ParseQualifiersTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/resources/Tasks.kt`.

1. **`parseQualifiers`** (`ParseQualifiersTest`) — parse a resource directory name into
   its typed locale/night/density qualifiers.
2. **`isCompatible`** (`IsCompatibleTest`) — decide whether a directory's qualifiers
   could ever apply to a given device, treating density as never contradicting.
3. **`bestMatch`** (`BestMatchTest`) — eliminate contradicting directories, then narrow
   the survivors by qualifier precedence to find the single winner.
4. **`closestDensity`** (`ClosestDensityTest`) — pick whichever available density
   bucket is numerically nearest the device's dpi.
5. **`isValidQualifierOrder`** (`IsValidQualifierOrderTest`) — check whether a directory
   name's qualifier tokens appear in Android's required precedence order.
6. **`dpToPx` / `spToPx`** (`DensityConversionTest`) — convert a dp or sp value to
   physical pixels, with sp additionally respecting the font-scale setting.
7. **`windowWidthSizeClass` / `windowHeightSizeClass`** (`WindowSizeClassTest`) —
   classify a window's width and height in dp into Compact/Medium/Expanded.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
