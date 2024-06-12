package com.project.skypass.presentation.checkout.checkoutDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.project.skypass.presentation.flight.result.FlightResultActivity
import com.project.skypass.presentation.home.HomeFragment
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.toIndonesianFormat

class CheckoutDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setClickListeners()
    }
    private fun setClickListeners() {
//        onclick binding
    }
    private fun observeResult(){
//        observe view model
    }
    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            sendOrderData(it)
            setProfileData(it)
        }
    }
    private fun setProfileData(item: OrderUser) {
        binding.apply {
            binding.tvTotalPrice.text = item.priceTotal.toIndonesianFormat()
            binding.rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
            binding.rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
            binding.rvTicketDetail.tvAirline.text = item.airlineName
            binding.rvTicketDetail.tvCityDeparture.text = item.departureCity
            binding.rvTicketDetail.tvCityArrival.text = item.arrivalCity
            binding.rvTicketDetail.tvDateDeparture.text = item.flightDepartureDate
            binding.rvTicketDetail.tvDateArrival.text = item.flightArrivalDate
            binding.rvTicketDetail.tvTimeDeparture.text = item.departureTime
            binding.rvTicketDetail.tvTimeArrival.text = item.arrivalTime
            binding.rvTicketDetail.tvInfoDetail.text = item.flightDescription
            binding.rvTicketDetail.tvFlightCode.text = item.flightCode


            if (item.isRoundTrip == false && item.supportRoundTrip == true) {
                binding.tvTotalPrice.text = (item.priceTotal?.plus(item.priceTotalRoundTrip!!)).toIndonesianFormat()
                // change departure to arrival
                binding.rvTicketDetail.tvAirportDeparture.text = item.departureAirportNameRoundTrip
                binding.rvTicketDetail.tvAirportArrival.text = item.arrivalAirportNameRoundTrip
                binding.rvTicketDetail.tvAirline.text = item.airlineNameRoundTrip
                binding.rvTicketDetail.tvCityDeparture.text = item.arrivalCity
                binding.rvTicketDetail.tvCityArrival.text = item.departureCity
                binding.rvTicketDetail.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                binding.rvTicketDetail.tvDateArrival.text = item.flightArrivalDateRoundTrip
                binding.rvTicketDetail.tvTimeDeparture.text = item.departureTimeRoundTrip
                binding.rvTicketDetail.tvTimeArrival.text = item.arrivalTimeRoundTrip
                binding.rvTicketDetail.tvInfoDetail.text = item.flightDescriptionRoundTrip
                binding.rvTicketDetail.tvFlightCode.text = item.flightCodeRoundTrip

                binding.rvTicketDetailRound.tvAirportDeparture.text = item.departureAirportName
                binding.rvTicketDetailRound.tvAirportArrival.text = item.arrivalAirportName
                binding.rvTicketDetailRound.tvAirline.text = item.airlineName
                binding.rvTicketDetailRound.tvCityDeparture.text = item.departureCity
                binding.rvTicketDetailRound.tvCityArrival.text = item.arrivalCity
                binding.rvTicketDetailRound.tvDateDeparture.text = item.flightDepartureDate
                binding.rvTicketDetailRound.tvDateArrival.text = item.flightArrivalDate
                binding.rvTicketDetailRound.tvTimeDeparture.text = item.departureTime
                binding.rvTicketDetailRound.tvTimeArrival.text = item.arrivalTime
                binding.rvTicketDetailRound.tvInfoDetail.text = item.flightDescription
                binding.rvTicketDetailRound.tvFlightCode.text = item.flightCode
                binding.rvTicketDetailRound.root.isVisible = true
            }else{
                binding.rvTicketDetailRound.root.isVisible = false
            }
        }
    }
    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            MainActivity.sendDataOrder(
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
                    airlineCode = item.airlineCode,
                    airlineName = item.airlineName,
                    arrivalAirportName = item.arrivalAirportName,
                    arrivalIATACode = item.arrivalIATACode,
                    arrivalTime = item.arrivalTime,
                    departureAirportName = item.departureAirportName,
                    departureIATACode = item.departureIATACode,
                    departureTime = item.departureTime,
                    flightCode = item.flightCode,
                    flightDescription = item.flightDescription,
                    flightDuration = item.flightDuration,
                    flightDurationFormat = item.flightDurationFormat,
                    flightId = item.flightId,
                    flightStatus = item.flightStatus,
                    flightSeat = item.seatClass,
                    flightArrivalDate = item.flightArrivalDate,
                    flightDepartureDate = item.flightDepartureDate,
                    planeType = item.planeType,
                    priceAdult = item.priceAdult,
                    priceBaby = item.priceBaby,
                    priceChild = item.priceChild,
                    priceTotal = item.priceTotal,
                    paymentPrice = item.paymentPrice,
                    seatsAvailable = item.seatsAvailable,
                    terminal = item.terminal,

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
                ),
            )
        }
    }
    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser
        ){
            val intent = Intent(context, CheckoutDetailActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }
}