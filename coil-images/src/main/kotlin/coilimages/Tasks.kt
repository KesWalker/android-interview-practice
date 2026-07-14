package coilimages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.memory.MemoryCache
import coil.request.ImageRequest

/** Turns a raw painter State into a short, testable label. */
private fun AsyncImagePainter.State.toStatusLabel(): String = when (this) {
    is AsyncImagePainter.State.Empty -> "No image"
    is AsyncImagePainter.State.Loading -> "Loading"
    is AsyncImagePainter.State.Success -> "Loaded"
    is AsyncImagePainter.State.Error -> "Couldn't load image"
}

// TODO(t1): T1AsyncImageStatusTest
// Load model with AsyncImage and track its AsyncImagePainter.State through the
// onState callback into a remembered status string, then render both the image
// and a "Status: ..." line so a test can see the request settle without
// inspecting pixels.
@Composable
fun AsyncImageStatus(model: Any?, imageLoader: ImageLoader, contentDescription: String, modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf("Loading") }
    Column(modifier = modifier) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            imageLoader = imageLoader,
            modifier = Modifier.size(64.dp),
            onState = { status = it.toStatusLabel() },
        )
        Text(text = "Status: $status")
    }
}

// TODO(t2): T2PlaceholderOrErrorImageTest
// A real image request has more than one "not loaded yet" outcome. When model is
// null, Coil resolves straight to State.Empty with no network involved at all:
// show a "No image" placeholder. When the request fails, State.Error fires:
// show "Couldn't load image" instead of a blank space or a crash. Otherwise
// render the image normally.
@Composable
fun PlaceholderOrErrorImage(model: Any?, imageLoader: ImageLoader, contentDescription: String, modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf("Loading") }
    Column(modifier = modifier) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            imageLoader = imageLoader,
            modifier = Modifier.size(64.dp),
            onState = { status = it.toStatusLabel() },
        )
        Text(text = status)
    }
}

// TODO(t3): T3CrossfadeRequestForTest
// Build a request that crossfades in when it loads instead of popping in
// abruptly. ImageRequest.Builder has a crossfade(durationMillis) shortcut that
// sets up a CrossfadeTransition.Factory for you.
fun crossfadeRequestFor(context: Context, model: Any?, durationMillis: Int): ImageRequest {
    return ImageRequest.Builder(context)
        .data(model)
        .crossfade(durationMillis)
        .build()
}

@Composable
fun CrossfadeImage(
    model: Any?,
    imageLoader: ImageLoader,
    contentDescription: String,
    durationMillis: Int = 300,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val request = remember(model, durationMillis) { crossfadeRequestFor(context, model, durationMillis) }
    AsyncImage(
        model = request,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        modifier = modifier.size(64.dp),
    )
}

data class ImageSize(val width: Int, val height: Int)

// TODO(t4): T4MemoryCacheKeyForTest
// By default Coil's memory cache key for a URL doesn't account for the size the
// image was decoded at, so a 40dp thumbnail and a 400dp full-size load of the
// SAME url can collide in the cache and one steals the other's bitmap. Build a
// MemoryCache.Key whose extras include the requested width and height, so
// different sizes of the same url get different cache entries.
fun memoryCacheKeyFor(url: String, size: ImageSize): MemoryCache.Key {
    return MemoryCache.Key(key = url, extras = mapOf("w" to size.width.toString(), "h" to size.height.toString()))
}

@Composable
fun SizedImage(url: String, size: ImageSize, imageLoader: ImageLoader, contentDescription: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val request = remember(url, size) {
        ImageRequest.Builder(context)
            .data(url)
            .size(size.width, size.height)
            .memoryCacheKey(memoryCacheKeyFor(url, size))
            .build()
    }
    AsyncImage(
        model = request,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        modifier = modifier.size(64.dp),
    )
}

// TODO(t5): T5SwappableImageTest
// AsyncImage rebuilds its request from `model` on every recomposition and
// automatically cancels an in-flight request when the caller passes a new
// model, so swapping model is enough on its own: no manual cancellation code
// needed. Wire it up with a status label like AsyncImageStatus so a test can
// confirm the UI always reflects the CURRENT model, never a stale one.
@Composable
fun SwappableImage(model: Any?, imageLoader: ImageLoader, modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf("Loading") }
    Column(modifier = modifier) {
        AsyncImage(
            model = model,
            contentDescription = model?.toString(),
            imageLoader = imageLoader,
            modifier = Modifier.size(64.dp),
            onState = { status = it.toStatusLabel() },
        )
        Text(text = "$model: $status")
    }
}
