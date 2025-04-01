package com.example.partystarter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partystarter.R
import com.example.partystarter.viewmodel.GameMode

@Composable
fun ModeSelectionScreen(
    onModeSelected: (GameMode) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black, Color(0xFF1B1B1B))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Choose Your Mode",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Game Modes
            GameMode.entries.forEach { mode ->
                ModeOption(
                    mode = mode,
                    onClick = { onModeSelected(mode) }
                )
            }
        }
    }
}

@Composable
fun ModeOption(mode: GameMode, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Magenta, Color.Cyan)
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mode Icon
            Image(
                painter = painterResource(
                    id = when (mode) {
                        GameMode.CLASSIC -> R.drawable.beer_can
                        GameMode.TIMED -> R.drawable.timed
                        GameMode.NAUGHTY -> R.drawable.naughty
                        GameMode.DATE -> R.drawable.date
                    }
                ),
                contentDescription = "${mode.name} Icon",
                modifier = Modifier.size(48.dp)
            )

            // Mode Text
            Column {
                Text(
                    text = mode.name,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = getModeDescription(mode),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

fun getModeDescription(mode: GameMode): String {
    return when (mode) {
        GameMode.CLASSIC -> "Traditional party games."
        GameMode.TIMED -> "Race against the clock!"
        GameMode.NAUGHTY -> "Spicy and daring fun."
        GameMode.DATE -> "Romantic and flirty games."
    }
}
