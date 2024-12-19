package com.example.socialswig.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Game : Screen("game_screen")
    object Exit : Screen("exit_screen")
    object Result : Screen("result")
    object ClassicMode : Screen("classic_mode")
    // Add other screens here
}