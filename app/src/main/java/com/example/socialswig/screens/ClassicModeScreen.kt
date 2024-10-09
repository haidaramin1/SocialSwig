package com.example.socialswig.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialswig.viewmodel.GameViewModel
import com.example.socialswig.ui.navigation.Screen
import androidx.compose.material3.AlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassicModeScreen(navController: NavController, viewModel: GameViewModel) {
    val questions by viewModel.questions.collectAsState()
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    if (currentQuestionIndex >= questions.size) {
        // All questions done, navigate to result
        LaunchedEffect(Unit) {
            navController.navigate(Screen.Result.route) {
                popUpTo(Screen.ClassicMode.route) { inclusive = true }
            }
        }
    } else {
        val question = questions[currentQuestionIndex]

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Classic Mode") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Classic Game",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = question.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Perform Action", color = MaterialTheme.colorScheme.onPrimary)
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.incrementScore(0) // For simplicity, increase score for the first player
                            viewModel.nextQuestion() // No need to pass navController here
                            currentQuestionIndex++
                            showDialog = false
                        }) {
                            Text("Done")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Skip")
                        }
                    })
            }
        }
    }
}