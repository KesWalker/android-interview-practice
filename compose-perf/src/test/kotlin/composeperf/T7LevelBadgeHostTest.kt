package composeperf

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
class T7LevelBadgeHostTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `badge only recomposes when points cross into a new level`() {
        val points = mutableStateOf(0)
        var recomposeCount = 0

        compose.setContent {
            MaterialTheme { LevelBadgeHost(points = points, onBadgeRecompose = { recomposeCount++ }) }
        }
        compose.onNodeWithText("Level: 0").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        // Nine point increments, all still level 0 (points / 10 == 0).
        for (p in 1..9) {
            points.value = p
            compose.waitForIdle()
        }
        compose.onNodeWithText("Level: 0").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        // Crossing into level 1 is the first change the badge actually needs.
        points.value = 10
        compose.waitForIdle()
        compose.onNodeWithText("Level: 1").assertIsDisplayed()
        assertEquals(2, recomposeCount)

        // Another run of increments within level 1 changes nothing for the badge.
        for (p in 11..19) {
            points.value = p
            compose.waitForIdle()
        }
        assertEquals(2, recomposeCount)

        points.value = 20
        compose.waitForIdle()
        compose.onNodeWithText("Level: 2").assertIsDisplayed()
        assertEquals(3, recomposeCount)
    }
}
