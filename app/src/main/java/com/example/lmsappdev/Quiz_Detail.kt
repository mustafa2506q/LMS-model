package com.example.lmsappdev

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_detail)

        // Get data from intent
        val title = intent.getStringExtra("title") ?: "Quiz"
        val courseName = intent.getStringExtra("courseName") ?: ""
        val questions = intent.getStringExtra("questions") ?: "10 Questions"
        val timeLimit = intent.getStringExtra("timeLimit") ?: "30 Minutes"

        // Set data
        findViewById<TextView>(R.id.tvQuizTitle).text = title
        findViewById<TextView>(R.id.tvCourseName).text = courseName
        findViewById<TextView>(R.id.tvQuestions).text = questions
        findViewById<TextView>(R.id.tvTimeLimit).text = timeLimit

        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Start Quiz button
        findViewById<TextView>(R.id.btnStartQuiz).setOnClickListener {
            val accessCode = findViewById<EditText>(R.id.etAccessCode).text.toString().trim()
            if (accessCode.isEmpty()) {
                Toast.makeText(this,
                    "Please enter access code",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,
                    "Starting Quiz...",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}