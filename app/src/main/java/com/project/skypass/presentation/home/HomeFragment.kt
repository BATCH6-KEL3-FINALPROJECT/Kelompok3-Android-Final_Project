package com.project.skypass.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.skypass.R
import com.project.skypass.data.model.DateCalendar
import com.project.skypass.data.model.Destination
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SeatClass
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.presentation.calendar.CalendarFragment
import com.project.skypass.presentation.customview.DataSelection
import com.project.skypass.presentation.flight.detail.FlightDetailActivity
import com.project.skypass.presentation.home.adapter.FavoriteDestinationAdapter
import com.project.skypass.presentation.home.flightclass.FlightClassFragment
import com.project.skypass.presentation.home.passengers.PassengersFragment
import com.project.skypass.presentation.home.search.SearchFragment
import com.project.skypass.utils.convertDateFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), DataSelection {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val favoriteDestinationAdapter: FavoriteDestinationAdapter by lazy {
        FavoriteDestinationAdapter {

        }
    }

    private var formatDateDepartureIntent: String? = null
    private var formatDateReturnIntent: String? = null
    private var passengers: String? = null

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
        sendData()
        bindSeatClass(viewModel.getFavoriteDestination())
    }

    private fun bindSeatClass(favDestination: List<Destination>) {
        binding.rvFavoriteDestination.apply {
            adapter = favoriteDestinationAdapter
        }
        favoriteDestinationAdapter.submitData(favDestination)
    }

    private fun sendData() {
        binding.btnSearchFlight.setOnClickListener {
            moveToFlight()
        }
    }

    private fun moveToFlight() {
        FlightDetailActivity.startActivity(
            requireContext(),
            OrderUser(
                id = null,
                airlineCode = "",
                airlineName = "",
                arrivalAirportName = "",
                arrivalCity = binding.etToTrip.text.toString(),
                arrivalDate = convertDateFormat(binding.etReturn.text.toString()),
                arrivalIATACode = "",
                arrivalTime = "",
                departureAirportName = "",
                departureCity = binding.etFromTrip.text.toString(),
                departureDate = convertDateFormat(binding.etDeparture.text.toString()),
                departureIATACode = "",
                departureTime = "",
                flightCode = "",
                flightDescription = "",
                flightDuration = null,
                flightId = "",
                flightStatus = "",
                flightSeat = "",
                planeType = "",
                priceAdult = null,
                priceBaby = null,
                priceChild = null,
                priceTotal = null,
                seatClass = binding.etSeatClass.text.toString(),
                seatsAvailable = null,
                terminal = "",
                orderDate = "",
                passengersTotal = binding.etPassengers.text.toString(),
                passengersAdult = null,
                passengersBaby = null,
                passengersChild = null,
            ),
        )
    }

    private fun clickListener() {
        binding.etPassengers.setOnClickListener {
            val passengerFragment = PassengersFragment()
            passengerFragment.passengersSelection = this@HomeFragment
            passengerFragment.show(childFragmentManager, "passengers")
        }
        binding.etFromTrip.setOnClickListener {
            val fromTripFragment = SearchFragment()
            fromTripFragment.tripSelection = this@HomeFragment
            fromTripFragment.show(childFragmentManager, "fromTrip")
        }
        binding.etToTrip.setOnClickListener {
            val toTripFragment = SearchFragment()
            toTripFragment.tripSelection = this@HomeFragment
            toTripFragment.show(childFragmentManager, "toTrip")
        }
        binding.etSeatClass.setOnClickListener {
            val seatClassFragment = FlightClassFragment()
            seatClassFragment.seatClassSelection = this@HomeFragment
            seatClassFragment.show(childFragmentManager, "flightClass")
        }
        binding.etDeparture.setOnClickListener {
            val calendarFragment = CalendarFragment()
            val bundle = Bundle()
            bundle.putString(
                "currentDateDeparture",
                binding.etDeparture.text.toString().ifEmpty { "Belum dipilih" })
            bundle.putString(
                "currentDateReturn",
                binding.etReturn.text.toString().ifEmpty { "Belum dipilih" })
            calendarFragment.arguments = bundle
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "departure")
        }
        binding.etReturn.setOnClickListener {
            val calendarFragment = CalendarFragment()
            val bundle = Bundle()
            bundle.putString(
                "currentDateDeparture",
                binding.etDeparture.text.toString().ifEmpty { "Belum dipilih" })
            bundle.putString(
                "currentDateReturn",
                binding.etReturn.text.toString().ifEmpty { "Belum dipilih" })
            calendarFragment.arguments = bundle
            calendarFragment.dateSelection = this@HomeFragment
            calendarFragment.show(parentFragmentManager, "return")
        }
        binding.ivSwitchTrip.setOnClickListener {
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

    override fun onDateSelected(tag: String, date: DateCalendar) {
        when (tag) {
            "departure" -> {
                binding.etDeparture.setText(date.ddMMMyyyy)
                formatDateDepartureIntent = date.ddMMyyyy
            }

            "return" -> {
                binding.etReturn.setText(date.ddMMMyyyy)
                formatDateReturnIntent = date.ddMMyyyy
            }
        }
    }

    override fun onPassengerSelected(tag: String, passenger: String) {
        when (tag) {
            "passengers" -> {
                binding.etPassengers.setText(getString(R.string.passengers_qyt_value, passenger))
                passengers = passenger
            }
        }
    }

    override fun onSeatClassSelected(tag: String, seatClass: SeatClass) {
        when (tag) {
            "flightClass" -> {
                binding.etSeatClass.setText(seatClass.classType)
            }
        }
    }

    override fun onTripSelected(tag: String, trip: Search) {
        when (tag) {
            "fromTrip" -> {
                binding.etFromTrip.setText(trip.city)
            }

            "toTrip" -> {
                binding.etToTrip.setText(trip.city)
            }
        }
    }


}