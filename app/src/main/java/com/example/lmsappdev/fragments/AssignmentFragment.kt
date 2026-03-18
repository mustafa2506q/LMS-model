package com.example.lmsappdev.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsappdev.AssignmentAdapter
import com.example.lmsappdev.AssignmentDetailActivity
import com.example.lmsappdev.AssignmentItem
import com.example.lmsappdev.R

class AssignmentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assignment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assignments = listOf(
            AssignmentItem(
                title = "Assignment 2: Derivatives",
                description = "Complete problems 1-20 on derivatives",
                dueDate = "Mar 15, 2026",
                points = "100 pts",
                status = "Due Soon"
            ),
            AssignmentItem(
                title = "Assignment 3: Integration",
                description = "Integration techniques and applications",
                dueDate = "Mar 22, 2026",
                points = "100 pts",
                status = "Upcoming"
            ),
            AssignmentItem(
                title = "Assignment 1: Limits",
                description = "Limit problems and continuity",
                dueDate = "Mar 8, 2026",
                points = "100 pts",
                status = "Submitted",
                grade = "Grade: 95/100"
            )
        )

        val rv = view.findViewById<RecyclerView>(R.id.rvAssignments)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = AssignmentAdapter(assignments) { assignment ->
            val intent = Intent(requireContext(), AssignmentDetailActivity::class.java)
            intent.putExtra("title", assignment.title)
            intent.putExtra("courseName", "Android App Development")
            intent.putExtra("dueDate", "${assignment.dueDate} 11:59 PM")
            intent.putExtra("points", "100")
            intent.putExtra("status", assignment.status)
            startActivity(intent)
        }
    }
}