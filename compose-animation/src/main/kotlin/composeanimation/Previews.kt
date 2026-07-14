package composeanimation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 200, heightDp = 160)
@Composable
fun PulseDotPreview() {
    MaterialTheme { Surface { PulseDot(expanded = true) } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 120)
@Composable
fun ToggleBannerPreview() {
    MaterialTheme { Surface { ToggleBanner(visible = true) } }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 160)
@Composable
fun ExpandableCardPreview() {
    MaterialTheme { Surface { ExpandableCard(state = CardState.Expanded) } }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 140)
@Composable
fun SlidingChipPreview() {
    MaterialTheme { Surface { SlidingChip() } }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 200)
@Composable
fun ExpandableListPreview() {
    MaterialTheme { Surface { ExpandableList(expanded = true) } }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 120)
@Composable
fun StatusPanelPreview() {
    MaterialTheme { Surface { StatusPanel(state = LoadState.Loaded) } }
}
