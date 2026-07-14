package expectactual

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: shared logic written only against PlatformStore.
class T1BumpLaunchCountTest {
    @Test fun `first bump on an empty store returns 1`() {
        assertEquals(1, bumpLaunchCount(AndroidStore()))
    }

    @Test fun `bumping twice increments to 2`() {
        val store = IosStore()
        bumpLaunchCount(store)
        assertEquals(2, bumpLaunchCount(store))
    }

    @Test fun `behaves identically across both actuals from a seeded count`() {
        val android = AndroidStore(mutableMapOf("launchCount" to "5"))
        val ios = IosStore(mutableMapOf("launchCount" to "5"))

        assertEquals(6, bumpLaunchCount(android))
        assertEquals(6, bumpLaunchCount(ios))
    }

    @Test fun `writes the new count back so it persists`() {
        val store = AndroidStore()
        bumpLaunchCount(store)
        assertEquals("1", store.read("launchCount"))
    }
}
