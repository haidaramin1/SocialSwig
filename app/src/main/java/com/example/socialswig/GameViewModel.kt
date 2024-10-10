package com.example.socialswig.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.socialswig.model.GameMode
import androidx.navigation.NavController
import com.example.socialswig.ui.navigation.Screen

class GameViewModel : ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _players = MutableStateFlow<List<String>>(emptyList())
    val players: StateFlow<List<String>> = _players

    private val _gameMode = MutableStateFlow<GameMode?>(null)
    val gameMode: StateFlow<GameMode?> = _gameMode

    private val _shouldNavigateToExit = MutableStateFlow(false)
    val shouldNavigateToExit: StateFlow<Boolean> = _shouldNavigateToExit

    init {
        loadDefaultQuestions()
    }

    private fun loadDefaultQuestions() {
        viewModelScope.launch {
            val sampleQuestions = listOf(
                Question("What is your favorite color?"),
                Question("What is your favorite food?")
            )
            _questions.value = sampleQuestions
        }
    }

    private fun loadNaughtyQuestions() {
        viewModelScope.launch {
            val naughtyQuestions = listOf(
                Question("What is your wildest fantasy?"),
                Question("Have you ever lied to your partner?")
            )
            _questions.value = naughtyQuestions
        }
    }

    fun incrementScore(points: Int) {
        _score.value += points
    }

    fun nextQuestion() {
        val currentQuestions = _questions.value.toMutableList()
        if (currentQuestions.isNotEmpty()) {
            currentQuestions.removeAt(0)
            _questions.value = currentQuestions
        }

        if (currentQuestions.isEmpty()) {
            _shouldNavigateToExit.value = true
        }
    }

    fun addPlayer(playerName: String) {
        _players.value = _players.value + playerName
    }

    fun setMode(mode: GameMode) {
        _gameMode.value = mode
        when (mode) {
            GameMode.NAUGHTY -> loadNaughtyQuestions()
            else -> loadDefaultQuestions()
        }
    }

    fun resetNavigationFlag() {
        _shouldNavigateToExit.value = false
    }
}

data class Question(val text: String)