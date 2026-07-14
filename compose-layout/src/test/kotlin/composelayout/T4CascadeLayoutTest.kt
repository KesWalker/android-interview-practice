package composelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
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
class T4CascadeLayoutTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun `three tiles cascade diagonally by 20dp per step`() {
        compose.setContent {
            MaterialTheme {
                CascadeLayout {
                    Box(Modifier.testTag("tile0").size(40.dp).background(Color(0xFF6650A4)))
                    Box(Modifier.testTag("tile1").size(40.dp).background(Color(0xFF625B71)))
                    Box(Modifier.testTag("tile2").size(40.dp).background(Color(0xFF7D5260)))
                }
            }
        }

        compose.onNodeWithTag("tile0").assertPositionInRootIsEqualTo(0.dp, 0.dp)
        compose.onNodeWithTag("tile1").assertPositionInRootIsEqualTo(20.dp, 20.dp)
        compose.onNodeWithTag("tile2").assertPositionInRootIsEqualTo(40.dp, 40.dp)
    }
}
