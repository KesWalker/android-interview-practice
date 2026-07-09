package mvvm

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * MVVM & Unidirectional Data Flow practice.
 *
 * Each piece below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it so its test
 * goes GREEN. Run a single test class from the gutter in Android Studio, or
 * run them all with:
 *
 *     ./gradlew :mvvm-udf:test
 */

// A screen's raw signals, before they're turned into something the UI can render.
data class FeedUiState(
    val items: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface ScreenStatus {
    data object Loading : ScreenStatus
    data class Error(val message: String) : ScreenStatus
    data object Empty : ScreenStatus
    data class Content(val items: List<String>) : ScreenStatus
}

// TODO(t1): ScreenStatusTest
// Collapse FeedUiState's separate fields into exactly one ScreenStatus: an
// error message wins over everything else, then loading, then an empty list,
// and only then the actual content.
fun screenStatus(state: FeedUiState): ScreenStatus {
    TODO("t1: pick the one ScreenStatus that state currently represents")
}

data class CartItem(val name: String, val priceCents: Int, val quantity: Int)

// Already correct: how much the cart costs, in cents. Not part of this task.
fun cartTotalCents(items: List<CartItem>): Int = items.sumOf { it.priceCents * it.quantity }

data class CartUiState(val items: List<CartItem>, val totalLabel: String)

// TODO(t2): CartUiStateTest
// Build the screen's state from the raw cart items: keep the items as-is, and
// turn the total cost into a display string like "$12.99" (or "$0.00" when
// there's nothing in the cart).
fun cartUiState(items: List<CartItem>): CartUiState {
    TODO("t2: build CartUiState with items and a dollar-formatted totalLabel")
}

data class FavoritesUiState(val favorites: Set<String> = emptySet())

class FavoritesViewModel {
    private val _state = MutableStateFlow(FavoritesUiState())
    val state: StateFlow<FavoritesUiState> = _state.asStateFlow()

    // TODO(t3): FavoritesViewModelTest
    // Flip whether `id` is currently in the favorited set, correctly even when
    // many callers do this at the same time.
    fun toggleFavorite(id: String) {
        TODO("t3: toggle id's membership in state.favorites, safe under concurrent calls")
    }
}

data class SearchUiState(val query: String, val onlyFavorites: Boolean, val results: List<String>)

// TODO(t4): SearchUiStateTest
// Derive one running SearchUiState from the current query and favorites
// toggle: results are the names from allItems whose name contains the query
// (case-insensitively), further restricted to favorites when the toggle is on.
fun searchUiState(
    query: StateFlow<String>,
    onlyFavorites: StateFlow<Boolean>,
    allItems: List<Pair<String, Boolean>>
): Flow<SearchUiState> {
    TODO("t4: recompute SearchUiState whenever query or onlyFavorites changes")
}
