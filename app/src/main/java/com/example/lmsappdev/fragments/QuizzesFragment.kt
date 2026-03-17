package com.example.lmsappdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsappdev.QuizAdapter
import com.example.lmsappdev.QuizItem
import com.example.lmsappdev.R

class QuizzesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quizzes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizzes = listOf(
            QuizItem(
                title = "Quiz 2: Limits",
                questions = "10 questions",
                timeLimit = "30 min time limit",
                attempts = "1 attempt allowed",
                dueDate = "Mar 18, 2026",
                status = "Available Now"
            ),
            QuizItem(
                title = "Quiz 3: Continuity",
                questions = "12 questions",
                timeLimit = "35 min time limit",
                attempts = "1 attempt allowed",
                dueDate = "Mar 25, 2026",
                status = "Locked"
            ),
            QuizItem(
                title = "Quiz 1: Derivatives",
                questions = "8 questions",
                timeLimit = "25 min time limit",
                attempts = "1 attempt allowed",
                dueDate = "Mar 10, 2026",
                status = "Available Now"
            )
        )

        val rv = view.findViewById<RecyclerView>(R.id.rvQuizzes)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = QuizAdapter(quizzes)
    }
}