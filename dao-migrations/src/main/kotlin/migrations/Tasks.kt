package migrations

/**
 * Room migrations practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :dao-migrations:test
 */

/** A column in a table: its name, declared type, and whether it allows null. */
data class Column(val name: String, val type: String, val nullable: Boolean = true)

/** A table: its name and the columns it currently has. */
data class Table(val name: String, val columns: List<Column>)

/** A database schema at a particular version. */
data class Schema(val version: Int, val tables: Map<String, Table>)

/** One structural change a Migration can make to a schema. */
sealed interface SchemaOperation
data class AddTable(val table: Table) : SchemaOperation
data class DropTable(val tableName: String) : SchemaOperation
data class AddColumn(val tableName: String, val column: Column) : SchemaOperation
data class DropColumn(val tableName: String, val columnName: String) : SchemaOperation
data class RenameColumn(val tableName: String, val from: String, val to: String) : SchemaOperation

/** Room's Migration: an ordered set of operations from one version to another. */
data class Migration(val from: Int, val to: Int, val operations: List<SchemaOperation>)

/** What Room decides to do to bring the database up to `targetVersion`. */
sealed interface MigrationPlan
data class Migrate(val path: List<Migration>) : MigrationPlan
object Destructive : MigrationPlan

// TODO(t1): T1ApplyOperationsTest
// Apply `operations` to `schema`'s tables in order and return the result:
// AddTable inserts a table, DropTable removes one, AddColumn appends a
// column to a table, DropColumn removes a column by name, and
// RenameColumn changes a column's name. An operation naming a table that
// doesn't exist is an authoring mistake, throw IllegalStateException.
fun applyOperations(schema: Schema, operations: List<SchemaOperation>): Schema {
    TODO("t1: apply each operation to schema's tables, in order")
}

// TODO(t2): T2ApplyMigrationTest
// Apply a single migration's operations (via applyOperations) to bring
// `schema` from migration.from to migration.to. `schema`'s current
// version must equal migration.from, that's Room's contract, throw
// IllegalStateException when it doesn't.
fun applyMigration(schema: Schema, migration: Migration): Schema {
    TODO("t2: apply migration's operations and bump schema to migration.to")
}

// TODO(t3): T3FindMigrationPathTest
// Find an ordered chain of migrations from `migrations` that gets a
// database from `from` to `to`, hopping through intermediate versions
// when there's no direct migration (e.g. 1->2 then 2->4). This is what
// Room does when it builds a MigrationSet; when no such chain exists,
// throw IllegalStateException, the same case Room reports as a missing
// migration.
fun findMigrationPath(migrations: List<Migration>, from: Int, to: Int): List<Migration> {
    TODO("t3: chain migrations from `from` to `to`, or throw when there's no path")
}

// TODO(t4): T4ApplyMigrationPathTest
// Apply every migration in `path`, in order, to `schema` (via
// applyMigration), returning the fully evolved schema.
fun applyMigrationPath(schema: Schema, path: List<Migration>): Schema {
    TODO("t4: fold applyMigration over path")
}

// TODO(t5): T5ValidateQueryTest
// Room validates a @Query's referenced columns against the schema at
// compile time. Check that `tableName` exists in `schema` and that every
// name in `referencedColumns` is one of that table's columns; throw
// IllegalStateException naming the missing table or column. Do nothing
// (return normally) when the query is valid.
fun validateQuery(schema: Schema, tableName: String, referencedColumns: List<String>) {
    TODO("t5: throw when tableName or any referencedColumns entry is unknown")
}

// TODO(t6): T6ResolveMigrationTest
// Decide how to bring `schema` up to `targetVersion`. When a migration
// path exists, return Migrate with that path. When it doesn't and
// `fallbackToDestructiveMigration` is true, return Destructive instead
// of throwing, that's Room's fallbackToDestructiveMigration() escape
// hatch. When it doesn't and the flag is false, let the
// IllegalStateException from findMigrationPath propagate.
fun resolveMigration(
    schema: Schema,
    targetVersion: Int,
    migrations: List<Migration>,
    fallbackToDestructiveMigration: Boolean
): MigrationPlan {
    TODO("t6: Migrate via findMigrationPath, or Destructive on fallback, or rethrow")
}
