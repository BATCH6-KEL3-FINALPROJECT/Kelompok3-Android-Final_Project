package com.project.skypass.presentation.flight.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.project.skypass.R
import com.project.skypass.data.model.Flight
import com.project.skypass.databinding.ActivityFlightDetailBinding
import com.project.skypass.presentation.flight.detail.adapter.FlightDetailAdapter
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.presentation.flight.filter.FilterFragment
import com.project.skypass.presentation.flight.result.FlightResultActivity
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class FlightDetailActivity : AppCompatActivity() {
    private val binding: ActivityFlightDetailBinding by lazy {
        ActivityFlightDetailBinding.inflate(layoutInflater)
    }
    private val flightDetailViewModel: FlightDetailViewModel by viewModel()
    private lateinit var flightDetailAdapter: FlightDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupCalendarView()
        setClickListeners()
        observeFlightData()
    }

    private fun setClickListeners() {
        binding.layoutHeader.ivBackFlight.setOnClickListener {
            onBackPressed()
        }
        binding.cardFilter.setOnClickListener {
            val filterFragment = FilterFragment()
            filterFragment.show(supportFragmentManager, filterFragment.tag)
        }
        flightDetailAdapter.setOnTicketClickListener { flight ->
            onItemClick(flight)
        }
    }

    private fun onItemClick(flight: Flight) {
        val intent = Intent(this, FlightResultActivity::class.java)
        intent.putExtra("EXTRAS", flight)
        startActivity(intent)
    }

    private fun setupAdapter() {
        val itemClickListener = object : OnItemClickedListener<Flight> {
            override fun onItemClicked(item: Flight) {
                onItemClick(item)
            }
        }

        flightDetailAdapter = FlightDetailAdapter(itemClickListener)
        binding.rvTicket.adapter = flightDetailAdapter
    }

    private fun setupCalendarView() {
        val today = LocalDate.now()
        val endOfWeek = today.plusDays(6)
//        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        binding.cvCalender.setup(
            YearMonth.from(today),
            YearMonth.from(today),
            DayOfWeek.from(today)
        )
        binding.cvCalender.scrollToDate(today)

        class DayViewContainer(view: View) : ViewContainer(view) {
            val textView: TextView = view.findViewById(R.id.tv_day)
            val weekTextView: TextView = view.findViewById(R.id.tv_week)
        }

        binding.cvCalender.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                if (day.date in today..endOfWeek) {
                    container.textView.text =
                        day.date.dayOfMonth.toString() // format(dateFormatter)
                    container.weekTextView.text = day.date.dayOfWeek.getDisplayName(
                        java.time.format.TextStyle.SHORT,
                        java.util.Locale.getDefault()
                    )

                    container.textView.visibility = View.VISIBLE
                    container.weekTextView.visibility = View.VISIBLE
                } else {
                    container.textView.visibility = View.INVISIBLE
                    container.weekTextView.visibility = View.GONE
                }
            }
        }
    }

    private fun observeFlightData() {
        flightDetailViewModel.getFlightDetail().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvTicket.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.ivEmptyTicket.isVisible = false
                    binding.tvEmptyTicket.isVisible = false
                    binding.tvEmptyTicketSub.isVisible = false
                    binding.btnEditSearch.isVisible = false
                    it.payload?.let { data ->
                        flightDetailAdapter.submitData(data)
                    }
                },
                doOnLoading = {
                    binding.rvTicket.isVisible = false
                    binding.pbLoading.isVisible = true
                    binding.ivEmptyTicket.isVisible = false
                    binding.tvEmptyTicket.isVisible = false
                    binding.tvEmptyTicketSub.isVisible = false
                    binding.btnEditSearch.isVisible = false
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.ivEmptyTicket.isVisible = true
                    binding.tvEmptyTicket.isVisible = true
                    binding.tvEmptyTicketSub.isVisible = true
                    binding.btnEditSearch.isVisible = true
                },
            )
        }
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
    }
}