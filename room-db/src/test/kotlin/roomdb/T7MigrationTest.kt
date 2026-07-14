package roomdb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T7MigrationTest {

    @Test
    fun `migration preserves existing rows and defaults the new column`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val name = "migrate-${System.nanoTime()}.db"
        val v1 = Room.databaseBuilder(context, TaskDatabaseV1::class.java, name).build()
        v1.taskDaoV1().insert(TaskEntityV1(title = "Buy milk"))
        v1.close()

        val v2 = openTaskDatabase(context, name, withMigration = true)
        val tasks = v2.taskDao().getAll()
        v2.close()

        assertEquals(1, tasks.size)
        assertEquals("Buy milk", tasks[0].title)
        assertFalse(tasks[0].done)
    }

    @Test
    fun `opening a v1 file as v2 without a migration throws`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val name = "no-migration-${System.nanoTime()}.db"
        val v1 = Room.databaseBuilder(context, TaskDatabaseV1::class.java, name).build()
        v1.taskDaoV1().insert(TaskEntityV1(title = "Buy milk"))
        v1.close()

        val v2 = openTaskDatabase(context, name, withMigration = false)
        assertThrows(IllegalStateException::class.java) {
            runBlocking { v2.taskDao().getAll() }
        }
    }
}
