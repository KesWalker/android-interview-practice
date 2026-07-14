package composemental

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class T1StatusLabelTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `renders online or offline based on state, and updates when state changes`() {
        var online by mutableStateOf(true)
        compose.setContent { MaterialTheme { StatusLabel(isOnline = online) } }
        compose.onNodeWithText("Online").assertIsDisplayed()

        online = false
        compose.waitForIdle()
        compose.onNodeWithText("Offline").assertIsDisplayed()
    }
}
