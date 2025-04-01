package com.example.partystarter.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PartyColorScheme = darkColorScheme(
    primary = NeonPink,
    secondary = NeonGreen,
    tertiary = NeonBlue,
    background = DarkBackground
)

@Composable
fun PartyStarterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PartyColorScheme,
        typography = typography,
        content = content
    )
}
