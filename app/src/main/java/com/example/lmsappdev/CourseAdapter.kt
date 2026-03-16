package com.example.lmsappdev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    private val courses: List<Triple<String, String, String>>,
    private val onClick: (Triple<String, String, String>) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCourseName: TextView = itemView.findViewById(R.id.tvCourseName)
        val tvInstructor: TextView = itemView.findViewById(R.id.tvInstructor)
        val tvSemester: TextView = itemView.findViewById(R.id.tvSemester)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.tvCourseName.text = course.first
        holder.tvInstructor.text = course.second
        holder.tvSemester.text = course.third
        holder.itemView.setOnClickListener { onClick(course) }
    }

    override fun getItemCount() = courses.size
}