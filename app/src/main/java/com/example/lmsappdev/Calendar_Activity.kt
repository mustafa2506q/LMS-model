package com.example.lmsappdev

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    private var currentYear = 2026
    private var currentMonth = 2
    private var selectedDay = -1
    private var currentTab = "Month"

    private val events = mapOf(
        15 to listOf("#C0392B"),
        17 to listOf("#E67E22"),
        18 to listOf("#C0392B", "#E67E22"),
        20 to listOf("#2980B9"),
        22 to listOf("#27AE60")
    )

    private val weekEvents = mapOf(
        15 to listOf(Pair("ASSIGNMENT", "Math Assignment 3\nCalculus I")),
        17 to listOf(Pair("ASSIGNMENT", "Essay on Shakespeare\nEnglish Literature")),
        18 to listOf(Pair("QUIZ", "Calculus Quiz 3: Limits\nCalculus I")),
        20 to listOf(Pair("ASSIGNMENT", "Lab Report 2\nPhysics")),
        22 to listOf(Pair("QUIZ", "Programming Quiz 2\nComputer Science"))
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

        tabMonth.setOnClickListener {
            currentTab = "Month"
            setTabSelected(tabMonth)
            showMonthView()
        }

        tabWeek.setOnClickListener {
            currentTab = "Week"
            setTabSelected(tabWeek)
            showWeekView()
        }

        tabAgenda.setOnClickListener {
            currentTab = "Agenda"
            setTabSelected(tabAgenda)
            showAgendaView()
        }

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

    // ===== SHOW VIEWS =====

    private fun showMonthView() {
        findViewById<LinearLayout>(R.id.calendarCard).visibility = View.VISIBLE
        findViewById<FrameLayout>(R.id.weekViewContainer).visibility = View.GONE
        buildCalendar()
    }

    private fun showWeekView() {
        findViewById<LinearLayout>(R.id.calendarCard).visibility = View.GONE
        findViewById<FrameLayout>(R.id.weekViewContainer).visibility = View.VISIBLE
        buildWeekView()
    }

    private fun showAgendaView() {
        findViewById<LinearLayout>(R.id.calendarCard).visibility = View.GONE
        findViewById<FrameLayout>(R.id.weekViewContainer).visibility = View.VISIBLE
        buildAgendaView()
    }

    // ===== AGENDA VIEW =====

    private fun buildAgendaView() {
        val container = findViewById<LinearLayout>(R.id.weekContainer)
        container.removeAllViews()

        val dp = resources.displayMetrics.density

        val agendaEvents = listOf(
            AgendaEvent("Assignment", "Math Assignment 3", "Calculus I",
                15, "Mar", "11:59 PM", "#C0392B", R.drawable.status_due_soon_bg),
            AgendaEvent("Assignment", "Essay on Shakespeare", "English Literature",
                17, "Mar", "11:59 PM", "#E67E22", R.drawable.status_due_soon_bg),
            AgendaEvent("Quiz", "Calculus Quiz 3: Limits", "Calculus I",
                18, "Mar", "10:00 AM", "#C0392B", R.drawable.status_upcoming_bg),
            AgendaEvent("Assignment", "Lab Report 2", "Physics",
                20, "Mar", "11:59 PM", "#2980B9", R.drawable.status_upcoming_bg),
            AgendaEvent("Quiz", "Programming Quiz 2", "Computer Science",
                22, "Mar", "02:00 PM", "#27AE60", R.drawable.status_submitted_bg),
            AgendaEvent("Assignment", "Integration Assignment", "Calculus I",
                25, "Mar", "11:59 PM", "#E67E22", R.drawable.status_due_soon_bg),
            AgendaEvent("Quiz", "Physics Quiz 1", "Physics",
                27, "Mar", "09:00 AM", "#8E44AD", R.drawable.status_upcoming_bg)
        )

        agendaEvents.forEach { event ->

            val outerCard = CardView(this).apply {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, (10 * dp).toInt())
                layoutParams = params
                radius = 16 * dp
                cardElevation = 2 * dp
            }

            val outerLayout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.HORIZONTAL
                setBackgroundColor(Color.WHITE)
            }

            // Left color bar
            val leftBar = View(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    (4 * dp).toInt(),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(Color.parseColor(event.borderColor))
            }

            // Content layout
            val contentLayout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                orientation = LinearLayout.VERTICAL
                setPadding(
                    (14 * dp).toInt(), (14 * dp).toInt(),
                    (8 * dp).toInt(), (14 * dp).toInt()
                )
            }

            // Tag
            val tagTv = TextView(this).apply {
                text = event.type
                textSize = 11f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(Color.parseColor(event.borderColor))
                background = getDrawable(event.tagBg)
                setPadding(
                    (8 * dp).toInt(), (3 * dp).toInt(),
                    (8 * dp).toInt(), (3 * dp).toInt()
                )
                val p = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                p.setMargins(0, 0, 0, (8 * dp).toInt())
                layoutParams = p
            }

            // Title
            val titleTv = TextView(this).apply {
                text = event.title
                textSize = 15f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(Color.parseColor("#222222"))
                val p = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                p.setMargins(0, 0, 0, (4 * dp).toInt())
                layoutParams = p
            }

            // Course
            val courseTv = TextView(this).apply {
                text = event.course
                textSize = 12f
                setTextColor(Color.parseColor("#888888"))
                val p = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                p.setMargins(0, 0, 0, (6 * dp).toInt())
                layoutParams = p
            }

            // Time
            val timeTv = TextView(this).apply {
                text = "🕐 ${event.time}"
                textSize = 11f
                setTextColor(Color.parseColor("#AAAAAA"))
            }

            contentLayout.addView(tagTv)
            contentLayout.addView(titleTv)
            contentLayout.addView(courseTv)
            contentLayout.addView(timeTv)

            // Right date layout
            val dateLayout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    (56 * dp).toInt(),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(
                    (8 * dp).toInt(), (14 * dp).toInt(),
                    (14 * dp).toInt(), (14 * dp).toInt()
                )
            }

            // Day number
            val dayTv = TextView(this).apply {
                text = event.day.toString()
                textSize = 22f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(Color.parseColor("#222222"))
                gravity = Gravity.CENTER
            }

            // Month
            val monthTv = TextView(this).apply {
                text = event.month
                textSize = 12f
                setTextColor(Color.parseColor("#888888"))
                gravity = Gravity.CENTER
            }

            dateLayout.addView(dayTv)
            dateLayout.addView(monthTv)

            outerLayout.addView(leftBar)
            outerLayout.addView(contentLayout)
            outerLayout.addView(dateLayout)
            outerCard.addView(outerLayout)
            container.addView(outerCard)
        }
    }

    // ===== WEEK VIEW =====

    private fun buildWeekView() {
        val container = findViewById<LinearLayout>(R.id.weekContainer)
        container.removeAllViews()

        val today = Calendar.getInstance()
        val todayDay = today.get(Calendar.DAY_OF_MONTH)
        val todayMonth = today.get(Calendar.MONTH)
        val todayYear = today.get(Calendar.YEAR)

        val dayNames = arrayOf("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
        val dp = resources.displayMetrics.density

        val cal = Calendar.getInstance()

        for (i in 0..6) {
            val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
            val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1
            val isToday = dayOfMonth == todayDay &&
                    cal.get(Calendar.MONTH) == todayMonth &&
                    cal.get(Calendar.YEAR) == todayYear

            val hasEvents = weekEvents.containsKey(dayOfMonth) &&
                    cal.get(Calendar.MONTH) == currentMonth

            val dayCard = CardView(this).apply {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, (8 * dp).toInt())
                layoutParams = params
                radius = 16 * dp
                cardElevation = 2 * dp
            }

            val dayLayout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.VERTICAL
                setPadding(
                    (16 * dp).toInt(), (16 * dp).toInt(),
                    (16 * dp).toInt(), (16 * dp).toInt()
                )
                setBackgroundColor(
                    if (isToday) Color.parseColor("#C0392B") else Color.WHITE
                )
            }

            val dayNameTv = TextView(this).apply {
                text = dayNames[dayOfWeek]
                textSize = 13f
                setTextColor(
                    if (isToday) Color.parseColor("#FFCCCC")
                    else Color.parseColor("#888888")
                )
            }

            val dayNumTv = TextView(this).apply {
                text = dayOfMonth.toString()
                textSize = 28f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(
                    if (isToday) Color.WHITE
                    else Color.parseColor("#222222")
                )
            }

            if (hasEvents && !isToday) {
                val topRow = LinearLayout(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                }

                val leftCol = LinearLayout(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    orientation = LinearLayout.VERTICAL
                }
                leftCol.addView(dayNameTv)
                leftCol.addView(dayNumTv)

                val count = weekEvents[dayOfMonth]?.size ?: 0
                val eventBadge = TextView(this).apply {
                    text = "$count event"
                    textSize = 12f
                    setTextColor(Color.parseColor("#C0392B"))
                    background = getDrawable(R.drawable.tag_bg)
                    setPadding(
                        (10 * dp).toInt(), (4 * dp).toInt(),
                        (10 * dp).toInt(), (4 * dp).toInt()
                    )
                }

                topRow.addView(leftCol)
                topRow.addView(eventBadge)
                dayLayout.addView(topRow)
            } else {
                dayLayout.addView(dayNameTv)
                dayLayout.addView(dayNumTv)
            }

            dayCard.addView(dayLayout)
            container.addView(dayCard)

            if (hasEvents) {
                weekEvents[dayOfMonth]?.forEach { event ->

                    val borderColor = if (event.first == "ASSIGNMENT") "#27AE60" else "#2980B9"
                    val tagBg = if (event.first == "ASSIGNMENT")
                        R.drawable.status_submitted_bg else R.drawable.status_upcoming_bg
                    val tagColor = if (event.first == "ASSIGNMENT") "#27AE60" else "#2980B9"

                    val outerCard = CardView(this).apply {
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 0, (8 * dp).toInt())
                        layoutParams = params
                        radius = 12 * dp
                        cardElevation = 2 * dp
                    }

                    val outerLayout = LinearLayout(this).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        orientation = LinearLayout.HORIZONTAL
                        setBackgroundColor(Color.WHITE)
                    }

                    val leftBar = View(this).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            (4 * dp).toInt(),
                            LinearLayout.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(Color.parseColor(borderColor))
                    }

                    val innerLayout = LinearLayout(this).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        orientation = LinearLayout.VERTICAL
                        setPadding(
                            (14 * dp).toInt(), (14 * dp).toInt(),
                            (14 * dp).toInt(), (14 * dp).toInt()
                        )
                    }

                    val typeTv = TextView(this).apply {
                        text = event.first
                        textSize = 11f
                        typeface = Typeface.DEFAULT_BOLD
                        setTextColor(Color.parseColor(tagColor))
                        background = getDrawable(tagBg)
                        setPadding(
                            (8 * dp).toInt(), (3 * dp).toInt(),
                            (8 * dp).toInt(), (3 * dp).toInt()
                        )
                        val p = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        p.setMargins(0, 0, 0, (8 * dp).toInt())
                        layoutParams = p
                    }

                    val lines = event.second.split("\n")

                    val titleTv = TextView(this).apply {
                        text = lines[0]
                        textSize = 15f
                        typeface = Typeface.DEFAULT_BOLD
                        setTextColor(Color.parseColor("#222222"))
                    }

                    val courseTv = TextView(this).apply {
                        text = if (lines.size > 1) lines[1] else ""
                        textSize = 12f
                        setTextColor(Color.parseColor("#888888"))
                    }

                    innerLayout.addView(typeTv)
                    innerLayout.addView(titleTv)
                    innerLayout.addView(courseTv)

                    outerLayout.addView(leftBar)
                    outerLayout.addView(innerLayout)
                    outerCard.addView(outerLayout)
                    container.addView(outerCard)
                }
            } else {
                val noEventCard = CardView(this).apply {
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 0, 0, (8 * dp).toInt())
                    layoutParams = params
                    radius = 12 * dp
                    cardElevation = 2 * dp
                }

                val noEventLayout = LinearLayout(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        (44 * dp).toInt()
                    )
                    gravity = Gravity.CENTER
                    setBackgroundColor(Color.parseColor("#F8F8F8"))
                }

                val noEventTv = TextView(this).apply {
                    text = "No events"
                    textSize = 13f
                    setTextColor(Color.parseColor("#AAAAAA"))
                    gravity = Gravity.CENTER
                }

                noEventLayout.addView(noEventTv)
                noEventCard.addView(noEventLayout)
                container.addView(noEventCard)
            }

            cal.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    // ===== MONTH VIEW =====

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

                    val dayTv = TextView(this).apply {
                        text = day.toString()
                        textSize = 14f
                        gravity = Gravity.CENTER
                        layoutParams = LinearLayout.LayoutParams(circleSizePx, circleSizePx)
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
                                val params = LinearLayout.LayoutParams(dotWidthPx, dotHeightPx)
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

// Data class for agenda events
data class AgendaEvent(
    val type: String,
    val title: String,
    val course: String,
    val day: Int,
    val month: String,
    val time: String,
    val borderColor: String,
    val tagBg: Int
)