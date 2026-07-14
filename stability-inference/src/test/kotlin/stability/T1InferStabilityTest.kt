package stability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: classify a TypeDescriptor as STABLE or UNSTABLE.
class T1InferStabilityTest {
    @Test fun `primitives are always stable`() {
        assertEquals(Stability.STABLE, inferStability(TypeDescriptor.Primitive("Int")))
        assertEquals(Stability.STABLE, inferStability(TypeDescriptor.Primitive("String")))
    }

    @Test fun `an unannotated interface type is unstable, like List`() {
        val list = TypeDescriptor.InterfaceType("List")
        assertEquals(Stability.UNSTABLE, inferStability(list))
    }

    @Test fun `an annotated interface type is trusted as stable`() {
        val immutableList = TypeDescriptor.InterfaceType("ImmutableList", StabilityAnnotation.IMMUTABLE)
        assertEquals(Stability.STABLE, inferStability(immutableList))
    }

    @Test fun `a class with only val fields of stable types is stable`() {
        val point = TypeDescriptor.ClassType(
            "Point",
            listOf(
                FieldDescriptor("x", TypeDescriptor.Primitive("Int")),
                FieldDescriptor("y", TypeDescriptor.Primitive("Int"))
            )
        )
        assertEquals(Stability.STABLE, inferStability(point))
    }

    @Test fun `a class with a var field is unstable even if the field type is stable`() {
        val counter = TypeDescriptor.ClassType(
            "Counter",
            listOf(FieldDescriptor("count", TypeDescriptor.Primitive("Int"), isVar = true))
        )
        assertEquals(Stability.UNSTABLE, inferStability(counter))
    }

    @Test fun `a class holding an unstable interface-typed field is unstable`() {
        val screenState = TypeDescriptor.ClassType(
            "ScreenState",
            listOf(FieldDescriptor("items", TypeDescriptor.InterfaceType("List")))
        )
        assertEquals(Stability.UNSTABLE, inferStability(screenState))
    }

    @Test fun `an explicit annotation overrides an otherwise-unstable class`() {
        val screenState = TypeDescriptor.ClassType(
            "ScreenState",
            listOf(
                FieldDescriptor("items", TypeDescriptor.InterfaceType("List")),
                FieldDescriptor("selected", TypeDescriptor.Primitive("Int"), isVar = true)
            ),
            explicitAnnotation = StabilityAnnotation.STABLE
        )
        assertEquals(Stability.STABLE, inferStability(screenState))
    }
}
