package com.example.lmsappdev

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Set current date
        val dateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.tvDate).text = dateFormat.format(Date())

        // Load courses horizontally
        val courses = listOf(
            Triple("Database Management System", "DBMS 301", R.color.red_primary),
            Triple("Cryptography", "CRYPT 401", R.color.red_primary),
            Triple("Data Structures & Algorithms", "DSA 201", R.color.red_primary),
            Triple("Android App Development", "AAD 301", R.color.red_primary),
            Triple("Computer Network", "CN 401", R.color.red_primary)
        )

        val llCourses = findViewById<LinearLayout>(R.id.llCourses)
        for (course in courses) {
            val courseCard = layoutInflater.inflate(R.layout.item_course_small, llCourses, false)
            courseCard.findViewById<TextView>(R.id.tvCourseNameSmall).text = course.first
            courseCard.findViewById<TextView>(R.id.tvCourseCodeSmall).text = course.second
            courseCard.setOnClickListener {
                val intent = Intent(this, course_detail::class.java)
                intent.putExtra("courseName", course.first)
                intent.putExtra("instructor", "Dr. Sarah Johnson")
                intent.putExtra("semester", "Spring 2026")
                startActivity(intent)
            }
            llCourses.addView(courseCard)
        }

        // View All click
        findViewById<TextView>(R.id.tvViewAll).setOnClickListener {
            val intent = Intent(this, AllCoursesActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation
        findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {
            // Will build Calendar screen later
        }
        findViewById<LinearLayout>(R.id.navProjects).setOnClickListener {
            // Will build Projects screen later
        }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            // Will build Profile screen later
        }
    }
}