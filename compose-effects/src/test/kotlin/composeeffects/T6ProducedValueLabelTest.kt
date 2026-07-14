package composeeffects

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T6ProducedValueLabelTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `shows Loading immediately then the fetched value once produceState completes`() {
        compose.mainClock.autoAdvance = false
        val source = DataSource(result = "Fetched!", delayMs = 1000L)
        compose.setContent { MaterialTheme { ProducedValueLabel(source = source) } }

        compose.onNodeWithText("Loading").assertIsDisplayed()

        compose.mainClock.advanceTimeBy(500L)
        compose.onNodeWithText("Loading").assertIsDisplayed()

        compose.mainClock.advanceTimeBy(500L)
        compose.onNodeWithText("Fetched!").assertIsDisplayed()
    }
}
