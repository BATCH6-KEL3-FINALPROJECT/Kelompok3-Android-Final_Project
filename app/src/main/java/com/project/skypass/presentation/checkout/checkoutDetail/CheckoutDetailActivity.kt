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
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
    private fun observeResult(){
//        observe view model
    }
    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            setProfileData(it)
            sendOrderData(it)

        }
    }
    private fun setProfileData(item: OrderUser) {
        binding.apply {
            if (item.isRoundTrip == false && item.supportRoundTrip == true) {
                tvTotalPrice.text = (item.priceTotal?.plus(item.priceTotalRoundTrip!!)).toIndonesianFormat()
                // change departure to arrival
                rvTicketDetail.tvAirportDeparture.text = item.departureAirportNameRoundTrip
                rvTicketDetail.tvAirportArrival.text = item.arrivalAirportNameRoundTrip
                rvTicketDetail.tvAirline.text = item.airlineNameRoundTrip
                rvTicketDetail.tvCityDeparture.text = item.arrivalCity
                rvTicketDetail.tvCityArrival.text = item.departureCity
                rvTicketDetail.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                rvTicketDetail.tvDateArrival.text = item.flightArrivalDateRoundTrip
                rvTicketDetail.tvTimeDeparture.text = item.departureTimeRoundTrip
                rvTicketDetail.tvTimeArrival.text = item.arrivalTimeRoundTrip
                rvTicketDetail.tvInfoDetail.text = item.flightDescriptionRoundTrip
                rvTicketDetail.tvFlightCode.text = item.flightCodeRoundTrip

                rvTicketDetailRound.tvAirportDeparture.text = item.departureAirportName
                rvTicketDetailRound.tvAirportArrival.text = item.arrivalAirportName
                rvTicketDetailRound.tvAirline.text = item.airlineName
                rvTicketDetailRound.tvCityDeparture.text = item.departureCity
                rvTicketDetailRound.tvCityArrival.text = item.arrivalCity
                rvTicketDetailRound.tvDateDeparture.text = item.flightDepartureDate
                rvTicketDetailRound.tvDateArrival.text = item.flightArrivalDate
                rvTicketDetailRound.tvTimeDeparture.text = item.departureTime
                rvTicketDetailRound.tvTimeArrival.text = item.arrivalTime
                rvTicketDetailRound.tvInfoDetail.text = item.flightDescription
                rvTicketDetailRound.tvFlightCode.text = item.flightCode
                rvTicketDetailRound.root.isVisible = true
            }else{
                rvTicketDetailRound.root.isVisible = false
                tvTotalPrice.text = item.priceTotal.toIndonesianFormat()
                rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
                rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
                rvTicketDetail.tvAirline.text = item.airlineName
                rvTicketDetail.tvCityDeparture.text = item.departureCity
                rvTicketDetail.tvCityArrival.text = item.arrivalCity
                rvTicketDetail.tvDateDeparture.text = item.flightDepartureDate
                rvTicketDetail.tvDateArrival.text = item.flightArrivalDate
                rvTicketDetail.tvTimeDeparture.text = item.departureTime
                rvTicketDetail.tvTimeArrival.text = item.arrivalTime
                rvTicketDetail.tvInfoDetail.text = item.flightDescription
                rvTicketDetail.tvFlightCode.text = item.flightCode
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