package com.example.socialswig.model

data class GameState(
    val players: List<String> = emptyList(),
    val gameMode: GameMode? = null,
    val questions: List<Question> = emptyList(),
    val currentScore: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentChallenge: String = ""
)