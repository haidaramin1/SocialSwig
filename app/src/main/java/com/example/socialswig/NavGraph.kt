package com.example.socialswig.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialswig.ui.screens.*



sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Setup : Screen("setup")
    object TimedMode : Screen("timed_mode")
    object ClassicMode : Screen("classic_mode")
    object NaughtyMode : Screen("naughty_mode")
    object Result : Screen("result")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: com.example.socialswig.viewmodel.GameViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Setup.route) {
            SetupScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.TimedMode.route) {
            TimedModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.ClassicMode.route) {
            ClassicModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.NaughtyMode.route) {
            NaughtyModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Result.route) {
            ResultScreen(navController = navController, viewModel = viewModel)
        }
    }
}

