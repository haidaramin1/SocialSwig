package com.example.socialswig.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialswig.screens.SetupScreen
import com.example.socialswig.screens.ClassicModeScreen
import com.example.socialswig.screens.ResultScreen
import com.example.socialswig.GameScreen
import com.example.socialswig.ui.screens.HomeScreen
import com.example.socialswig.viewmodel.GameViewModel
import com.example.socialswig.ui.screens.TimedModeScreen
import com.example.socialswig.screens.ExitScreen
import com.example.socialswig.screens.NaughtyModeScreen


@Composable
fun NavGraph(navController: NavHostController, viewModel: GameViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Setup.route) {
            SetupScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.ClassicMode.route) {
            ClassicModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Result.route) {
            ResultScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.GameScreen.route) {
            GameScreen(gameViewModel = viewModel)
        }
        composable(Screen.NaughtyMode.route) {
            NaughtyModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.TimedMode.route) {
            TimedModeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Game.route) {
            GameScreen(gameViewModel = viewModel)
        }
        composable(Screen.Exit.route) {
            ExitScreen(navController = navController)
        }
        }

    }
