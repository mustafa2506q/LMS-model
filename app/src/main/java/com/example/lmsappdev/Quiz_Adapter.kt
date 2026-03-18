package com.example.lmsappdev

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class QuizAdapter(
    private val quizzes: List<QuizItem>
) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuizStatus: TextView = itemView.findViewById(R.id.tvQuizStatus)
        val tvQuizTitle: TextView = itemView.findViewById(R.id.tvQuizTitle)
        val tvQuestions: TextView = itemView.findViewById(R.id.tvQuestions)
        val tvTimeLimit: TextView = itemView.findViewById(R.id.tvTimeLimit)
        val tvAttempts: TextView = itemView.findViewById(R.id.tvAttempts)
        val tvQuizDueDate: TextView = itemView.findViewById(R.id.tvQuizDueDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quiz, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = quizzes[position]

        holder.tvQuizTitle.text = item.title
        holder.tvQuestions.text = item.questions
        holder.tvTimeLimit.text = item.timeLimit
        holder.tvAttempts.text = item.attempts
        holder.tvQuizDueDate.text = "Due: ${item.dueDate}"
        holder.tvQuizStatus.text = item.status

        // Status style
        when (item.status) {
            "Available Now" -> {
                holder.tvQuizStatus.setBackgroundResource(R.drawable.status_submitted_bg)
                holder.tvQuizStatus.setTextColor(Color.parseColor("#27AE60"))
            }
            "Locked" -> {
                holder.tvQuizStatus.setBackgroundResource(R.drawable.status_upcoming_bg)
                holder.tvQuizStatus.setTextColor(Color.parseColor("#888888"))
            }
        }

        // Click listener
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, QuizDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("courseName", "Android App Development")
            intent.putExtra("questions", item.questions)
            intent.putExtra("timeLimit", item.timeLimit)
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount() = quizzes.size
}