# DataStore Prefs

Practice module for the **DataStore Preferences** topic on Android Interview Prep.

This one uses real Jetpack DataStore: `PreferenceDataStoreFactory`, typed keys, `edit {}`,
the `.data` `Flow`, and the library's own `SharedPreferencesMigration`. Each task is a
function in `Tasks.kt` that's currently `TODO`, with a matching test that's **red**. Make
each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :datastore-prefs:test                              # run everything
./gradlew :datastore-prefs:test --tests "*T1CreateAndWriteTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/datastoreprefs/Tasks.kt`.

1. **`createStoreAndWriteCount`** (`T1CreateAndWriteTest`): the standard boilerplate for
   building a Preferences DataStore, `PreferenceDataStoreFactory.create` pointed at
   `context.preferencesDataStoreFile(name)`, then a typed write through `edit {}`.
2. **`readThemeOrDefault`** (`T2DefaultValueTest`): a key that might not exist yet. Reading
   a missing key returns null, so every read needs a fallback.
3. **`incrementAtomically`** (`T3AtomicIncrementTest`): `edit {}` reads and writes in one
   atomic transaction, so concurrent increments never lose an update the way a naive
   read-then-write would.
4. **`setName`** (`T4ObserveTest`): just a write, the interesting part is that `store.data`
   is already a `Flow` that re-emits after it, for free.
5. **`safeData`** (`T5CatchIOExceptionTest`): `store.data` can throw `IOException` if the
   underlying file can't be read. Catch it and fall back to `emptyPreferences()`; let
   anything else keep propagating.
6. **`createStoreMigratingFrom`** (`T6MigrateFromSharedPreferencesTest`): DataStore's own
   `SharedPreferencesMigration` pulls values out of a legacy `SharedPreferences` file the
   first time the DataStore is opened.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
