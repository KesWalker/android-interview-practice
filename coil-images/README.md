# Coil Images

Practice module for the **Image Loading** topic on Android Interview Prep.

Every image-heavy screen leans on an async loader, and Coil is the one most interviewers
ask about. This module works through the real Coil Compose APIs: `AsyncImage`, its
`AsyncImagePainter.State` sealed class (`Empty` / `Loading` / `Success` / `Error`),
crossfade transitions, `MemoryCache.Key`, and how a composable should react when its
`model` changes. Tests use `coil-test`'s `FakeImageLoaderEngine` so every request is
answered synchronously and offline, no real network involved, no flakiness. Each task is
a small `@Composable` (or, for two of them, a small builder function) that's currently
`TODO()`, with a matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :coil-images:test                              # run everything
./gradlew :coil-images:test --tests "*T1AsyncImageStatusTest" # one task
./gradlew :coil-images:recordRoborazziDebug               # render every @Preview to build/previews/
```

## The tasks

All the work is in `src/main/kotlin/coilimages/Tasks.kt`. Each task has a matching
`@Preview` in `Previews.kt` you can render live in Android Studio.

1. **`AsyncImageStatus`** (`T1AsyncImageStatusTest`) — the basics: load a model with
   `AsyncImage`, and track its `AsyncImagePainter.State` through the `onState` callback
   so you (and the test) can tell when a request has actually finished.
2. **`PlaceholderOrErrorImage`** (`T2PlaceholderOrErrorImageTest`) — a real image slot has
   more than one "not the real picture" outcome: a null model shouldn't fire a request at
   all, and a failed request (`State.Error`) needs its own fallback UI instead of a blank
   space or a crash.
3. **`crossfadeRequestFor`** (`T3CrossfadeRequestForTest`) — build a request that
   crossfades in on load instead of popping in abruptly, using
   `ImageRequest.Builder.crossfade(durationMillis)`.
4. **`memoryCacheKeyFor`** (`T4MemoryCacheKeyForTest`) — Coil's default memory cache key
   doesn't account for the size an image was decoded at, so a thumbnail and a full-size
   load of the same URL can collide and steal each other's bitmap. Build a
   `MemoryCache.Key` whose extras include the requested size to keep them apart.
5. **`SwappableImage`** (`T5SwappableImageTest`) — `AsyncImage` rebuilds its request from
   `model` on every recomposition and cancels an in-flight request automatically when the
   model changes, so the UI should always reflect the CURRENT model, never a stale one
   left over from a previous request.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
