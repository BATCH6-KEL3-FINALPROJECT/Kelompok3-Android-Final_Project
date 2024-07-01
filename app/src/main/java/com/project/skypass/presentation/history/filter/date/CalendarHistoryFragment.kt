package com.project.skypass.presentation.history.filter.date

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.project.skypass.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.project.skypass.databinding.FragmentCalendarHistoryBinding
import com.project.skypass.utils.displayText
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarHistoryFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCalendarHistoryBinding
    private val viewModel: CalendarHistoryViewModel by viewModel()
    private var isSelectingReturnDate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalendarHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        calendarView()
        clickListener()
    }

    private fun clickListener() {
        with(binding) {
            btnCalendar.setOnClickListener {
                if (viewModel.selectedDateDeparture.value != null && viewModel.selectedDateReturn.value != null) {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val departure = viewModel.selectedDateDeparture.value!!.format(formatter)
                    val retur = viewModel.selectedDateReturn.value!!.format(formatter)
                    targetFragment?.onActivityResult(
                        101, Activity.RESULT_OK, Intent().apply {
                            putExtra("selectedDateDeparture", departure)
                            putExtra("selectedDateReturn", retur)
                        }
                    )
                    dismiss()
                }
            }
        }
    }

    private fun calendarView() {
        binding.rvDate.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                container.textView.text = data.date.dayOfMonth.toString()

                if (data.position == DayPosition.MonthDate) {
                    container.textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.colorTextCalendar))
                    when (data.date) {
                        viewModel.selectedDateDeparture.value -> {
                            container.textView.setBackgroundResource(R.drawable.bg_button)
                            container.textView.setTextColor(Color.WHITE)
                        }
                        viewModel.selectedDateReturn.value -> {
                            container.textView.setBackgroundResource(R.drawable.bg_button)
                            container.textView.setTextColor(Color.WHITE)
                        }
                        else -> {
                            val departureDate = viewModel.selectedDateDeparture.value
                            val returnDate = viewModel.selectedDateReturn.value
                            if (departureDate != null && returnDate != null && data.date.isAfter(departureDate) && data.date.isBefore(returnDate)) {
                                container.textView.setBackgroundResource(R.drawable.bg_date_calendar_between)
                                container.textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.colorTextCalendar))
                            } else {
                                container.textView.background = null
                            }
                        }
                    }
                } else {
                    container.textView.setTextColor(Color.GRAY)
                    container.textView.background = null
                }

                container.textView.setOnClickListener {
                    val departureDate = viewModel.selectedDateDeparture.value
                    val returnDate = viewModel.selectedDateReturn.value

                    if (!isSelectingReturnDate) {
                        // Selecting departure date
                        viewModel.selectDepartureDate(data.date)
                        binding.tvDateDeparture.text = formatDate(data.date)
                        // Reset return date
                        viewModel.selectReturnDate(null)
                        binding.tvDateReturn.text = ""
                        isSelectingReturnDate = true
                    } else {
                        // Selecting return date
                        if (departureDate == null) {
                            viewModel.selectDepartureDate(data.date)
                            binding.tvDateDeparture.text = formatDate(data.date)
                            isSelectingReturnDate = true
                        } else if (data.date.isBefore(departureDate)) {
                            // Swap dates if return date is before departure date
                            viewModel.selectReturnDate(departureDate)
                            viewModel.selectDepartureDate(data.date)
                            binding.tvDateDeparture.text = formatDate(data.date)
                            binding.tvDateReturn.text = formatDate(departureDate)
                        } else {
                            viewModel.selectReturnDate(data.date)
                            binding.tvDateReturn.text = formatDate(data.date)
                        }
                        isSelectingReturnDate = false
                    }
                    binding.rvDate.notifyCalendarChanged()
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
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        val firstDayOfWeek = firstDayOfWeekFromLocale()
        binding.rvDate.setup(startMonth, endMonth, firstDayOfWeek)
        binding.rvDate.scrollToMonth(currentMonth)
        binding.rvDate.monthScrollListener = { month ->
            val formatter = DateTimeFormatter.ofPattern(getString(R.string.format_month_year))
            binding.tvMonthCalendar.text = month.yearMonth.format(formatter)
        }
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        return date.format(formatter)
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView: TextView = view.findViewById(R.id.tv_date)
        lateinit var day: CalendarDay

        init {
            view.setOnClickListener {  }
        }
    }
}