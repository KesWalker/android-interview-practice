package composelayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T5IntrinsicWidthTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun `divider width matches the text's intrinsic width`() {
        compose.setContent { MaterialTheme { IntrinsicWidthColumn() } }

        val textWidthPx = compose.onNodeWithTag("text").fetchSemanticsNode().size.width
        val dividerWidthPx = compose.onNodeWithTag("divider").fetchSemanticsNode().size.width

        assertTrue("text should have a measurable width", textWidthPx > 0)
        assertEquals(textWidthPx, dividerWidthPx)
    }
}
