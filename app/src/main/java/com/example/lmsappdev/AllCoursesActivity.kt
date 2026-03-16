package com.example.lmsappdev

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AllCoursesActivity : AppCompatActivity() {

    private val courses = listOf(
        CourseItem("Calculus I", "Dr. Sarah Johnson", "MATH 101", "45 students", "Spring 2026", "#C0392B"),
        CourseItem("Computer Science Fundamentals", "Prof. Michael Chen", "CS 101", "60 students", "Spring 2026", "#2980B9"),
        CourseItem("English Literature", "Dr. Emily Brown", "ENG 201", "35 students", "Spring 2026", "#E67E22"),
        CourseItem("Physics II", "Dr. Mark Lee", "PHY 201", "50 students", "Spring 2026", "#27AE60"),
        CourseItem("Chemistry", "Dr. Aisha Khan", "CHEM 101", "40 students", "Spring 2026", "#8E44AD")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_courses)

        // Load courses
        val llAllCourses = findViewById<LinearLayout>(R.id.llAllCourses)
        for (course in courses) {
            val view = LayoutInflater.from(this)
                .inflate(R.layout.item_course_detail, llAllCourses, false)

            view.findViewById<TextView>(R.id.tvCourseName).text = course.name
            view.findViewById<TextView>(R.id.tvInstructor).text = course.instructor
            view.findViewById<TextView>(R.id.tvCourseCode).text = course.code
            view.findViewById<TextView>(R.id.tvStudents).text = "👥 ${course.students}"
            view.findViewById<TextView>(R.id.tvSemesterTag).text = course.semester

            // Set icon background color
            val iconView = view.findViewById<TextView>(R.id.tvCourseIcon)
            val drawable = androidx.core.content.ContextCompat
                .getDrawable(this, R.drawable.course_icon_bg) as android.graphics.drawable.GradientDrawable
            drawable.setColor(android.graphics.Color.parseColor(course.iconColor))
            iconView.background = drawable
            iconView.setTextColor(android.graphics.Color.WHITE)

            // Click to open course detail
            view.setOnClickListener {
                val intent = Intent(this, course_detail::class.java)
                intent.putExtra("courseName", course.name)
                intent.putExtra("instructor", course.instructor)
                intent.putExtra("semester", course.semester)
                startActivity(intent)
            }

            llAllCourses.addView(view)
        }

        // Bottom nav
        findViewById<LinearLayout>(R.id.navOverview).setOnClickListener {
            val intent = Intent(this, dashboard::class.java)
            startActivity(intent)
            finish()
        }
    }
}

data class CourseItem(
    val name: String,
    val instructor: String,
    val code: String,
    val students: String,
    val semester: String,
    val iconColor: String
)