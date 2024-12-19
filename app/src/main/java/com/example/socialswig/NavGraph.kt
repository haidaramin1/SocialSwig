package com.example.socialswig.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialswig.ui.screens.*

@Composable
fun SetupNavGraph(navController: NavHostController, gameViewModel: GameViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Game.route) {
            GameScreen(navController)
        }
        composable(route = Screen.Exit.route) {
            ExitScreen(navController)
        }
        composable(route = Screen.Result.route) {
            ResultScreen(navController)
        }
        composable(route = Screen.ClassicMode.route) {
            ClassicModeScreen(navController, viewModel = gameViewModel)
        }
    }
}