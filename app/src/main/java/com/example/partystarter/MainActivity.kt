package com.example.partystarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.partystarter.theme.PartyStarterTheme
import com.example.partystarter.ui.PartyStarterNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gameViewModel = viewModel<com.example.partystarter.viewmodel.GameViewModel>()
            PartyStarterTheme {
                PartyStarterNavHost(
                    gameViewModel = gameViewModel,
                    activity = this
                )
            }
        }
    }
}
