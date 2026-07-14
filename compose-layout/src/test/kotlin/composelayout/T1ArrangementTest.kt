package composelayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T1ArrangementTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun `three 40dp chips land evenly spaced across a 300dp row`() {
        compose.setContent { MaterialTheme { ArrangedRow() } }

        // Row is 300dp wide, three 40dp chips: SpaceBetween puts a 90dp gap
        // between neighbours, flush against both edges.
        compose.onNodeWithTag("chip1").assertPositionInRootIsEqualTo(0.dp, 0.dp)
        compose.onNodeWithTag("chip2").assertPositionInRootIsEqualTo(130.dp, 0.dp)
        compose.onNodeWithTag("chip3").assertPositionInRootIsEqualTo(260.dp, 0.dp)
    }
}
