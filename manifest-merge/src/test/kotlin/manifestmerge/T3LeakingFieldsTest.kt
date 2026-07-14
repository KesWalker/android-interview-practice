package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: report which named holders leak, in order.
class T3LeakingFieldsTest {
    @Test fun `mixed holders report only the leaking field names, in order`() {
        val holders = listOf(
            NamedContextHolder("okHolder", ContextHolder(HolderLifetime.ACTIVITY_SCOPED, ContextKind.ACTIVITY)),
            NamedContextHolder("leakyHolder", ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.ACTIVITY)),
            NamedContextHolder("appHolder", ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.APPLICATION)),
            NamedContextHolder("anotherLeak", ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.ACTIVITY))
        )
        assertEquals(listOf("leakyHolder", "anotherLeak"), leakingFields(holders))
    }

    @Test fun `no leaking holders returns an empty list`() {
        val holders = listOf(
            NamedContextHolder("a", ContextHolder(HolderLifetime.VIEW_SCOPED, ContextKind.ACTIVITY)),
            NamedContextHolder("b", ContextHolder(HolderLifetime.APPLICATION_SCOPED, ContextKind.APPLICATION))
        )
        assertEquals(emptyList<String>(), leakingFields(holders))
    }

    @Test fun `an empty holder list returns an empty list`() {
        assertEquals(emptyList<String>(), leakingFields(emptyList()))
    }
}
