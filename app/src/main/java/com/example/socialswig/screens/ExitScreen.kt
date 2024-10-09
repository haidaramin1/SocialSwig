// File: app/src/main/java/com/example/socialswig/screens/ExitScreen.kt

package com.example.socialswig.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exit") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        // Your UI content for the exit screen
        Text(
            text = "Thank you for playing!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(innerPadding)
        )
    }
}