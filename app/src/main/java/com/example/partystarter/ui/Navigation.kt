package com.example.partystarter.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.partystarter.viewmodel.GameMode
import com.example.partystarter.viewmodel.GameViewModel
import com.example.partystarter.utils.lockOrientation
import com.example.partystarter.utils.unlockOrientation

sealed class Screen(val route: String) {
    object PlayerInput : Screen("player_input")
    object ModeSelection : Screen("mode_selection")
    object Game : Screen("game")
}

@Composable
fun PartyStarterNavHost(
    navController: NavHostController = rememberNavController(),
    gameViewModel: GameViewModel,
    activity: Activity
) {
    NavHost(
        navController = navController,
        startDestination = Screen.PlayerInput.route
    ) {
        // Player Input Screen
        composable(Screen.PlayerInput.route) {
            PlayerInputScreen(
                gameViewModel = gameViewModel,
                onStartModeSelection = { navController.navigate(Screen.ModeSelection.route) }
            )
        }

        // Mode Selection Screen
        composable(Screen.ModeSelection.route) {
            ModeSelectionScreen(
                onModeSelected = { mode ->
                    gameViewModel.setMode(mode)  // Set selected mode
                    gameViewModel.startGame()   // Load the first question
                    navController.navigate(Screen.Game.route) // Navigate to GameScreen
                }
            )
        }

        // Game Screen
        composable(Screen.Game.route) {
            GameScreen(
                gameViewModel = gameViewModel,
                activity = activity,
                onEndGame = {
                    unlockOrientation(activity) // Reset orientation
                    navController.popBackStack(Screen.ModeSelection.route, inclusive = false)
                }
            )
        }
    }
}
