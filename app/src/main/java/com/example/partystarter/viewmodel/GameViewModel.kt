package com.example.partystarter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partystarter.model.Challenge
import com.example.partystarter.model.Player

enum class GameMode {
    CLASSIC, TIMED, NAUGHTY, DATE
}

class GameViewModel : ViewModel() {
    // Current game mode
    private val _currentMode = mutableStateOf<GameMode?>(null)
    val currentMode: State<GameMode?> get() = _currentMode

    // Current challenge
    private val _currentChallenge = mutableStateOf<Challenge?>(null)
    val currentChallenge: State<Challenge?> get() = _currentChallenge

    // List of players
    val players = mutableStateListOf<Player>()
    private val usedChallenges = mutableSetOf<Challenge>()

    // Set the selected mode
    fun setMode(mode: GameMode) {
        _currentMode.value = mode
        usedChallenges.clear()
    }

    // Add a player to the list
    fun addPlayer(name: String) {
        if (name.isNotBlank()) {
            players.add(Player(name))
        }
    }

    // Remove a player from the list
    fun removePlayer(player: Player) {
        players.remove(player)
    }

    // Start the game and load the first question
    fun startGame() {
        usedChallenges.clear()
        _currentChallenge.value = getRandomChallenge()
    }

    // Move to the next question
    fun nextChallenge() {
        _currentChallenge.value = getRandomChallenge()
    }

    // Get a random challenge for the selected mode
    private fun getRandomChallenge(): Challenge {
        val challenges = _currentMode.value?.let { getChallengesForMode(it) }?.filterNot { it in usedChallenges } ?: emptyList()
        if (challenges.isEmpty()) {
            usedChallenges.clear()
            return getRandomChallenge()
        }
        return challenges.random().also { usedChallenges.add(it) }
    }

    // Define challenges for each mode
    private fun getChallengesForMode(mode: GameMode): List<Challenge> {
        return when (mode) {
            GameMode.CLASSIC -> listOf(
                Challenge("Describe your happiest childhood memory."),
                Challenge("If you could live anywhere, where would it be?")
            )
            GameMode.TIMED -> listOf(
                Challenge("Name as many animals as you can in 10 seconds."),
                Challenge("List five things that are blue.")
            )
            GameMode.NAUGHTY -> listOf(
                Challenge("Ge ${getDifferentPlayer(null)?.name} en kyss på valfri kroppsdel. Vägrar du? Drick 3 klunkar."),
                Challenge("${getDifferentPlayer(null)?.name} väljer en person som måste viska något snuskigt i hens öra. Vägrar du? Drick 2 klunkar."),
                Challenge("Laget runt! Säg namnet på en person i rummet du hade kunnat tänka dig ligga med. Den som inte nämner någon dricker 1 klunk."),
                Challenge("${getDifferentPlayer(null)?.name} måste massera ${getDifferentPlayer(getDifferentPlayer(null))?.name} tills hen är nöjd."),
                Challenge("Tjejer! Om du någonsin har haft en sprutorgasm, då får du ge en klunk till en valfri kille"),
                Challenge("Alla måste berätta sin största turn-on. Den som vägrar dricker 3 klunkar."),
                Challenge("${getDifferentPlayer(null)?.name} måste göra bodyshot på en deltagare av motsatt kön. All form av vägran straffas med hela 5 klunkar"),
                Challenge("Den senaste personen du skrev med på sociala medier – läs upp senaste meddelandet. Vägrar du? Drick 3 klunkar."),
                Challenge("${getDifferentPlayer(null)?.name} får känna på din mage i 5 sekunder. Vägrar du? Drick 3 klunkar."),
                Challenge("Beskriv din bästa kyssupplevelse. Vägrar du? Drick 3 klunkar."),
                Challenge("${getDifferentPlayer(null)?.name} får välja en person du måste sitta i knät på i en minut. Vägrar du? Drick 4 klunkar."),
                Challenge("Alla måste gissa om du föredrar att vara dominant eller undergiven. Rätta dem! Vägrar du? Drick 3 klunkar."),
                Challenge("Låt ${getDifferentPlayer(null)?.name} kolla din senaste Tinder-match eller DM. Vägrar du? Drick 5 klunkar."),
                Challenge("Gör en sexig dans för någon i rummet i 10 sekunder. Vägrar du? Drick 4 klunkar."),
                Challenge("Berätta om ditt mest pinsamma sex-relaterade ögonblick. Vägrar du? Drick 4 klunkar.")
            )
            GameMode.DATE -> listOf(
                Challenge("Describe your perfect date."),
                Challenge("What’s the most romantic thing you’ve done?")
            )
        }
    }

    // Get a different player than the one provided
    private fun getDifferentPlayer(excludedPlayer: Player?): Player? {
        val availablePlayers = players.filter { it != excludedPlayer }
        return if (availablePlayers.isNotEmpty()) availablePlayers.random() else null
    }
}
