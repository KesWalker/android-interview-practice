package imagecache

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: memory, then disk, then network, short-circuiting on the first hit.
class T6LayeredLookupTest {
    @Test fun `memory hit returns immediately without touching disk or network`() = runTest {
        val log = mutableListOf<String>()

        val result = loadImage(
            key = "a",
            memory = { log += "memory"; "mem-bytes".toByteArray() },
            disk = { log += "disk"; null },
            network = { log += "network"; "net-bytes".toByteArray() },
            onLoaded = { _, _ -> log += "onLoaded" }
        )

        assertArrayEquals("mem-bytes".toByteArray(), result)
        assertEquals(listOf("memory"), log)
    }

    @Test fun `disk hit skips network and populates a faster layer`() = runTest {
        val log = mutableListOf<String>()

        val result = loadImage(
            key = "a",
            memory = { log += "memory"; null },
            disk = { log += "disk"; "disk-bytes".toByteArray() },
            network = { log += "network"; "net-bytes".toByteArray() },
            onLoaded = { _, _ -> log += "onLoaded" }
        )

        assertArrayEquals("disk-bytes".toByteArray(), result)
        assertEquals(listOf("memory", "disk", "onLoaded"), log)
    }

    @Test fun `falls through to network when memory and disk both miss`() = runTest {
        val log = mutableListOf<String>()

        val result = loadImage(
            key = "a",
            memory = { log += "memory"; null },
            disk = { log += "disk"; null },
            network = { log += "network"; "net-bytes".toByteArray() },
            onLoaded = { _, _ -> log += "onLoaded" }
        )

        assertArrayEquals("net-bytes".toByteArray(), result)
        assertEquals(listOf("memory", "disk", "network", "onLoaded"), log)
    }
}
