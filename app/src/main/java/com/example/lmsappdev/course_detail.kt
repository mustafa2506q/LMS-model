package com.example.lmsappdev

import android.os.Bundle
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lmsappdev.fragments.GeneralFragment
import com.example.lmsappdev.fragments.SyllabusFragment
import com.example.lmsappdev.fragments.AssignmentsFragment
import com.example.lmsappdev.fragments.QuizzesFragment
import com.example.lmsappdev.fragments.ActivitiesFragment
import com.example.lmsappdev.fragments.ProjectsFragment

class course_detail : AppCompatActivity() {

    private lateinit var tabGeneral: LinearLayout
    private lateinit var tabSyllabus: LinearLayout
    private lateinit var tabAssignments: LinearLayout
    private lateinit var tabQuizzes: LinearLayout
    private lateinit var tabActivities: LinearLayout
    private lateinit var tabProjects: LinearLayout

    private lateinit var tvGeneral: TextView
    private lateinit var tvSyllabus: TextView
    private lateinit var tvAssignments: TextView
    private lateinit var tvQuizzes: TextView
    private lateinit var tvActivities: TextView
    private lateinit var tvProjects: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        // Get data passed from Dashboard
        val courseName = intent.getStringExtra("courseName") ?: "Course"
        val instructor = intent.getStringExtra("instructor") ?: ""
        val semester = intent.getStringExtra("semester") ?: ""

        // Set header data
        findViewById<TextView>(R.id.tvCourseTitle).text = courseName
        findViewById<TextView>(R.id.tvCourseInstructor).text = instructor
        findViewById<TextView>(R.id.tvCourseSemester).text = semester

        // Init tabs
        tabGeneral = findViewById(R.id.tabGeneral)
        tabSyllabus = findViewById(R.id.tabSyllabus)
        tabAssignments = findViewById(R.id.tabAssignments)
        tabQuizzes = findViewById(R.id.tabQuizzes)
        tabActivities = findViewById(R.id.tabActivities)
        tabProjects = findViewById(R.id.tabProjects)

        // Init TextViews
        tvGeneral = findViewById(R.id.tvGeneral)
        tvSyllabus = findViewById(R.id.tvSyllabus)
        tvAssignments = findViewById(R.id.tvAssignments)
        tvQuizzes = findViewById(R.id.tvQuizzes)
        tvActivities = findViewById(R.id.tvActivities)
        tvProjects = findViewById(R.id.tvProjects)

        // Tab click listeners
        tabGeneral.setOnClickListener { switchTab("General") }
        tabSyllabus.setOnClickListener { switchTab("Syllabus") }
        tabAssignments.setOnClickListener { switchTab("Assignments") }
        tabQuizzes.setOnClickListener { switchTab("Quizzes") }
        tabActivities.setOnClickListener { switchTab("Activities") }
        tabProjects.setOnClickListener { switchTab("Projects") }

        // Load General by default
        switchTab("General")
    }

    private fun switchTab(tabName: String) {
        resetAllTabs()

        val fragment: Fragment = when (tabName) {
            "Syllabus" -> {
                setTabSelected(tabSyllabus, tvSyllabus)
                SyllabusFragment()
            }
            "Assignments" -> {
                setTabSelected(tabAssignments, tvAssignments)
                AssignmentsFragment()
            }
            "Quizzes" -> {
                setTabSelected(tabQuizzes, tvQuizzes)
                QuizzesFragment()
            }
            "Activities" -> {
                setTabSelected(tabActivities, tvActivities)
                ActivitiesFragment()
            }
            "Projects" -> {
                setTabSelected(tabProjects, tvProjects)
                ProjectsFragment()
            }
            else -> {
                setTabSelected(tabGeneral, tvGeneral)
                GeneralFragment()
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun resetAllTabs() {
        listOf(
            tabGeneral, tabSyllabus, tabAssignments,
            tabQuizzes, tabActivities, tabProjects
        ).forEach {
            it.setBackgroundResource(R.drawable.tab_unselected_bg)
        }

        listOf(
            tvGeneral, tvSyllabus, tvAssignments,
            tvQuizzes, tvActivities, tvProjects
        ).forEach {
            it.setTextColor(getColor(R.color.black))
            it.typeface = Typeface.DEFAULT
        }
    }

    private fun setTabSelected(tab: LinearLayout, tv: TextView) {
        tab.setBackgroundResource(R.drawable.tab_selected_bg)
        tv.setTextColor(getColor(R.color.white))
        tv.typeface = Typeface.DEFAULT_BOLD
    }
}