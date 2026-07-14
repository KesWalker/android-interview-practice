package coilimages

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader

@Preview(showBackground = true, widthDp = 160, heightDp = 100)
@Composable
fun AsyncImageStatusPreview() {
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            AsyncImageStatus(
                model = "https://example.com/photo.jpg",
                imageLoader = imageLoader,
                contentDescription = "A photo",
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 160, heightDp = 100)
@Composable
fun PlaceholderOrErrorImagePreview() {
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            PlaceholderOrErrorImage(model = null, imageLoader = imageLoader, contentDescription = "A photo")
        }
    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 100)
@Composable
fun CrossfadeImagePreview() {
    // The preview sandbox has no network, so this never gets past Loading, same
    // as any AsyncImage preview of a real URL. The gray fill still shows the
    // request's actual size so you can confirm the layout is right.
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            CrossfadeImage(
                model = "https://example.com/photo.jpg",
                imageLoader = imageLoader,
                contentDescription = "A photo",
                modifier = Modifier.background(Color.LightGray),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 100)
@Composable
fun SizedImagePreview() {
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            SizedImage(
                url = "https://example.com/photo.jpg",
                size = ImageSize(64, 64),
                imageLoader = imageLoader,
                contentDescription = "A photo",
                modifier = Modifier.background(Color.LightGray),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun SwappableImagePreview() {
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            SwappableImage(model = "https://example.com/photo.jpg", imageLoader = imageLoader)
        }
    }
}
