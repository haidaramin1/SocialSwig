// File: app/src/main/java/com/example/socialswig/MainActivity.kt

package com.example.socialswig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.socialswig.ui.navigation.NavGraph
import com.example.socialswig.viewmodel.GameViewModel
import com.example.socialswig.ui.theme.SocialSwigTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialSwigTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SocialSwigApp()
                }
            }
        }
    }
}

@Composable
fun SocialSwigApp() {
    val navController = rememberNavController()
    val viewModel = GameViewModel()
    NavGraph(navController = navController, viewModel = viewModel)
}