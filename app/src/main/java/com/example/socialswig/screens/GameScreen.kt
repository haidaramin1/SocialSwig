package com.example.socialswig.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socialswig.viewmodel.GameViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = viewModel()) {
    val questions by viewModel.questions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (questions.isNotEmpty()) {
            Text(text = questions[0].text, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.nextQuestion(navController) }) {
                Text("Next Question")
            }
        } else {
            Text("No more questions!")
        }
    }
}