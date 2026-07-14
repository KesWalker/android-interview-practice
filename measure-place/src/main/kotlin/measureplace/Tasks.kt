package measureplace

/**
 * MeasurePlace practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :measure-place:test
 */

/** Bounds a size must resolve into: [minWidth, maxWidth] x [minHeight, maxHeight]. */
data class Constraints(val minWidth: Int, val maxWidth: Int, val minHeight: Int, val maxHeight: Int)

data class Size(val width: Int, val height: Int)

data class Position(val x: Int, val y: Int)

/** Thrown when a layout tries to measure the same child more than once in a pass. */
class MultipleMeasureException(message: String) : RuntimeException(message)

/** A tiny modifier chain: outermost element first, innermost last. */
sealed interface LayoutModifier {
    data class PaddingAll(val amount: Int) : LayoutModifier
    data class ExactSize(val size: Int) : LayoutModifier
}

// TODO(t1): T1CoerceToConstraintsTest
// Clamp `requested` so its width falls within [constraints.minWidth,
// constraints.maxWidth] and its height falls within
// [constraints.minHeight, constraints.maxHeight].
fun coerceToConstraints(constraints: Constraints, requested: Size): Size {
    TODO("t1: clamp requested width/height into the constraints' min/max range")
}

// TODO(t2): T2ChildConstraintsForPaddingTest
// A padding modifier shrinks the constraints it hands to its child: reduce
// both min and max width by `horizontal` (the padding on both sides
// combined) and both min and max height by `vertical`. Never let a bound go
// below 0. An unbounded max (Int.MAX_VALUE) stays Int.MAX_VALUE, it is never
// reduced.
fun childConstraintsForPadding(incoming: Constraints, horizontal: Int, vertical: Int): Constraints {
    TODO("t2: shrink min/max width/height by the padding, floor at 0, leave MAX_VALUE unbounded")
}

// TODO(t3): T3SizeAfterPaddingTest
// A padding modifier's own size is its child's measured size with the
// padding added back on both axes, then coerced into `incoming` (the
// padding box can never end up bigger than what its own parent allows).
fun sizeAfterPadding(childSize: Size, horizontal: Int, vertical: Int, incoming: Constraints): Size {
    TODO("t3: add padding back onto childSize, then coerce the total into incoming")
}

// TODO(t4): T4MeasureRowTest
// A Row-style parent's own size: width is the sum of its children's
// widths, height is the tallest child. Coerce that into `incoming`. Note the
// row's reported size can end up smaller than the sum of its children when
// the incoming constraints are tighter, real Compose overflows in exactly
// this way rather than growing past its own constraints.
fun measureRow(children: List<Size>, incoming: Constraints): Size {
    TODO("t4: sum children widths, take the max height, coerce the pair into incoming")
}

// TODO(t5): T5PlaceRowTest
// Place a Row's children left to right: each child's x is the sum of the
// widths of every child before it, y is always 0.
fun placeRow(children: List<Size>): List<Position> {
    TODO("t5: cumulative x offset per child, y always 0")
}

// TODO(t6): T6MeasureChildrenOnceTest
// `measureCalls` is the sequence of child ids a parent's measure block
// invoked measure() on, in order. Look each id up in `sizes` and return its
// size, in call order. The moment an id shows up in `measureCalls` for a
// second time, throw MultipleMeasureException naming that id, this mirrors
// Compose's runtime crash when a custom layout measures the same child more
// than once in a single measure pass.
fun measureChildrenOnce(measureCalls: List<Int>, sizes: Map<Int, Size>): List<Size> {
    TODO("t6: return sizes in call order, throw MultipleMeasureException the moment an id repeats")
}

// TODO(t7): T7ModifierOrderTest
// Resolve the total size a chain of modifiers produces, outermost first
// (index 0 of `modifiers` is outermost). PaddingAll(amount) wraps its
// content: the content is measured with `amount` shaved off each axis
// (2 * amount total per axis), then the padding is added back around
// whatever comes back. ExactSize(size) fixes its OWN reported size to
// `size` (coerced into whatever constraints it receives at that point in
// the chain), regardless of what's further inside it, that's exactly why
// padding-then-size and size-then-padding produce different totals for the
// same numbers. When the chain runs out, the remaining content fills all
// the space its constraints allow (width = maxWidth, height = maxHeight).
fun sizeAfterModifierChain(modifiers: List<LayoutModifier>, incoming: Constraints): Size {
    TODO("t7: fold the chain outer to inner; PaddingAll wraps its inner result, ExactSize overrides its own size")
}
