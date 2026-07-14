package reachability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: retracing, and why a mismatched mapping yields confident nonsense.
class T6RetraceClassNameTest {
    @Test fun `retraces a short name back to its original class`() {
        val mapping = obfuscationMapping(setOf("MainActivity", "Presenter"))
        assertEquals("Presenter", retraceClassName(mapping, "b"))
    }

    @Test fun `unmapped short name is returned unchanged`() {
        val mapping = obfuscationMapping(setOf("MainActivity", "Presenter"))
        assertEquals("z", retraceClassName(mapping, "z"))
    }

    @Test fun `a mapping from a different build silently produces the wrong class`() {
        // The crash actually happened in a build where mapping "b" was NetworkClient.
        val buildTwoMapping = obfuscationMapping(setOf("AuthManager", "NetworkClient"))
        assertEquals("NetworkClient", retraceClassName(buildTwoMapping, "b"))

        // Retracing the same short name with build one's mapping.txt instead
        // doesn't fail, it just confidently returns the wrong class.
        val buildOneMapping = obfuscationMapping(setOf("MainActivity", "Presenter", "Repository"))
        assertEquals("Presenter", retraceClassName(buildOneMapping, "b"))
    }
}
