package com.example.partystarter.ui

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.partystarter.viewmodel.GameViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    activity: Activity,
    onEndGame: () -> Unit
) {
    // Force landscape orientation
    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED // Reset orientation
        }
    }

    // Load Lottie animation
    val composition = rememberLottieComposition(LottieCompositionSpec.Asset("party_animation.json"))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { gameViewModel.nextChallenge() }, // Tap anywhere to load the next question
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the question
            Text(
                text = gameViewModel.currentChallenge.value?.question ?: "Loading question...",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the Lottie animation
            if (composition.value != null) {
                LottieAnimation(
                    composition = composition.value,
                    iterations = Int.MAX_VALUE, // Loop indefinitely
                    modifier = Modifier.fillMaxSize(0.4f) // Adjust size of animation
                )
            }
        }
    }
}
