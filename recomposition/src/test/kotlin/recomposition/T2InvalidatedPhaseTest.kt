package recomposition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: which phase does each kind of state read invalidate.
class T2InvalidatedPhaseTest {
    @Test fun `text content invalidates composition`() {
        assertEquals(Phase.COMPOSITION, invalidatedPhase(StateRead.TEXT_CONTENT))
    }

    @Test fun `padding and measured size invalidate layout`() {
        assertEquals(Phase.LAYOUT, invalidatedPhase(StateRead.PADDING_DP))
        assertEquals(Phase.LAYOUT, invalidatedPhase(StateRead.SIZE_PX))
    }

    @Test fun `color and a graphicsLayer offset invalidate only draw`() {
        assertEquals(Phase.DRAW, invalidatedPhase(StateRead.COLOR))
        assertEquals(Phase.DRAW, invalidatedPhase(StateRead.OFFSET_PX))
    }
}
