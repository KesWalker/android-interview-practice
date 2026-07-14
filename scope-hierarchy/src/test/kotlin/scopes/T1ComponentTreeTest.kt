package scopes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 1: only the real Hilt nestings are legal when growing the tree.
class T1ComponentTreeTest {
    private val singleton = Component(ComponentType.SINGLETON, "app", null)

    @Test fun `a legal nesting builds a child pointing back at its parent`() {
        val activityRetained = createChild(singleton, ComponentType.ACTIVITY_RETAINED, "ar1")

        assertEquals(ComponentType.ACTIVITY_RETAINED, activityRetained.type)
        assertEquals(singleton, activityRetained.parent)
    }

    @Test fun `Activity and ViewModel are both legal children of ActivityRetained`() {
        val activityRetained = createChild(singleton, ComponentType.ACTIVITY_RETAINED, "ar1")

        val activity = createChild(activityRetained, ComponentType.ACTIVITY, "a1")
        val viewModel = createChild(activityRetained, ComponentType.VIEW_MODEL, "vm1")

        assertEquals(activityRetained, activity.parent)
        assertEquals(activityRetained, viewModel.parent)
    }

    @Test fun `skipping a level in the hierarchy is illegal`() {
        assertThrows(IllegalComponentNestingException::class.java) {
            createChild(singleton, ComponentType.FRAGMENT, "bad")
        }
    }

    @Test fun `ViewModel can only nest under ActivityRetained, not Activity`() {
        val activityRetained = createChild(singleton, ComponentType.ACTIVITY_RETAINED, "ar1")
        val activity = createChild(activityRetained, ComponentType.ACTIVITY, "a1")

        assertThrows(IllegalComponentNestingException::class.java) {
            createChild(activity, ComponentType.VIEW_MODEL, "bad")
        }
    }
}
