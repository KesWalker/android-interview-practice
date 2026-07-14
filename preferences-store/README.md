# PreferencesStore

Practice module for the **DataStore** topic on Android Interview Prep.

You're building a DataStore-alike from the ground up: a typed key that reads
back either a default or the wrong-type failure it's supposed to catch, a
migration that folds a legacy flat key/value map into the typed store, and
the atomic `edit` that has to survive hundreds of coroutines incrementing the
same counter at once without losing a single update, the way `updateData`
protects you from a plain get-then-set race. The last task wires migration
and `edit` together into the real DataStore contract: legacy data moves in
exactly once, on the first read of an empty store, and never again. Each
task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :preferences-store:test                             # run everything
./gradlew :preferences-store:test --tests "*T1GetOrDefaultTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/prefstore/Tasks.kt`.

1. **`Preferences.getOrDefault`** (`T1GetOrDefaultTest`) - fall back to a default when a key isn't stored.
2. **`Preferences.getTyped`** (`T2GetTypedTest`) - a type-safe key read that throws instead of handing back the wrong type.
3. **`migrateLegacy`** (`T3MigrateLegacyTest`) - fold a legacy flat key/value map into typed Preferences, dropping anything unknown or mistyped.
4. **`PreferencesStore.edit`** (`T4EditTest`) - the atomic read-modify-write at the heart of DataStore: hundreds of concurrent increments must not lose an update.
5. **`PreferencesStore.readWithLegacyMigration`** (`T5ReadWithLegacyMigrationTest`) - the real interview question: migrate legacy data in exactly once, on the first read of an empty store, and never touch it again.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
