package com.example.lmsappdev

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnnouncementDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement_detail)

        // Get data from intent
        val title = intent.getStringExtra("title") ?: ""
        val category = intent.getStringExtra("category") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val body = intent.getStringExtra("body") ?: ""

        // Set data
        findViewById<TextView>(R.id.tvAnnouncementTitle).text = title
        findViewById<TextView>(R.id.tvCategory).text = category
        findViewById<TextView>(R.id.tvAnnouncementDate).text = "📅 $date"
        findViewById<TextView>(R.id.tvAnnouncementBody).text = body

        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}