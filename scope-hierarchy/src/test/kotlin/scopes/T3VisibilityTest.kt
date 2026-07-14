package scopes

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: a binding is visible to its own component and every descendant,
// never to a sibling or an ancestor.
class T3VisibilityTest {
    private val singleton = Component(ComponentType.SINGLETON, "app", null)
    private val activityRetained = Component(ComponentType.ACTIVITY_RETAINED, "ar1", singleton)
    private val activity = Component(ComponentType.ACTIVITY, "a1", activityRetained)
    private val viewModel = Component(ComponentType.VIEW_MODEL, "vm1", activityRetained)
    private val fragment = Component(ComponentType.FRAGMENT, "f1", activity)

    @Test fun `a binding is visible to the component it's installed in`() {
        assertTrue(isVisible(installedAt = activity, requester = activity))
    }

    @Test fun `a binding is visible to a direct and an indirect descendant`() {
        assertTrue(isVisible(installedAt = activity, requester = fragment))
        assertTrue(isVisible(installedAt = singleton, requester = fragment))
    }

    @Test fun `a binding is not visible to its own ancestor`() {
        assertFalse(isVisible(installedAt = activity, requester = activityRetained))
        assertFalse(isVisible(installedAt = fragment, requester = activity))
    }

    @Test fun `a binding is not visible to a sibling component`() {
        assertFalse(isVisible(installedAt = activity, requester = viewModel))
    }
}
