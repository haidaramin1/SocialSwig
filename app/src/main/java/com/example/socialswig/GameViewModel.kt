package com.example.socialswig.viewmodel

import androidx.lifecycle.ViewModel
import com.example.socialswig.model.GameMode
import com.example.socialswig.model.Player
import com.example.socialswig.model.Question
import com.example.socialswig.model.QuestionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _currentMode = MutableStateFlow<GameMode?>(null)
    val currentMode: StateFlow<GameMode?> = _currentMode

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    fun setMode(mode: GameMode) {
        _currentMode.value = mode
        loadQuestions(mode)
    }

    private fun loadQuestions(mode: GameMode) {
        // Här kan du ladda dina frågor baserat på spelläge
        _questions.value = when (mode) {
            GameMode.TIMED -> generateTimedQuestions()
            GameMode.CLASSIC -> generateClassicQuestions()
            GameMode.NAUGHTY -> generateNaughtyQuestions()
        }
    }

    private fun generateTimedQuestions(): List<Question> {
        // Generera Timed Mode-frågor
        return List(50) { index ->
            Question(text = "Timed Question ${index + 1}", type = QuestionType.TIMED)
        }
    }

    private fun generateClassicQuestions(): List<Question> {
        // Generera Classic Mode-frågor
        return List(100) { index ->
            Question(text = "Classic Question ${index + 1}", type = QuestionType.CLASSIC)
        }
    }

    private fun generateNaughtyQuestions(): List<Question> {
        // Generera Naughty Mode-frågor
        return List(50) { index ->
            Question(text = "Naughty Question ${index + 1}", type = QuestionType.NAUGHTY)
        }
    }

    fun addPlayer(name: String) {
        if (name.isNotBlank()) {
            val updatedPlayers = _players.value.toMutableList()
            updatedPlayers.add(Player(name))
            _players.value = updatedPlayers
        }
    }

    fun incrementScore(playerIndex: Int) {
        val updatedPlayers = _players.value.toMutableList()
        if (playerIndex in updatedPlayers.indices) {
            val player = updatedPlayers[playerIndex]
            updatedPlayers[playerIndex] = player.copy(score = player.score + 1)
            _players.value = updatedPlayers
        }
    }

    fun resetGame() {
        _currentMode.value = null
        _questions.value = emptyList()
        _currentQuestionIndex.value = 0
        _players.value = emptyList()
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }
}
