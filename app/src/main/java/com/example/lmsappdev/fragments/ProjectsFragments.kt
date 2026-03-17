package com.example.lmsappdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsappdev.ProjectAdapter
import com.example.lmsappdev.ProjectItem
import com.example.lmsappdev.R

class ProjectsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projects = listOf(
            ProjectItem(
                title = "Final Project: LMS Android App",
                description = "Build a complete Learning Management System Android application",
                progress = 35,
                teamMembers = "Mustafa, Prince., Milind",
                dueDate = "Mar 28, 2026"
            ),
            ProjectItem(
                title = "Mid-term Project: UI/UX Design",
                description = "Design and implement modern Android UI components",
                progress = 85,
                teamMembers = "Mustafa, Prince, Milind",
                dueDate = "Mar 12, 2026"
            ),
            ProjectItem(
                title = "Mini Project: Firebase Integration",
                description = "Integrate Firebase authentication and Firestore database",
                progress = 60,
                teamMembers = "Mustafa, Prince, Milind",
                dueDate = "Apr 5, 2026"
            )
        )

        val rv = view.findViewById<RecyclerView>(R.id.rvProjects)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = ProjectAdapter(projects)
    }
}