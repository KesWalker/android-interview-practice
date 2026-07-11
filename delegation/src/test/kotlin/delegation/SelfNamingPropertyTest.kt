package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: custom provideDelegate binding the property's own declared name at bind time.
class SelfNamingPropertyTest {
    @Test fun `label reads back with its own declared property name`() {
        assertEquals("label=draft", Ledger().label)
    }

    @Test fun `a different property on the same class reads back its own name too`() {
        assertEquals("status=draft", Ledger().status)
    }

    @Test fun `repeated reads keep returning the same captured value`() {
        val ledger = Ledger()
        assertEquals(ledger.label, ledger.label)
        assertEquals("label=draft", ledger.label)
    }
}
