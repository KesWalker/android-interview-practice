package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T3ConflictResolutionTest {

    @Test
    fun `local write wins when it is newer than the remote write`() {
        val local = VersionedValue("local edit", timestamp = 200L)
        val remote = VersionedValue("remote edit", timestamp = 100L)

        assertEquals(local, resolveConflict(local, remote))
    }

    @Test
    fun `remote write wins when it is newer than the local write`() {
        val local = VersionedValue("local edit", timestamp = 100L)
        val remote = VersionedValue("remote edit", timestamp = 300L)

        assertEquals(remote, resolveConflict(local, remote))
    }

    @Test
    fun `remote write wins on a timestamp tie, since the server is authoritative`() {
        val local = VersionedValue("local edit", timestamp = 150L)
        val remote = VersionedValue("remote edit", timestamp = 150L)

        assertEquals(remote, resolveConflict(local, remote))
    }
}
