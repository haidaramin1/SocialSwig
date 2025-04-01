package com.example.partystarter.ui

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.partystarter.utils.lockOrientation
import com.example.partystarter.utils.unlockOrientation


@Composable
fun TimedModeScreen(
    activity: Activity,
    onEndGame: () -> Unit
) {
    LaunchedEffect(Unit) {
        lockOrientation(activity) // Lock to landscape
    }

    DisposableEffect(Unit) {
        onDispose {
            unlockOrientation(activity) // Unlock on exit
        }
    }

    TintedBackground {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Aligns child items by default
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Classic Mode",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }

            Button(
                onClick = onEndGame,
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Ensure this is within a Box
                    .padding(16.dp)
            ) {
                Text("End Game")
            }
        }
    }
}
