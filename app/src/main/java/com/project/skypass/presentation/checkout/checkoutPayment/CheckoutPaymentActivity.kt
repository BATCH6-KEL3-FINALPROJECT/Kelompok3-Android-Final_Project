package com.project.skypass.presentation.checkout.checkoutPayment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.CheckoutPayment
import com.project.skypass.data.model.Flight
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.databinding.ActivityCheckoutPaymentBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity.Companion
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.CheckoutPaymentAdapter
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.OnItemClickedPaymentListener
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.project.skypass.presentation.flight.detail.adapter.FlightDetailAdapter
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.presentation.home.HomeFragment
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.convertMinutesToHours
import com.project.skypass.utils.toIndonesianFormat

class CheckoutPaymentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutPaymentBinding.inflate(layoutInflater) }
    private lateinit var paymentAdapter: CheckoutPaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        getArgumentData()
        setOnClick()
    }

    private fun setOnClick(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupAdapter(){
        val itemClickListener = object : OnItemClickedPaymentListener<PassengersData> {
            override fun onItemClicked(item: PassengersData) {

            }
        }

        paymentAdapter = CheckoutPaymentAdapter(itemClickListener)
        binding.rvPassenger.adapter = paymentAdapter
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {

            intent.extras?.getParcelable<CheckoutPayment>(EXTRA_USER_ORDER)
                ?.let { orderPassenger ->
                    setProfileData(it, orderPassenger)
                    sendOrderData(it, orderPassenger)
                    orderPassenger.passengersData?.let { it1 -> paymentAdapter.submitData(it1) }
                }
        }
    }
    private fun setProfileData(item: OrderUser, passengerData: CheckoutPayment){

        binding.apply {
            inTicket.tvClass.text = item.seatClass
            inTicket.tvTotalPrice.text = item.paymentPrice.toIndonesianFormat()
            inTicket.tvDateArrival.text = item.flightArrivalDate
            inTicket.tvTimeArrival.text = item.arrivalTime
            inTicket.tvCityNameDestinationAlias.text = item.arrivalIATACode
            inTicket.tvCityNameDestination.text = item.arrivalCity
            inTicket.tvDateDeparture.text = item.flightDepartureDate
            inTicket.tvTimeDeparture.text = item.departureTime
            inTicket.tvCityNameAlias.text = item.departureIATACode
            inTicket.tvCityName.text = item.departureCity
            inTicket.tvPassangersList.text = "${item.passengersAdult} Adult, ${item.passengersChild} Child, ${item.passengersBaby} Baby"

            item.flightDuration?.let { duration ->
                val (hours, remainingMinutes) = convertMinutesToHours(duration)
                val durationText = if (hours > 0) {
                    "$hours Jam $remainingMinutes Menit"
                } else {
                    "$remainingMinutes Menit"
                }
                inTicket.tvLengthOfJourney.text = durationText
            } ?: run {
                inTicket.tvLengthOfJourney.text  = "N/A"
            }

            if (item.seatsAvailableRoundTrip != null){
                inTicketRoundTrip.root.isVisible = true
                inTicketRoundTrip.tvClass.text = item.seatClass
                inTicketRoundTrip.tvTotalPrice.text = item.paymentPrice.toIndonesianFormat()
                inTicketRoundTrip.tvDateArrival.text = item.flightArrivalDateRoundTrip
                inTicketRoundTrip.tvTimeArrival.text = item.arrivalTimeRoundTrip
                inTicketRoundTrip.tvLengthOfJourney.text = item.flightDurationFormatRoundTrip
                inTicketRoundTrip.tvCityNameDestinationAlias.text = item.arrivalIATACodeRoundTrip
                inTicketRoundTrip.tvCityNameDestination.text = item.departureCity
                inTicketRoundTrip.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                inTicketRoundTrip.tvTimeDeparture.text = item.departureTimeRoundTrip
                inTicketRoundTrip.tvCityNameAlias.text = item.departureIATACodeRoundTrip
                inTicketRoundTrip.tvCityName.text = item.arrivalCity
                inTicketRoundTrip.tvPassangersList.text = "${item.passengersAdult} Adult, ${item.passengersChild} Child, ${item.passengersBaby} Baby"

                item.flightDurationRoundTrip?.let { duration ->
                    val (hours, remainingMinutes) = convertMinutesToHours(duration)
                    val durationText = if (hours > 0) {
                        "$hours Jam $remainingMinutes Menit"
                    } else {
                        "$remainingMinutes Menit"
                    }
                    inTicketRoundTrip.tvLengthOfJourney.text = durationText
                } ?: run {
                    inTicketRoundTrip.tvLengthOfJourney.text  = "N/A"
                }
            }else{
                inTicketRoundTrip.root.isVisible = false
            }
        }
    }

    private fun sendOrderData(item: OrderUser, passengerData: CheckoutPayment) {
        binding.btnSubmit.setOnClickListener {
            MainActivity.sendDataOrder(
                this,
                item
            )
        }
    }
    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
            orderPassenger: CheckoutPayment
        ) {
            val intent = Intent(context, CheckoutPaymentActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            intent.putExtra(EXTRA_USER_ORDER, orderPassenger)
            context.startActivity(intent)
        }
    }

}