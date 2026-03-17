package com.example.lmsappdev

data class AssignmentItem(
    val title: String,
    val description: String,
    val dueDate: String,
    val points: String,
    val status: String, // "Due Soon", "Upcoming", "Submitted"
    val grade: String = ""
)