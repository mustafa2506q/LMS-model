package com.example.lmsappdev.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.lmsappdev.AnnouncementDetailActivity
import com.example.lmsappdev.R

class GeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Announcement 1 click
        view.findViewById<LinearLayout>(R.id.cardAnnouncement1).setOnClickListener {
            val intent = Intent(requireContext(), AnnouncementDetailActivity::class.java)
            intent.putExtra("title", "Midterm Schedule Released")
            intent.putExtra("category", "Academic Affairs")
            intent.putExtra("date", "March 9, 2026 • 2 hours ago")
            intent.putExtra("body",
                "Dear Students,\n\n" +
                        "We are pleased to announce that the midterm examination schedule for Spring 2026 semester has been released.\n\n" +
                        "Key Details:\n" +
                        "• Midterm exams will be held from March 20-27, 2026\n" +
                        "• Exam schedules are now available on your student portal\n" +
                        "• Each exam will be 2 hours in duration\n" +
                        "• Students must arrive 15 minutes before the exam start time\n" +
                        "• Valid student ID is mandatory for entry\n\n" +
                        "Please check your individual schedules and plan accordingly. If you have any conflicts or concerns, contact the Academic Affairs office before March 15, 2026.\n\n" +
                        "Best regards,\nAcademic Affairs Office"
            )
            startActivity(intent)
        }

        // Announcement 2 click
        view.findViewById<LinearLayout>(R.id.cardAnnouncement2).setOnClickListener {
            val intent = Intent(requireContext(), AnnouncementDetailActivity::class.java)
            intent.putExtra("title", "Assignment 3 Extension")
            intent.putExtra("category", "Course Update")
            intent.putExtra("date", "March 7, 2026 • 3 days ago")
            intent.putExtra("body",
                "Dear Students,\n\n" +
                        "Due to technical issues experienced on our learning platform, we have decided to extend the deadline for Assignment 3.\n\n" +
                        "New Deadline:\n" +
                        "• Assignment 3 is now due on March 17, 2026 at 11:59 PM\n" +
                        "• No further extensions will be granted\n" +
                        "• Please submit through the student portal\n\n" +
                        "If you have already submitted, no action is required. We apologize for any inconvenience caused.\n\n" +
                        "Best regards,\nYour Instructor"
            )
            startActivity(intent)
        }
    }
}