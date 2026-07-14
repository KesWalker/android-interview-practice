package composelayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertWidthIsEqualTo
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
class T3WeightedRowTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun `300dp row splits 1 to 2 to 1 across three weighted children`() {
        compose.setContent { MaterialTheme { WeightedRow() } }

        // Total weight is 4 across 300dp: 75dp, 150dp, 75dp.
        compose.onNodeWithTag("w1").assertWidthIsEqualTo(75.dp)
        compose.onNodeWithTag("w2").assertWidthIsEqualTo(150.dp)
        compose.onNodeWithTag("w3").assertWidthIsEqualTo(75.dp)
    }
}
