package com.example.socialswig.model

data class Question(
    val text: String,
    val type: QuestionType // Enum för typ av fråga
)

enum class QuestionType {
    TIMED,
    CLASSIC,
    NAUGHTY
}
