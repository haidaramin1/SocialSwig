package com.example.socialswig.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialswig.ui.navigation.Screen

@Composable
fun ExitScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Game Over!", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Thanks for playing!", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }) {
            Text("Back to Home")
        }
    }
}