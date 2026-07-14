package roomdb

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * Room practice.
 *
 * The entities, DAOs and databases below are the schema: real Room
 * annotations, given to you complete, because annotations are declarations
 * the Room compiler reads at build time, not logic you can leave half
 * finished and still compile. Read them, they are the actual API surface
 * this module is testing. Your job is the handful of functions marked
 * TODO, each a small piece of real code that calls into this schema the
 * way an app would.
 */

// ---------------------------------------------------------------------
// Notes: entity + dao (schema given; two of its methods are TODO tasks)
// ---------------------------------------------------------------------

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnoring(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplacing(note: NoteEntity): Long

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun findById(id: Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity WHERE title = :title")
    suspend fun findByTitle(title: String): NoteEntity?

    @Query("UPDATE NoteEntity SET title = :title WHERE id = :id")
    suspend fun updateTitle(id: Int, title: String)

    @Query("SELECT * FROM NoteEntity ORDER BY id")
    fun observeAll(): Flow<List<NoteEntity>>

    // TODO(t2): T2NormalizedLookupTest
    // findByTitle above is an exact match on a bound :title param. Users
    // search sloppily: extra spaces, mixed case. Normalize `query` the same
    // way notes are stored (trim, then lowercase) before handing it to
    // findByTitle, so a messy query still finds a note stored as its clean
    // lowercase title.
    suspend fun findByNormalizedTitle(query: String): NoteEntity? {
        TODO("t2: return findByTitle(query.trim().lowercase())")
    }

    // TODO(t3): T3UpsertConflictStrategyTest
    // insertIgnoring and insertReplacing both handle a primary-key conflict,
    // but differently: IGNORE silently drops the new row and keeps the old
    // one in place, REPLACE deletes the old row and inserts the new one.
    // Pick the right one based on keepExisting.
    suspend fun upsert(note: NoteEntity, keepExisting: Boolean): Long {
        TODO("t3: return if (keepExisting) insertIgnoring(note) else insertReplacing(note)")
    }
}

// TODO(t1): T1InMemoryDatabaseTest
// Build an in-memory Room database for AppDatabase. In-memory databases are
// perfect for tests: no file on disk, wiped when the process exits, and
// (the part that surprises people) a brand new isolated database every
// single time you call Room.inMemoryDatabaseBuilder, even from the same
// Context.
fun buildDatabase(context: Context): AppDatabase {
    TODO("t1: return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()")
}

// TODO(t4): T4RenameNoteTest
// Rename a note in place. There's already a DAO method for this
// (updateTitle); the point of this task is calling it and seeing that
// NoteDao.observeAll()'s Flow notices the write and re-emits without you
// doing anything else, because Room tracks which tables a @Query touches
// and invalidates matching Flows whenever any write hits that table.
suspend fun renameNote(dao: NoteDao, id: Int, newTitle: String) {
    TODO("t4: dao.updateTitle(id, newTitle)")
}

// ---------------------------------------------------------------------
// Events: a @TypeConverter for Date <-> Long
// ---------------------------------------------------------------------

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val occurredAt: Date
)

@Dao
interface EventDao {
    @Insert
    suspend fun insert(event: EventEntity): Long

    @Query("SELECT * FROM EventEntity WHERE id = :id")
    suspend fun findById(id: Int): EventEntity?
}

// TODO(t5): T5DateRoundTripTest
// Room can't store a java.util.Date column directly, it stores whatever the
// @TypeConverter above turns it into: a Long. Insert the event, then read
// it back by the generated id, so the test can prove the Date that comes
// back out equals the Date that went in.
suspend fun recordEvent(dao: EventDao, name: String, occurredAt: Date): EventEntity {
    TODO(
        "t5: val id = dao.insert(EventEntity(name = name, occurredAt = occurredAt)); " +
            "return dao.findById(id.toInt())!!"
    )
}

// ---------------------------------------------------------------------
// Authors/books: one-to-many @Relation, loaded with @Transaction
// ---------------------------------------------------------------------

@Entity
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val authorId: Int,
    val title: String
)

data class AuthorWithBooks(
    @Embedded val author: AuthorEntity,
    @Relation(parentColumn = "id", entityColumn = "authorId")
    val books: List<BookEntity>
)

@Dao
interface AuthorBookDao {
    @Insert
    suspend fun insertAuthor(author: AuthorEntity): Long

    @Insert
    suspend fun insertBook(book: BookEntity): Long

    // @Transaction matters here: Room loads a @Relation with a second query
    // behind the scenes, and without @Transaction those two queries aren't
    // guaranteed to see a consistent snapshot of the database.
    @Transaction
    @Query("SELECT * FROM AuthorEntity WHERE id = :authorId")
    suspend fun getAuthorWithBooks(authorId: Int): AuthorWithBooks?
}

// TODO(t6): T6AuthorRelationTest
// Add a book for authorId, then return every one of that author's book
// titles by loading the author back through the @Relation above.
suspend fun addBookToAuthor(dao: AuthorBookDao, authorId: Int, title: String): List<String> {
    TODO(
        "t6: dao.insertBook(BookEntity(authorId = authorId, title = title)); " +
            "return dao.getAuthorWithBooks(authorId)!!.books.map { it.title }"
    )
}

// ---------------------------------------------------------------------
// Tasks: a real Migration, v1 -> v2
// ---------------------------------------------------------------------

// The v1 schema: no `done` column yet. Used only to seed a database file
// that looks like it was written by an older version of this app.
@Entity(tableName = "TaskEntity")
data class TaskEntityV1(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)

@Dao
interface TaskDaoV1 {
    @Insert
    suspend fun insert(task: TaskEntityV1): Long
}

@Database(entities = [TaskEntityV1::class], version = 1, exportSchema = false)
abstract class TaskDatabaseV1 : RoomDatabase() {
    abstract fun taskDaoV1(): TaskDaoV1
}

// The current (v2) schema: same table, plus `done`.
@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val done: Boolean = false
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity ORDER BY id")
    suspend fun getAll(): List<TaskEntity>
}

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE TaskEntity ADD COLUMN done INTEGER NOT NULL DEFAULT 0")
    }
}

// TODO(t7): T7MigrationTest
// Open `name` as a v2 TaskDatabase. When withMigration is true, supply
// MIGRATION_1_2 so Room can carry a v1 file forward; when it's false, don't,
// so opening a v1 file fails the way it should: Room refuses to guess how
// to get your data from an old schema to a new one.
fun openTaskDatabase(context: Context, name: String, withMigration: Boolean): TaskDatabase {
    TODO(
        "t7: val builder = Room.databaseBuilder(context, TaskDatabase::class.java, name); " +
            "if (withMigration) builder.addMigrations(MIGRATION_1_2); return builder.build()"
    )
}

// ---------------------------------------------------------------------
// The app database: every entity above except the migration-test schema.
// ---------------------------------------------------------------------

@Database(
    entities = [NoteEntity::class, EventEntity::class, AuthorEntity::class, BookEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun eventDao(): EventDao
    abstract fun authorBookDao(): AuthorBookDao
}
