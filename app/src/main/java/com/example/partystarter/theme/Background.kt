package com.example.partystarter.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.partystarter.R


@Composable
fun PartyBackgroundWithFillHeight(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_party),
            contentDescription = "Party Background - Fill Height",
            contentScale = ContentScale.FillHeight, // Ensures full height coverage
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.8f)
        )
        content()
    }
}
