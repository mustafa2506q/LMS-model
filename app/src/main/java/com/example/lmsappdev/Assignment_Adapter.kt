package com.example.lmsappdev

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AssignmentAdapter(
    private val assignments: List<AssignmentItem>,
    private val onClick: (AssignmentItem) -> Unit
) : RecyclerView.Adapter<AssignmentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvPoints: TextView = itemView.findViewById(R.id.tvPoints)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvDueDate: TextView = itemView.findViewById(R.id.tvDueDate)
        val tvGrade: TextView = itemView.findViewById(R.id.tvGrade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_assignment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = assignments[position]

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvDueDate.text = "Due: ${item.dueDate}"
        holder.tvPoints.text = item.points
        holder.tvStatus.text = item.status
        holder.tvGrade.text = item.grade

        // Status style
        when (item.status) {
            "Due Soon" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.status_due_soon_bg)
                holder.tvStatus.setTextColor(Color.parseColor("#E74C3C"))
            }
            "Upcoming" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.status_upcoming_bg)
                holder.tvStatus.setTextColor(Color.parseColor("#2980B9"))
            }
            "Submitted" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.status_submitted_bg)
                holder.tvStatus.setTextColor(Color.parseColor("#27AE60"))
            }
        }

        // Click listener
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = assignments.size
}