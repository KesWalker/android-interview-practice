package scopes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: the capstone. Combine visibility with per-instance overrides: the
// nearest ancestor that binds a key wins, like a debug module shadowing a
// Singleton default.
class T6NearestBindingTest {
    private val singleton = Component(ComponentType.SINGLETON, "app", null)
    private val activityRetained = Component(ComponentType.ACTIVITY_RETAINED, "ar1", singleton)
    private val activity = Component(ComponentType.ACTIVITY, "a1", activityRetained)
    private val fragment = Component(ComponentType.FRAGMENT, "f1", activity)

    private val sites = listOf(
        BindingSite(singleton, "Config") { "prod-config" },
        BindingSite(activity, "Config") { "debug-config" }
    )

    @Test fun `the nearest ancestor override wins over a Singleton default`() {
        assertEquals("debug-config", resolveNearest(sites, fragment, "Config"))
    }

    @Test fun `a requester below the override still sees it`() {
        assertEquals("debug-config", resolveNearest(sites, activity, "Config"))
    }

    @Test fun `a requester whose ancestor chain never reaches the override falls back further up`() {
        assertEquals("prod-config", resolveNearest(sites, activityRetained, "Config"))
    }

    @Test fun `no matching site on the walk throws, naming the key`() {
        val ex = assertThrows(MissingComponentBindingException::class.java) {
            resolveNearest(sites, fragment, "Missing")
        }
        assertEquals(true, ex.message!!.contains("Missing"))
    }
}
