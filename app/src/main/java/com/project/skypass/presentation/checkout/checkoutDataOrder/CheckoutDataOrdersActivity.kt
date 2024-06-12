package com.project.skypass.presentation.checkout.checkoutDataOrder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.data.model.Flight
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDataOrdersBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.flight.result.FlightResultActivity

class CheckoutDataOrdersActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataOrdersBinding.inflate(layoutInflater) }
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
        }
    }
        private fun sendOrderData(item: OrderUser) {
            binding.btnSubmit.setOnClickListener {
                CheckoutDataPassengerActivity.sendDataOrder(
                    this,
                    OrderUser(
                        // Change arrival into departure
                        id = item.id,
                        arrivalCity = item.departureCity,
                        arrivalDate = item.departureDate,
                        seatClass = item.seatClass,
                        departureCity = item.arrivalCity,
                        departureDate = item.arrivalDate,
                        passengersTotal = item.passengersTotal,
                        passengersAdult = item.passengersAdult,
                        passengersBaby = item.passengersBaby,
                        passengersChild = item.passengersChild,
                        isRoundTrip = item.isRoundTrip,
                        supportRoundTrip = item.supportRoundTrip,
                        orderDate = item.orderDate,



                        // Convert from arrival into departure
                        airlineCode = item.airlineCodeRoundTrip,
                        airlineName = item.airlineNameRoundTrip,
                        arrivalAirportName = item.arrivalAirportNameRoundTrip,
                        arrivalIATACode= item.arrivalIATACodeRoundTrip,
                        arrivalTime = item.arrivalTimeRoundTrip,
                        departureAirportName = item.departureAirportNameRoundTrip,
                        departureIATACode = item.departureIATACodeRoundTrip,
                        departureTime = item.departureTimeRoundTrip,
                        flightCode = item.flightCodeRoundTrip,
                        flightDescription = item.flightDescriptionRoundTrip,
                        flightDuration= item.flightDurationRoundTrip,
                        flightDurationFormat = item.flightDurationFormatRoundTrip,
                        flightId = item.flightIdRoundTrip,
                        flightStatus = item.flightStatusRoundTrip,
                        flightSeat = item.flightSeatRoundTrip,
                        flightDepartureDate = item.flightDepartureDateRoundTrip,
                        flightArrivalDate = item.flightArrivalDateRoundTrip,
                        planeType = item.planeTypeRoundTrip,
                        priceAdult = item.priceAdultRoundTrip,
                        priceBaby = item.priceBabyRoundTrip,
                        priceChild = item.priceChildRoundTrip,
                        priceTotal = item.priceTotalRoundTrip,
                        paymentPrice = item.paymentPriceRoundTrip,
                        seatsAvailable = item.seatsAvailableRoundTrip,
                        terminal = item.terminalRoundTrip,

                        // Convert from arrival into departure
                        airlineCodeRoundTrip = item.airlineCode,
                        airlineNameRoundTrip = item.airlineName,
                        arrivalAirportNameRoundTrip = item.arrivalAirportName,
                        arrivalIATACodeRoundTrip = item.arrivalIATACode,
                        arrivalTimeRoundTrip = item.arrivalTime,
                        departureAirportNameRoundTrip = item.departureAirportName,
                        departureIATACodeRoundTrip = item.departureIATACode,
                        departureTimeRoundTrip = item.departureTime,
                        flightCodeRoundTrip = item.flightCode,
                        flightDescriptionRoundTrip = item.flightDescription,
                        flightDurationRoundTrip = item.flightDuration,
                        flightDurationFormatRoundTrip = item.flightDurationFormat,
                        flightIdRoundTrip = item.flightId,
                        flightStatusRoundTrip = item.flightStatus,
                        flightSeatRoundTrip = item.flightSeat,
                        flightDepartureDateRoundTrip = item.flightDepartureDate,
                        flightArrivalDateRoundTrip = item.flightArrivalDate,
                        planeTypeRoundTrip = item.planeType,
                        priceAdultRoundTrip = item.priceAdult,
                        priceBabyRoundTrip = item.priceBaby,
                        priceChildRoundTrip = item.priceChild,
                        priceTotalRoundTrip = item.priceTotal,
                        paymentPriceRoundTrip = item.paymentPrice,
                        seatsAvailableRoundTrip = item.seatsAvailable,
                        terminalRoundTrip = item.terminal
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
            val intent = Intent(context, CheckoutDataOrdersActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }
}