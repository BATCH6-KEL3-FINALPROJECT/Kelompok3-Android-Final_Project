package com.project.skypass.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.skypass.R
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.presentation.calendar.CalendarFragment
import com.project.skypass.presentation.customview.DateSelection
import com.project.skypass.presentation.home.flightclass.FlightClassFragment
import com.project.skypass.presentation.home.passengers.PassengersFragment
import com.project.skypass.presentation.home.search.SearchFragment

class HomeFragment : Fragment(), DateSelection {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListener()
        //setValue()
    }

    private fun setValue() {
        childFragmentManager.setFragmentResultListener("requestDateDeparture", this) { _, bundle ->
            val result = bundle.getString("selectedDateDeparture")
            binding.etDeparture.setText(result)
        }
    }

    private fun clickListener() {
        binding.etPassengers.setOnClickListener {
            val passengerFragment = PassengersFragment()
            passengerFragment.show(childFragmentManager, passengerFragment.tag)
        }
        binding.etFromTrip.setOnClickListener {
            val fromTripFragment = SearchFragment()
            fromTripFragment.show(childFragmentManager, fromTripFragment.tag)
        }
        binding.etToTrip.setOnClickListener {
            val toTripFragment = SearchFragment()
            toTripFragment.show(childFragmentManager, toTripFragment.tag)
        }
        binding.etSeatClass.setOnClickListener {
            val seatClassFragment = FlightClassFragment()
            seatClassFragment.show(childFragmentManager, seatClassFragment.tag)
        }
        binding.etDeparture.setOnClickListener {
            val calendarFragment = CalendarFragment()
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "departure")
        }
        binding.etReturn.setOnClickListener {
            val calendarFragment = CalendarFragment()
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "return")
        }
        binding.ivSwitchTrip.setOnClickListener{
            switchFromTo()
        }

        tripChecked()
    }

    private fun switchFromTo() {
        val fromTrip = binding.etFromTrip.text.toString()
        val toTrip = binding.etToTrip.text.toString()

        binding.etFromTrip.setText(toTrip)
        binding.etToTrip.setText(fromTrip)
    }

    private fun tripChecked() {
        binding.layoutReturn.visibility = View.GONE

        binding.rgTrip.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_one_way -> {
                    binding.layoutReturn.visibility = View.GONE
                }
                R.id.rb_round_trip -> {
                    binding.layoutReturn.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDateSelected(tag: String, date: String) {
        when (tag) {
            "departure" -> binding.etDeparture.setText(date)
            "return" -> binding.etReturn.setText(date)
        }
    }

}