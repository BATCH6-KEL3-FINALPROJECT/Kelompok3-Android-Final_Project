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
                        id = null,
                        airlineCode = "",
                        airlineName = "",
                        arrivalAirportName = "",
                        arrivalCity = item.arrivalCity,
                        arrivalDate = item.arrivalDate,
                        arrivalIATACode = "",
                        arrivalTime = "",
                        departureAirportName = "",
                        departureCity = item.departureCity,
                        departureDate = item.departureDate,
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
                        seatClass = item.seatClass,
                        seatsAvailable = null,
                        terminal = "",
                        orderDate = "",
                        passengersTotal = item.passengersTotal,
                        passengersAdult = null,
                        passengersBaby = null,
                        passengersChild = null,
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