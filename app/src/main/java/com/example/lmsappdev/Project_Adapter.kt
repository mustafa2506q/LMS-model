package com.example.lmsappdev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectAdapter(
    private val projects: List<ProjectItem>
) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProjectTitle: TextView = itemView.findViewById(R.id.tvProjectTitle)
        val tvProjectDesc: TextView = itemView.findViewById(R.id.tvProjectDesc)
        val tvProgressPercent: TextView = itemView.findViewById(R.id.tvProgressPercent)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        val tvTeamMembers: TextView = itemView.findViewById(R.id.tvTeamMembers)
        val tvProjectDueDate: TextView = itemView.findViewById(R.id.tvProjectDueDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = projects[position]
        holder.tvProjectTitle.text = item.title
        holder.tvProjectDesc.text = item.description
        holder.tvProgressPercent.text = "${item.progress}%"
        holder.progressBar.progress = item.progress
        holder.tvTeamMembers.text = item.teamMembers
        holder.tvProjectDueDate.text = item.dueDate
    }

    override fun getItemCount() = projects.size
}