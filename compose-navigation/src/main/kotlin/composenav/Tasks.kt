package composenav

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// TODO(t1): T1HomeDetailsNavGraphTest
// A NavHost with two destinations, "home" and "details". Home renders the
// text "Home Screen" and a button labelled "Go to Details" that calls
// navController.navigate("details"). Details renders the text "Details
// Screen". This is the whole shape of Navigation Compose: a NavController
// steering a NavHost between composable routes.
@Composable
fun HomeDetailsNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t1: NavHost(navController, startDestination = \"home\", modifier) { " +
            "composable(\"home\") { Column { Text(\"Home Screen\"); " +
            "Button(onClick = { navController.navigate(\"details\") }) " +
            "{ Text(\"Go to Details\") } } }; " +
            "composable(\"details\") { Text(\"Details Screen\") } }"
    )
}

// TODO(t2): T2ItemDetailsNavGraphTest
// A route that carries a typed argument through the route pattern:
// "details/{itemName}" declared with navArgument("itemName") { type =
// NavType.StringType }. The list screen has a button per item ("Apple",
// "Banana") that navigates to "details/<name>". The details screen reads
// backStackEntry.arguments?.getString("itemName") and renders
// "Selected: <name>".
@Composable
fun ItemDetailsNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t2: NavHost(navController, startDestination = \"list\", modifier) { " +
            "composable(\"list\") { Column { " +
            "Button(onClick = { navController.navigate(\"details/Apple\") }) { Text(\"Apple\") }; " +
            "Button(onClick = { navController.navigate(\"details/Banana\") }) { Text(\"Banana\") } } }; " +
            "composable(\"details/{itemName}\", arguments = listOf(navArgument(\"itemName\") " +
            "{ type = NavType.StringType })) { backStackEntry -> " +
            "val itemName = backStackEntry.arguments?.getString(\"itemName\") ?: \"Unknown\"; " +
            "Text(\"Selected: \$itemName\") } }"
    )
}

// TODO(t3): T3OnboardingNavGraphTest
// Three destinations, A -> B -> C. B's button navigates to C with
// popUpTo("A") { inclusive = true }, which clears BOTH A and B out of the
// back stack (inclusive means "A" itself is removed too, not just entries
// above it). After reaching C, navController.currentBackStack.value should
// contain only C among these three routes: A and B are gone for good.
@Composable
fun OnboardingNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t3: NavHost(navController, startDestination = \"A\", modifier) { " +
            "composable(\"A\") { Column { Text(\"Screen A\"); " +
            "Button(onClick = { navController.navigate(\"B\") }) { Text(\"Next\") } } }; " +
            "composable(\"B\") { Column { Text(\"Screen B\"); " +
            "Button(onClick = { navController.navigate(\"C\") { popUpTo(\"A\") { inclusive = true } } }) " +
            "{ Text(\"Finish\") } } }; " +
            "composable(\"C\") { Text(\"Screen C\") } }"
    )
}

// TODO(t4): T4SingleTopNavGraphTest
// Home navigates to "notifications" with launchSingleTop = true. The
// notifications screen has a "Refresh" button that navigates to
// "notifications" AGAIN, also with launchSingleTop = true. Without that
// flag, each Refresh click would push a new duplicate entry onto the back
// stack; with it, the existing top entry is reused. Only one "notifications"
// entry should ever appear in navController.currentBackStack.value, no
// matter how many times Refresh is clicked.
@Composable
fun SingleTopNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t4: NavHost(navController, startDestination = \"home\", modifier) { " +
            "composable(\"home\") { Button(onClick = { navController.navigate(\"notifications\") " +
            "{ launchSingleTop = true } }) { Text(\"Open Notifications\") } }; " +
            "composable(\"notifications\") { Column { Text(\"Notifications\"); " +
            "Button(onClick = { navController.navigate(\"notifications\") { launchSingleTop = true } }) " +
            "{ Text(\"Refresh\") } } } }"
    )
}

