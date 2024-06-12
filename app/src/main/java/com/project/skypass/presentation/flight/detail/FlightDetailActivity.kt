package com.project.skypass.presentation.flight.detail

import android.annotation.SuppressLint
import android.content.Context
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
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityFlightDetailBinding
import com.project.skypass.presentation.flight.detail.adapter.FlightDetailAdapter
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.presentation.flight.filter.FilterFragment
import com.project.skypass.presentation.flight.result.FlightResultActivity
import com.project.skypass.utils.convertDateFormat
import com.project.skypass.utils.convertFlightDetail
import com.project.skypass.utils.convertMinutesToHours
import com.project.skypass.utils.orderDate
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
        getArgumentData()
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
        flightDetailAdapter.setOnTicketClickListener {

        }
    }

    private fun onItemClick(flight: Flight) {

        intent.putExtra("EXTRAS", flight)
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            sendOrderData(it,flight)
        }
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
        fun startActivity(
            context: Context,
            orderData: OrderUser,
        ) {
            val intent = Intent(context, FlightDetailActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderData)
            context.startActivity((intent))
        }
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            flightDetailViewModel.getHomeData(it)
            setProfileData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setProfileData(item: OrderUser){
        binding.layoutHeader.tvTypeFlight.text = item.seatClass
        binding.layoutHeader.tvPassenger.text = item.passengersTotal
        binding.layoutHeader.tvDestination.text = "${item.departureCity} > ${item.arrivalCity}"

    }



    private fun sendOrderData(item: OrderUser, itemFlight: Flight) {
        FlightResultActivity.sendDataOrder(
            this,
            OrderUser(
                // Home data
                id = item.id,
                arrivalCity = item.arrivalCity,
                arrivalDate = item.arrivalDate,
                seatClass = item.seatClass,
                departureCity = item.departureCity,
                departureDate = item.departureDate,
                passengersTotal = item.passengersTotal,
                passengersAdult = item.passengersAdult,
                passengersBaby = item.passengersBaby,
                passengersChild = item.passengersChild,
                isRoundTrip = item.isRoundTrip,
                supportRoundTrip = item.supportRoundTrip,
                orderDate = item.orderDate,

                // Flight data (One Way)
                airlineCode = itemFlight.airlineCode,
                airlineName = itemFlight.airlineName,
                arrivalAirportName = itemFlight.arrivalAirportName,
                arrivalIATACode = itemFlight.arrivalIATACode,
                arrivalTime = itemFlight.arrivalTime,
                departureAirportName = itemFlight.departureAirportName,
                departureIATACode = itemFlight.departureIATACode,
                departureTime = itemFlight.departureTime,
                flightCode = itemFlight.flightCode,
                flightDescription = itemFlight.flightDescription,
                flightDuration = itemFlight.flightDuration,
                flightDurationFormat =  itemFlight.flightDuration?.let { duration -> val (hours, remainingMinutes) = convertMinutesToHours(duration)}.toString(),
                flightId = itemFlight.flightId,
                flightStatus = itemFlight.flightStatus,
                flightSeat = itemFlight.seatClass,
                flightArrivalDate = itemFlight.arrivalDate,
                flightDepartureDate = itemFlight.departureDate,
                planeType = itemFlight.planeType,
                priceAdult = item.priceAdult, // add edit
                priceBaby = item.priceBaby, // add edit
                priceChild = item.priceChild,// add edit
                priceTotal = itemFlight.price,
                paymentPrice = item.paymentPrice,// add edit
                seatsAvailable = itemFlight.seatsAvailable,
                terminal = itemFlight.terminal,

                // Flight data (Round Trip)
                airlineCodeRoundTrip = item.airlineCodeRoundTrip,
                airlineNameRoundTrip = item.airlineNameRoundTrip,
                arrivalAirportNameRoundTrip = item.arrivalAirportNameRoundTrip,
                arrivalIATACodeRoundTrip = item.arrivalIATACodeRoundTrip,
                arrivalTimeRoundTrip = item.arrivalTimeRoundTrip,
                departureAirportNameRoundTrip = item.departureAirportNameRoundTrip,
                departureIATACodeRoundTrip = item.departureIATACodeRoundTrip,
                departureTimeRoundTrip = item.departureTimeRoundTrip,
                flightCodeRoundTrip = item.flightCodeRoundTrip,
                flightDescriptionRoundTrip = item.flightDescriptionRoundTrip,
                flightDurationRoundTrip = item.flightDurationRoundTrip,
                flightDurationFormatRoundTrip = item.flightDurationFormatRoundTrip,
                flightIdRoundTrip = item.flightIdRoundTrip,
                flightStatusRoundTrip = item.flightStatusRoundTrip,
                flightSeatRoundTrip = item.flightSeatRoundTrip,
                flightArrivalDateRoundTrip = item.flightArrivalDateRoundTrip,
                flightDepartureDateRoundTrip = item.flightDepartureDateRoundTrip,
                planeTypeRoundTrip = item.planeTypeRoundTrip,
                priceAdultRoundTrip = item.priceAdultRoundTrip,
                priceBabyRoundTrip = item.priceBabyRoundTrip,
                priceChildRoundTrip = item.priceChildRoundTrip,
                priceTotalRoundTrip = item.priceTotalRoundTrip,
                paymentPriceRoundTrip = item.paymentPriceRoundTrip,
                seatsAvailableRoundTrip = item.seatsAvailableRoundTrip,
                terminalRoundTrip = item.terminalRoundTrip
            )
        )
    }



}