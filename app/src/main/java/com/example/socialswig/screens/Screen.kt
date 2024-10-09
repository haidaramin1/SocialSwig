// File: app/src/main/java/com/example/socialswig/ui/navigation/Screen.kt

package com.example.socialswig.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Setup : Screen("setup")
    object ClassicMode : Screen("classic_mode")
    object Result : Screen("result")
    object GameScreen : Screen("game_screen")
    object NaughtyMode : Screen("naughty_mode")
    object TimedMode : Screen("timed_mode")
    object Game : Screen("game")
    object Exit : Screen("exit")
}