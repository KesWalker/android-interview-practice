# DaoMigrations

Practice module for the **Room** topic on Android Interview Prep.

You're building the schema and migration logic Room enforces behind the
scenes: applying a Migration's structural operations to a schema, chaining
migrations into a path when there's no single migration that covers the
version jump, replaying that whole path to bring a database up to date,
validating a `@Query`'s referenced columns against the schema the way Room's
annotation processor does at compile time, and the fallback decision Room
makes when no migration path exists at all. Each task is a small function
that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :dao-migrations:test                                   # run everything
./gradlew :dao-migrations:test --tests "*T1ApplyOperationsTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/migrations/Tasks.kt`.

1. **`applyOperations`** (`T1ApplyOperationsTest`) - apply a Migration's structural operations (add/drop table, add/drop/rename column) to a schema.
2. **`applyMigration`** (`T2ApplyMigrationTest`) - apply one migration, checking it starts from the schema's current version and bumping it to the migration's target.
3. **`findMigrationPath`** (`T3FindMigrationPathTest`) - chain migrations from an installed version to a target version, hopping through intermediates when there's no direct migration, and throw when there's no path.
4. **`applyMigrationPath`** (`T4ApplyMigrationPathTest`) - replay a whole migration path against a schema.
5. **`validateQuery`** (`T5ValidateQueryTest`) - validate a `@Query`'s referenced columns against the schema at "compile time," the way Room's processor catches typo'd column names.
6. **`resolveMigration`** (`T6ResolveMigrationTest`) - the real interview question: find a migration path, or fall back to a destructive rebuild when `fallbackToDestructiveMigration` is set, or throw when it isn't.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
