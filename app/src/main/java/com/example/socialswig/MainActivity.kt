package com.example.socialswig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.socialswig.ui.navigation.NavGraph
import com.example.socialswig.ui.theme.SocialSwigTheme
import com.example.socialswig.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        setContent {
            SocialSwigTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}