// TODO(t5): T5ColorPickerNavGraphTest
// Screen A reads its OWN back stack entry's savedStateHandle as a StateFlow
// (backStackEntry.savedStateHandle.getStateFlow("selectedColor", "None")
// .collectAsState()) and shows "Selected color: <value>". A button navigates
// to screen B. B has "Red" and "Blue" buttons; each one sets a value on
// navController.previousBackStackEntry?.savedStateHandle before calling
// navController.popBackStack(), so when A comes back into view its
// savedStateHandle already has the new value.
@Composable
fun ColorPickerNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t5: NavHost(navController, startDestination = \"A\", modifier) { " +
            "composable(\"A\") { backStackEntry -> " +
            "val selectedColor by backStackEntry.savedStateHandle" +
            ".getStateFlow(\"selectedColor\", \"None\").collectAsState(); " +
            "Column { Text(\"Selected color: \$selectedColor\"); " +
            "Button(onClick = { navController.navigate(\"B\") }) { Text(\"Pick Color\") } } }; " +
            "composable(\"B\") { Column { Text(\"Pick a color\"); " +
            "Button(onClick = { navController.previousBackStackEntry?.savedStateHandle" +
            "?.set(\"selectedColor\", \"Red\"); navController.popBackStack() }) { Text(\"Red\") }; " +
            "Button(onClick = { navController.previousBackStackEntry?.savedStateHandle" +
            "?.set(\"selectedColor\", \"Blue\"); navController.popBackStack() }) { Text(\"Blue\") } } } }"
    )
}

/**
 * Backing ViewModel for [SharedCounterNavGraph] (t6). Shared across the two
 * destinations inside the nested "innerGraph" by scoping it to the graph's
 * own NavBackStackEntry rather than to either individual screen.
 */
class CounterViewModel : ViewModel() {
    var count by mutableStateOf(0)
        private set

    fun increment() {
        count++
    }
}

// TODO(t6): T6SharedCounterNavGraphTest
// A nested graph, built with navigation(startDestination = "counterA", route
// = "innerGraph") { composable("counterA") { ... }; composable("counterB")
// { ... } } inside the outer NavHost. Both destinations obtain the SAME
// CounterViewModel instance by scoping it to the graph's own back stack
// entry: viewModel(viewModelStoreOwner = navController.getBackStackEntry
// ("innerGraph")). counterA shows the count and an "Increment" button;
// counterB just displays the count and a way to get there. Because both
// screens share one ViewModelStoreOwner (the graph entry, not the screen's
// own entry), the count survives navigating from A to B.
@Composable
fun SharedCounterNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    TODO(
        "t6: NavHost(navController, startDestination = \"start\", modifier) { " +
            "composable(\"start\") { Button(onClick = { navController.navigate(\"innerGraph\") }) " +
            "{ Text(\"Enter Counter Flow\") } }; " +
            "navigation(startDestination = \"counterA\", route = \"innerGraph\") { " +
            "composable(\"counterA\") { " +
            "val parentEntry = remember { navController.getBackStackEntry(\"innerGraph\") }; " +
            "val sharedViewModel: CounterViewModel = viewModel(viewModelStoreOwner = parentEntry); " +
            "Column { Text(\"Counter A: \${sharedViewModel.count}\"); " +
            "Button(onClick = { sharedViewModel.increment() }) { Text(\"Increment\") }; " +
            "Button(onClick = { navController.navigate(\"counterB\") }) { Text(\"Go to Counter B\") } } }; " +
            "composable(\"counterB\") { " +
            "val parentEntry = remember { navController.getBackStackEntry(\"innerGraph\") }; " +
            "val sharedViewModel: CounterViewModel = viewModel(viewModelStoreOwner = parentEntry); " +
            "Text(\"Counter B sees: \${sharedViewModel.count}\") } } }"
    )
}
