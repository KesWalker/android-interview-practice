package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: interface delegation gotcha — a delegate's own methods can't see overrides.
class T5WithDisplayLabelTest {
    @Test fun `label reads back as the new label`() {
        val inner = BasicLabeled("original")
        val wrapped = withDisplayLabel(inner, "override")
        assertEquals("override", wrapped.label)
    }

    @Test fun `describe still reports inner's own label, not the override`() {
        val inner = BasicLabeled("original")
        val wrapped = withDisplayLabel(inner, "override")
        assertEquals("label: original", wrapped.describe())
    }

    @Test fun `different inner and label combinations stay independent`() {
        val a = withDisplayLabel(BasicLabeled("alpha"), "displayA")
        val b = withDisplayLabel(BasicLabeled("beta"), "displayB")
        assertEquals("displayA", a.label)
        assertEquals("label: alpha", a.describe())
        assertEquals("displayB", b.label)
        assertEquals("label: beta", b.describe())
    }
}
