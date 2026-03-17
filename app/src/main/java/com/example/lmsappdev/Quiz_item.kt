package com.example.lmsappdev

data class QuizItem(
    val title: String,
    val questions: String,
    val timeLimit: String,
    val attempts: String,
    val dueDate: String,
    val status: String // "Available Now" or "Locked"
)