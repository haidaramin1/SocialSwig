package com.example.socialswig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.socialswig.ui.navigation.SetupNavGraph
import com.example.socialswig.ui.theme.SocialSwigTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialSwigTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}