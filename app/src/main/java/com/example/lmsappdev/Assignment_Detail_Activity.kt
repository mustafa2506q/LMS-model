package com.example.lmsappdev

import android.os.Bundle
import android.graphics.Typeface
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AssignmentDetailActivity : AppCompatActivity() {

    private lateinit var tabBrief: TextView
    private lateinit var tabRubrics: TextView
    private lateinit var contentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_detail)

        // Get data from intent
        val title = intent.getStringExtra("title") ?: "Assignment"
        val courseName = intent.getStringExtra("courseName") ?: ""
        val dueDate = intent.getStringExtra("dueDate") ?: ""
        val points = intent.getStringExtra("points") ?: "100"
        val status = intent.getStringExtra("status") ?: "Upcoming"

        // Set header data
        findViewById<TextView>(R.id.tvAssignmentTitle).text = title
        findViewById<TextView>(R.id.tvCourseName).text = courseName
        findViewById<TextView>(R.id.tvDueDate).text = "📅 Due: $dueDate"
        findViewById<TextView>(R.id.tvPoints).text = points

        // Set status
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        tvStatus.text = status
        when (status) {
            "Due Soon" -> {
                tvStatus.setBackgroundResource(R.drawable.status_due_soon_bg)
                tvStatus.setTextColor(getColor(android.R.color.holo_red_dark))
            }
            "Upcoming" -> {
                tvStatus.setBackgroundResource(R.drawable.status_upcoming_bg)
                tvStatus.setTextColor(getColor(android.R.color.holo_blue_dark))
            }
            "Submitted" -> {
                tvStatus.setBackgroundResource(R.drawable.status_submitted_bg)
                tvStatus.setTextColor(getColor(android.R.color.holo_green_dark))
            }
        }

        // Init tabs and container
        tabBrief = findViewById(R.id.tabBrief)
        tabRubrics = findViewById(R.id.tabRubrics)
        contentContainer = findViewById(R.id.contentContainer)

        // Load Brief by default
        loadBrief()

        // Tab clicks
        tabBrief.setOnClickListener {
            loadBrief()
        }

        tabRubrics.setOnClickListener {
            loadRubrics()
        }

        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Submit button
        findViewById<TextView>(R.id.btnSubmit).setOnClickListener {
            Toast.makeText(this,
                "Assignment submitted successfully!",
                Toast.LENGTH_SHORT).show()
        }

        // Upload box click
        findViewById<android.widget.LinearLayout>(R.id.uploadBox).setOnClickListener {
            Toast.makeText(this,
                "File picker coming soon!",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadBrief() {
        // Update tab styles
        tabBrief.setBackgroundResource(R.drawable.tab_selected_bg)
        tabBrief.setTextColor(getColor(R.color.white))
        tabBrief.typeface = Typeface.DEFAULT_BOLD
        tabRubrics.setBackgroundResource(android.R.color.transparent)
        tabRubrics.setTextColor(getColor(R.color.text_gray))
        tabRubrics.typeface = Typeface.DEFAULT

        // Load brief layout
        contentContainer.removeAllViews()
        val briefView = LayoutInflater.from(this)
            .inflate(R.layout.fragment_brief, contentContainer, false)
        contentContainer.addView(briefView)
    }

    private fun loadRubrics() {
        // Update tab styles
        tabRubrics.setBackgroundResource(R.drawable.tab_selected_bg)
        tabRubrics.setTextColor(getColor(R.color.white))
        tabRubrics.typeface = Typeface.DEFAULT_BOLD
        tabBrief.setBackgroundResource(android.R.color.transparent)
        tabBrief.setTextColor(getColor(R.color.text_gray))
        tabBrief.typeface = Typeface.DEFAULT

        // Load rubrics layout
        contentContainer.removeAllViews()
        val rubricsView = LayoutInflater.from(this)
            .inflate(R.layout.fragment_rubrics, contentContainer, false)
        contentContainer.addView(rubricsView)
    }
}