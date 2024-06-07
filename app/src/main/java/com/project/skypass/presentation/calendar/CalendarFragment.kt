package com.project.skypass.presentation.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.project.skypass.R
import com.project.skypass.databinding.FragmentCalendarBinding
import com.project.skypass.presentation.customview.DateSelection
import com.project.skypass.utils.displayText
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCalendarBinding
    var dateSelection: DateSelection? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView()
    }

    private fun calendarView() {
        binding.rvDate.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                // Set the calendar day for this container.
                container.day = data
                //binding.tvMonthCalendar.text = data.date.month.toString()
                // Set the date text
                container.textView.text = data.date.dayOfMonth.toString()
                // Any other binding logic
                if (data.position == DayPosition.MonthDate) {
                    container.textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.colorTextCalendar))
                } else {
                    container.textView.setTextColor(Color.GRAY)
                }
                container.textView.setOnClickListener {
                    val selectedDate = data.date.toString()
                    dateSelection?.onDateSelected(tag ?: "", selectedDate)
                    dismiss()
                }
            }
        }
        val daysOfWeek = daysOfWeek()
        binding.layoutWeek.root.children
            .map { it as TextView }
            .forEachIndexed { index, textView ->
                textView.text = daysOfWeek[index].displayText()
            }
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)  // Adjust as needed
        val endMonth = currentMonth.plusMonths(100)  // Adjust as needed
        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        binding.rvDate.setup(startMonth, endMonth, firstDayOfWeek)
        binding.rvDate.scrollToMonth(currentMonth)
        binding.rvDate.monthScrollListener = { month ->
            //binding.tvMonthCalendar.text = month.yearMonth.month.toString()
            val formatter = DateTimeFormatter.ofPattern(getString(R.string.format_month_year))
            binding.tvMonthCalendar.text = month.yearMonth.format(formatter)
        }
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = view.findViewById<TextView>(R.id.tv_date)
        // Will be set when this container is bound
        lateinit var day: CalendarDay

        init {
            view.setOnClickListener {
                // Use the CalendarDay associated with this container.
            }
        }
    }

}