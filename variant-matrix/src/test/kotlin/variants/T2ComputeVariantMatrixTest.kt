package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: the full variant matrix from crossing dimensions with build types.
class T2ComputeVariantMatrixTest {
    @Test fun `no dimensions, matrix is just the build types`() {
        assertEquals(
            listOf("debug", "release"),
            computeVariantMatrix(emptyList(), listOf("debug", "release"))
        )
    }

    @Test fun `single dimension crossed with build types`() {
        assertEquals(
            listOf("freeDebug", "freeRelease", "paidDebug", "paidRelease"),
            computeVariantMatrix(listOf(listOf("free", "paid")), listOf("debug", "release"))
        )
    }

    @Test fun `two dimensions, dimension 0 varies slowest`() {
        val dimensions = listOf(listOf("free", "paid"), listOf("arm", "x86"))
        val buildTypes = listOf("debug", "release")
        assertEquals(
            listOf(
                "freeArmDebug", "freeArmRelease",
                "freeX86Debug", "freeX86Release",
                "paidArmDebug", "paidArmRelease",
                "paidX86Debug", "paidX86Release"
            ),
            computeVariantMatrix(dimensions, buildTypes)
        )
    }
}
