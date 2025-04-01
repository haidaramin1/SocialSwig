package com.example.partystarter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partystarter.R
import com.example.partystarter.theme.PartyBackgroundWithFillHeight
import com.example.partystarter.model.Player
import com.example.partystarter.viewmodel.GameViewModel

@Composable
fun PlayerInputScreen(
    gameViewModel: GameViewModel,
    onStartModeSelection: () -> Unit,
    modifier: Modifier = Modifier
) {
    PartyBackgroundWithFillHeight { // Ensure this function exists in your theme package
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            PlayerListCompact(
                players = gameViewModel.players, // Pass the correct player list
                onRemovePlayer = { gameViewModel.removePlayer(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            PlayerInputFieldWithIcons(
                playerName = remember { mutableStateOf("") },
                onAddPlayer = { name ->
                    if (name.isNotBlank()) {
                        gameViewModel.addPlayer(name)
                    }
                },
                onStartModeSelection = onStartModeSelection
            )
        }
    }
}

@Composable
fun PlayerInputFieldWithIcons(
    playerName: MutableState<String>,
    onAddPlayer: (String) -> Unit,
    onStartModeSelection: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Magenta, Color.Cyan)
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            BasicTextField(
                value = playerName.value,
                onValueChange = { playerName.value = it },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    color = Color.Black, // Placeholder and input text color set to black
                    fontSize = 18.sp
                ),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (playerName.value.isEmpty()) {
                        Text(
                            text = "Enter your name here",
                            style = TextStyle.Default.copy(
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Add Player",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (playerName.value.isNotBlank()) {
                            onAddPlayer(playerName.value)
                            playerName.value = ""
                        }
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.beer_glass),
                contentDescription = "Start Game",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onStartModeSelection)
            )
        }
    }
}

@Composable
fun PlayerListCompact(players: List<Player>, onRemovePlayer: (Player) -> Unit) {
    if (players.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            players.forEach { player ->
                Box(
                    modifier = Modifier
                        .background(Color.Black, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = player.name,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "Remove Player",
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { onRemovePlayer(player) }
                        )
                    }
                }
            }
        }
    }
}
