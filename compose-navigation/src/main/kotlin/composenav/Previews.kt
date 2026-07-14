package composenav

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun HomeDetailsNavGraphPreview() {
    MaterialTheme { Surface { HomeDetailsNavGraph() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun ItemDetailsNavGraphPreview() {
    MaterialTheme { Surface { ItemDetailsNavGraph() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun OnboardingNavGraphPreview() {
    MaterialTheme { Surface { OnboardingNavGraph() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun SingleTopNavGraphPreview() {
    MaterialTheme { Surface { SingleTopNavGraph() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun ColorPickerNavGraphPreview() {
    MaterialTheme { Surface { ColorPickerNavGraph() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun SharedCounterNavGraphPreview() {
    MaterialTheme { Surface { SharedCounterNavGraph() } }
}
