package scopes

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 5: a longer-lived object must never hold a shorter-lived one.
class T5ScopeLeakTest {
    @Test fun `a shorter-lived consumer depending on a longer-lived scope is safe`() {
        assertDoesNotThrow {
            checkForScopeLeak(consumerScope = ComponentType.ACTIVITY, dependencyScope = ComponentType.SINGLETON)
        }
    }

    @Test fun `a scope depending on itself is safe`() {
        assertDoesNotThrow {
            checkForScopeLeak(consumerScope = ComponentType.FRAGMENT, dependencyScope = ComponentType.FRAGMENT)
        }
    }

    @Test fun `a Singleton holding an Activity leaks past the Activity's lifecycle`() {
        assertThrows(ScopeLeakException::class.java) {
            checkForScopeLeak(consumerScope = ComponentType.SINGLETON, dependencyScope = ComponentType.ACTIVITY)
        }
    }

    @Test fun `an ActivityRetained holding a Fragment is also a leak`() {
        assertThrows(ScopeLeakException::class.java) {
            checkForScopeLeak(consumerScope = ComponentType.ACTIVITY_RETAINED, dependencyScope = ComponentType.FRAGMENT)
        }
    }

    @Test fun `siblings depending on each other are equally illegal, not just ancestors`() {
        assertThrows(ScopeLeakException::class.java) {
            checkForScopeLeak(consumerScope = ComponentType.ACTIVITY, dependencyScope = ComponentType.VIEW_MODEL)
        }
    }
}
