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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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
class T4MemoryCacheKeyForTest {
    @get:Rule val compose = createComposeRule()

    private val context get() = ApplicationProvider.getApplicationContext<Context>()

    @Test fun `a thumbnail and a full-size load of the same url get different keys`() {
        val url = "https://example.com/photo.jpg"
        val thumbnailKey = memoryCacheKeyFor(url, ImageSize(40, 40))
        val fullKey = memoryCacheKeyFor(url, ImageSize(400, 400))

        assertNotEquals(thumbnailKey, fullKey)
    }

    @Test fun `the same url and size always produce the same key`() {
        val url = "https://example.com/photo.jpg"
        val first = memoryCacheKeyFor(url, ImageSize(40, 40))
        val second = memoryCacheKeyFor(url, ImageSize(40, 40))

        assertEquals(first, second)
    }

    @Test fun `still renders the loaded image`() {
        val url = "https://example.com/photo.jpg"
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(url, ColorDrawable(Color.MAGENTA))
            .build()
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        compose.setContent {
            MaterialTheme {
                SizedImage(url = url, size = ImageSize(64, 64), imageLoader = imageLoader, contentDescription = "A photo")
            }
        }
        compose.waitForIdle()

        compose.onNodeWithContentDescription("A photo").assertIsDisplayed()
    }
}
