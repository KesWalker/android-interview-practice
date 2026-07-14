# Room DB

Practice module for the **Room** topic on Android Interview Prep.

This one uses real Room: real `@Entity`, `@Dao` and `@Database` annotations, processed by
the actual Room compiler through KSP. The entities and DAOs in `Tasks.kt` are given to you
complete, because they're declarations the compiler reads at build time, not logic you can
half-finish and still compile. Read them closely, they're the whole point: bound `:params`,
`OnConflictStrategy`, a `Flow`-returning query, a `@TypeConverter`, a `@Relation`, and a real
schema `Migration`. Your job is a handful of functions marked `TODO` that call into that
schema the way an app actually would. Each has a matching test that's **red**. Make each
test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :room-db:test                              # run everything
./gradlew :room-db:test --tests "*T1InMemoryDatabaseTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/roomdb/Tasks.kt`.

1. **`buildDatabase`** (`T1InMemoryDatabaseTest`): `Room.inMemoryDatabaseBuilder` so tests
   run fast and offline. Every call makes a brand new, isolated database, even from the same
   `Context`.
2. **`findByNormalizedTitle`** (`T2NormalizedLookupTest`): using a `@Query` with a bound
   `:title` param correctly: normalize the caller's messy input the same way notes are
   stored before handing it to the exact-match query.
3. **`upsert`** (`T3UpsertConflictStrategyTest`): `OnConflictStrategy.IGNORE` keeps the
   existing row on a primary-key conflict, `REPLACE` overwrites it. Pick the right DAO method.
4. **`renameNote`** (`T4RenameNoteTest`): a plain suspend write, and proof that a
   `Flow`-returning `@Query` re-emits after it with zero extra plumbing, because Room tracks
   which tables a query touches and invalidates matching flows on any write to them.
5. **`recordEvent`** (`T5DateRoundTripTest`): a `java.util.Date` column that only exists
   because a `@TypeConverter` turns it into a `Long` and back. Insert, then read back by the
   generated id to prove the round trip.
6. **`addBookToAuthor`** (`T6AuthorRelationTest`): a one-to-many `@Relation`, loaded with
   `@Transaction` because Room resolves it with a second query behind the scenes.
7. **`openTaskDatabase`** (`T7MigrationTest`): a real `Migration` from a v1 schema to a v2
   schema that adds a column, and what Room does instead of guessing when you don't supply
   one: throws.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
