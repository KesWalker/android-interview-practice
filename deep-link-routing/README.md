# Deep-Link URI Parsing & Routing

Practice module for the **Intents & Deep Linking** topic on Android Interview Prep.

You're building the pure-Kotlin core of a deep-link router: the bit that takes a raw
URI string and turns it into a typed screen the app can navigate to. Each task is a
small function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time, and you'll have written the kind of
URI parsing and pattern matching that sits underneath Android's intent-filter
resolution and `Uri`/`NavDeepLink` handling.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :deep-link-routing:test                        # run everything
./gradlew :deep-link-routing:test --tests "*ParseUriTest"      # one task
```

## The tasks

All the work is in `src/main/kotlin/deeplink/Tasks.kt`. The supporting types
(`ParsedUri`, `Destination`, `LinkKind`) are already written for you; you're only
filling in the functions. The tasks build on each other, so solving them in order
(1 → 2 → 3 → 4 → 5) is the smoothest path.

1. **`parseUri`** (`ParseUriTest`) — split a raw URI string into scheme, host, path
   segments, and query params.
2. **`matchPath`** (`MatchPathTest`) — match path segments against a template like
   `tour/{id}`, capturing placeholder values.
3. **`route`** (`RouteDestinationTest`) — turn a parsed URI into a typed
   `Destination`, falling back to `Unknown` when nothing matches.
4. **`classifyLink`** (`ClassifyLinkTest`) — tell a verified app link apart from an
   unverified web link and a custom-scheme deep link.
5. **`matchesFilter`** (`MatchesFilterTest`) — decide whether an implicit intent
   matches a declared `<intent-filter>`'s action and category lists, including the
   classic forgot-`CATEGORY_DEFAULT` gotcha.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
