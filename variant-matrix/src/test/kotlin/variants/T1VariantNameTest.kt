package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: camelCase variant naming.
class T1VariantNameTest {
    @Test fun `no flavors, name is just the build type`() {
        assertEquals("debug", variantName(emptyList(), "debug"))
    }

    @Test fun `single flavor lowercase then build type capitalized`() {
        assertEquals("freeDebug", variantName(listOf("free"), "debug"))
    }

    @Test fun `multiple flavors, first lowercase rest capitalized`() {
        assertEquals("freeArm64Release", variantName(listOf("free", "arm64"), "release"))
    }
}
