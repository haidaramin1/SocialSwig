package com.example.socialswig.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialswig.model.GameMode
import com.example.socialswig.ui.navigation.Screen
import com.example.socialswig.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupScreen(navController: NavController, viewModel: GameViewModel) {
    var playerName by remember { mutableStateOf("") }
    var selectedMode by remember { mutableStateOf<GameMode?>(null) }
    val players by viewModel.players.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Setup Game",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = playerName,
            onValueChange = { playerName = it },
            label = { Text("Player Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Group,
                    contentDescription = "Player Icon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.addPlayer(playerName)
                playerName = ""
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Add Player", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Select Game Mode",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        GameModeSelector(selectedMode = selectedMode, onModeSelected = { selectedMode = it })

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                selectedMode?.let {
                    viewModel.setMode(it)
                    navController.navigate(it.route)
                }
            },
            enabled = selectedMode != null && players.size >= 2,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Start Game", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun GameModeSelector(selectedMode: GameMode?, onModeSelected: (GameMode) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        GameModeCard(
            mode = GameMode.TIMED,
            isSelected = selectedMode == GameMode.TIMED,
            onClick = { onModeSelected(GameMode.TIMED) }
        )
        GameModeCard(
            mode = GameMode.CLASSIC,
            isSelected = selectedMode == GameMode.CLASSIC,
            onClick = { onModeSelected(GameMode.CLASSIC) }
        )
        GameModeCard(
            mode = GameMode.NAUGHTY,
            isSelected = selectedMode == GameMode.NAUGHTY,
            onClick = { onModeSelected(GameMode.NAUGHTY) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameModeCard(mode: GameMode, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = mode.name,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}