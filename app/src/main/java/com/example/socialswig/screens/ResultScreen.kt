// File: app/src/main/java/com/example/socialswig/ui/screens/ResultScreen.kt
package com.example.socialswig.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.socialswig.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController, viewModel: GameViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Results") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        // Your UI content for the result screen
        Text(
            text = "Game Results",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(innerPadding)
        )
    }
}