package coilimages

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
    MaterialTheme {
        Surface {
            val imageLoader = ImageLoader.Builder(LocalContext.current).build()
            CrossfadeImage(
                model = "https://example.com/photo.jpg",
                imageLoader = imageLoader,
                contentDescription = "A photo",
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
