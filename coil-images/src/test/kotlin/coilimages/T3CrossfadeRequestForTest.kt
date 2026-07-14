package coilimages

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import coil.transition.CrossfadeTransition
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@OptIn(ExperimentalCoilApi::class)
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T3CrossfadeRequestForTest {
    @get:Rule val compose = createComposeRule()

    private val context get() = ApplicationProvider.getApplicationContext<Context>()

    @Test fun `builds a request with a crossfade transition of the given duration`() {
        val request = crossfadeRequestFor(context, "https://example.com/photo.jpg", durationMillis = 300)

        val factory = request.transitionFactory
        assertTrue(factory is CrossfadeTransition.Factory)
        assertEquals(300, (factory as CrossfadeTransition.Factory).durationMillis)
    }

    @Test fun `still renders the loaded image`() {
        val url = "https://example.com/photo.jpg"
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(url, ColorDrawable(Color.GREEN))
            .build()
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        compose.setContent {
            MaterialTheme {
                CrossfadeImage(model = url, imageLoader = imageLoader, contentDescription = "A photo")
            }
        }
        compose.waitForIdle()

        compose.onNodeWithContentDescription("A photo").assertIsDisplayed()
    }
}
