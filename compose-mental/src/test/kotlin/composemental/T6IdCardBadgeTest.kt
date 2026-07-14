package composemental

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T6IdCardBadgeTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `id is generated exactly once and stays stable across recompositions`() {
        var nextId = 100
        val generateId = { nextId++ }
        val refreshTrigger = mutableStateOf(0)

        compose.setContent {
            MaterialTheme { IdCardBadge(generateId = generateId, refreshTrigger = refreshTrigger) }
        }
        compose.onNodeWithText("ID: 100").assertIsDisplayed()

        refreshTrigger.value = 1
        compose.waitForIdle()
        compose.onNodeWithText("ID: 100").assertIsDisplayed()

        refreshTrigger.value = 2
        compose.waitForIdle()
        compose.onNodeWithText("ID: 100").assertIsDisplayed()

        assertEquals(101, nextId)
    }
}
