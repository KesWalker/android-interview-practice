# Compose Animation

Practice module for the **Compose Animation** topic on Android Interview Prep.

You're animating a small status UI with the real Compose animation APIs: a value
that eases to a target, content that enters and exits, several properties driven
off one state, and a spring you can interrupt mid-flight. Each task is a
composable that's currently unwritten, with a matching test that's **red**. Make
each test **green**, one at a time.

You can see your work as you go: every task has an `@Preview`, so it renders live
in the Android Studio preview pane.

## How to run it

```bash
./gradlew :compose-animation:testDebugUnitTest                          # all tasks
./gradlew :compose-animation:testDebugUnitTest --tests "*T1PulseDotTest"  # one task
./gradlew :compose-animation:recordRoborazziDebug                       # render every @Preview to build/previews
```

The tests run off-device (Robolectric), so no emulator is needed. Animation tests
drive a virtual clock (`mainClock.advanceTimeBy`), so they're deterministic.

## The tasks

All the work is in `src/main/kotlin/composeanimation/Tasks.kt`.

1. **`PulseDot`** (`T1PulseDotTest`) - animate a value to a target with `animate*AsState`.
2. **`ToggleBanner`** (`T2ToggleBannerTest`) - enter and exit with `AnimatedVisibility`.
3. **`ExpandableCard`** (`T3ExpandableCardTest`) - grow and shrink with `animateContentSize`.
4. **`SlidingChip`** (`T4SlidingChipTest`) - drive an `Animatable` with a custom spec, interruptible mid-flight.
5. **`ExpandableList`** (`T5ExpandableListTest`) - animate several properties together with `updateTransition`.
6. **`StatusPanel`** (`T6StatusPanelTest`) - swap between states with `AnimatedContent`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
