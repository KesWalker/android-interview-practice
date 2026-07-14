package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T2SurvivingCounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `count survives simulated activity recreation`() {
        val restorationTester = StateRestorationTester(compose)
        restorationTester.setContent { MaterialTheme { SurvivingCounter() } }

        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Count: 2").assertIsDisplayed()

        restorationTester.emulateSavedInstanceStateRestore()

        compose.onNodeWithText("Count: 2").assertIsDisplayed()
    }
}
