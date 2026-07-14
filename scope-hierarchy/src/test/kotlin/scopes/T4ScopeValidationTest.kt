package scopes

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 4: a scope annotation must match the component it's installed in.
class T4ScopeValidationTest {
    @Test fun `an unscoped binding is legal in any component`() {
        assertDoesNotThrow {
            validateScope(scope = null, installedIn = ComponentType.ACTIVITY)
        }
        assertDoesNotThrow {
            validateScope(scope = null, installedIn = ComponentType.SINGLETON)
        }
    }

    @Test fun `a scope matching its own component is legal`() {
        assertDoesNotThrow {
            validateScope(scope = ComponentType.SINGLETON, installedIn = ComponentType.SINGLETON)
        }
    }

    @Test fun `a scope installed in the wrong component throws`() {
        assertThrows(IllegalScopeException::class.java) {
            validateScope(scope = ComponentType.SINGLETON, installedIn = ComponentType.ACTIVITY)
        }
    }

    @Test fun `an ActivityScoped binding cannot be installed in FragmentComponent`() {
        assertThrows(IllegalScopeException::class.java) {
            validateScope(scope = ComponentType.ACTIVITY, installedIn = ComponentType.FRAGMENT)
        }
    }
}
