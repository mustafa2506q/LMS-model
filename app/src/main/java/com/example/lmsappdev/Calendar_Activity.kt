package com.example.lmsappdev

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    private var currentYear = 2026
    private var currentMonth = 2
    private var selectedDay = -1

    private val events = mapOf(
        15 to listOf("#C0392B"),
        17 to listOf("#E67E22"),
        18 to listOf("#C0392B", "#E67E22"),
        20 to listOf("#2980B9"),
        22 to listOf("#27AE60")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val today = Calendar.getInstance()
        currentYear = today.get(Calendar.YEAR)
        currentMonth = today.get(Calendar.MONTH)
        selectedDay = today.get(Calendar.DAY_OF_MONTH)

        updateMonthLabel()
        buildCalendar()

        findViewById<TextView>(R.id.btnPrevMonth).setOnClickListener {
            currentMonth--
            if (currentMonth < 0) { currentMonth = 11; currentYear-- }
            selectedDay = -1
            updateMonthLabel()
            buildCalendar()
        }

        findViewById<TextView>(R.id.btnNextMonth).setOnClickListener {
            currentMonth++
            if (currentMonth > 11) { currentMonth = 0; currentYear++ }
            selectedDay = -1
            updateMonthLabel()
            buildCalendar()
        }

        val tabMonth = findViewById<TextView>(R.id.tabMonth)
        val tabWeek = findViewById<TextView>(R.id.tabWeek)
        val tabAgenda = findViewById<TextView>(R.id.tabAgenda)
        tabMonth.setOnClickListener { setTabSelected(tabMonth) }
        tabWeek.setOnClickListener { setTabSelected(tabWeek) }
        tabAgenda.setOnClickListener { setTabSelected(tabAgenda) }

        findViewById<LinearLayout>(R.id.navOverview).setOnClickListener {
            startActivity(Intent(this, dashboard::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {}
        findViewById<LinearLayout>(R.id.navProjects).setOnClickListener {}
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {}
    }

    private fun updateMonthLabel() {
        val months = arrayOf("January","February","March","April","May","June",
            "July","August","September","October","November","December")
        findViewById<TextView>(R.id.tvMonthYear).text = "${months[currentMonth]} $currentYear"
    }

    private fun buildCalendar() {
        val grid = findViewById<LinearLayout>(R.id.calendarGrid)
        grid.removeAllViews()

        val cal = Calendar.getInstance()
        cal.set(currentYear, currentMonth, 1)
        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1
        val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val today = Calendar.getInstance()
        val isCurrentMonth = today.get(Calendar.YEAR) == currentYear &&
                today.get(Calendar.MONTH) == currentMonth
        val todayDay = if (isCurrentMonth) today.get(Calendar.DAY_OF_MONTH) else -1

        // Convert dp to px for consistent sizing
        val dp = resources.displayMetrics.density
        val cellHeightPx = (52 * dp).toInt()
        val circleSizePx = (38 * dp).toInt()
        val dotHeightPx = (4 * dp).toInt()
        val dotWidthPx = (20 * dp).toInt()

        var dayCount = 1

        for (week in 0..5) {
            if (dayCount > daysInMonth) break

            val weekRow = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                orientation = LinearLayout.HORIZONTAL
            }

            for (col in 0..6) {
                val cell = LinearLayout(this).apply {
                    layoutParams = LinearLayout.LayoutParams(0, cellHeightPx, 1f)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                val skip = week == 0 && col < firstDayOfWeek
                val done = dayCount > daysInMonth

                if (!skip && !done) {
                    val day = dayCount
                    val isToday = day == todayDay
                    val isSelected = day == selectedDay

                    // Day number TextView
                    val dayTv = TextView(this).apply {
                        text = day.toString()
                        textSize = 14f
                        gravity = Gravity.CENTER
                        layoutParams = LinearLayout.LayoutParams(
                            circleSizePx, circleSizePx)
                        typeface = if (isToday || isSelected)
                            Typeface.DEFAULT_BOLD else Typeface.DEFAULT

                        when {
                            isSelected -> {
                                setBackgroundResource(R.drawable.selected_day_bg)
                                setTextColor(Color.WHITE)
                            }
                            isToday -> {
                                setBackgroundResource(R.drawable.today_day_bg)
                                setTextColor(Color.parseColor("#C0392B"))
                            }
                            else -> {
                                setBackgroundColor(Color.TRANSPARENT)
                                setTextColor(Color.parseColor("#222222"))
                            }
                        }
                    }
                    cell.addView(dayTv)

                    // Event color bars
                    events[day]?.let { colors ->
                        val dotsRow = LinearLayout(this).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                        }
                        colors.forEach { color ->
                            val bar = View(this).apply {
                                val params = LinearLayout.LayoutParams(
                                    dotWidthPx, dotHeightPx)
                                params.setMargins(2, 3, 2, 0)
                                layoutParams = params
                                setBackgroundColor(Color.parseColor(color))
                            }
                            dotsRow.addView(bar)
                        }
                        cell.addView(dotsRow)
                    }

                    cell.setOnClickListener {
                        selectedDay = day
                        buildCalendar()
                    }

                    dayCount++
                }

                weekRow.addView(cell)
            }
            grid.addView(weekRow)
        }
    }

    private fun setTabSelected(selected: TextView) {
        listOf(
            findViewById<TextView>(R.id.tabMonth),
            findViewById<TextView>(R.id.tabWeek),
            findViewById<TextView>(R.id.tabAgenda)
        ).forEach {
            it.setBackgroundResource(R.drawable.tab_unselected_bg)
            it.setTextColor(getColor(R.color.text_gray))
        }
        selected.setBackgroundResource(R.drawable.tab_selected_bg)
        selected.setTextColor(getColor(R.color.white))
    }
}