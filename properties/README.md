# Properties, lazy & lateinit

Practice module for the **Properties, lazy & lateinit** topic on Android Interview
Prep.

You're building a handful of small classes that lean on Kotlin's property
mechanics: a config value that shouldn't be computed until it's needed, a
session that's assigned after construction, a profile field that needs to
clean up whatever it's given, and a list that should only be mutable from the
inside. Each task is currently **red**. Make each test **green**, one at a
time, and you'll have written the property idioms that come up constantly in
interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :properties:test                          # run everything
./gradlew :properties:test --tests "*LazyConfigTest"    # one task
```

## The tasks

All the work is in `src/main/kotlin/properties/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`LazyConfig.value`** (`LazyConfigTest`) — computed from a loader, but only
   on first read, then reused.
2. **`UserSession.greeting`** (`SessionGreetingTest`) — greet by name once
   `username` has been assigned, otherwise greet a stranger.
3. **`Profile.bio`** (`TrimmedBioTest`) — whatever's assigned should come back
   out with its whitespace trimmed.
4. **`TodoList.items`** (`TodoListItemsTest`) — a read-only view of the list
   this class holds internally.
5. **`configuredAttempts`** (`ConfiguredAttemptsTest`) — read a primitive
   `var` backed by `Delegates.notNull()`, the lateinit alternative for types
   that can't be null-sentinelled.
6. **`Rectangle.area`** (`RectangleAreaTest`) — a computed getter with no
   backing field, always derived fresh from `width`/`height`.
7. **`Account.deposit`/`withdraw`** (`AccountTest`) — mutate a `private set`
   property only from inside its own class.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
