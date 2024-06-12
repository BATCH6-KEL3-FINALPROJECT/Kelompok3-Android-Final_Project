package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.R
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.SectionPassengerCheckout
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDataOrder.CheckoutDataOrdersActivity
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.DataItem
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.HeaderItem
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.project.skypass.presentation.flight.result.FlightResultActivity
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class CheckoutDataPassengerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataPassengerBinding.inflate(layoutInflater) }

    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
        getArgumentData()
        setData()
    }
    private fun setClickListeners() {
//        onclick binding
    }
    private fun observeResult(){
//        observe view model
    }

    private fun setData() {
        binding.rvPassengerData.apply {
            layoutManager = LinearLayoutManager(this@CheckoutDataPassengerActivity)
            adapter = this@CheckoutDataPassengerActivity.adapter
        }

        val section = getListData().map {
            val section = Section()
            section.setHeader(HeaderItem(it.name) { data ->
                Toast.makeText(this, "Header Clicked : $data", Toast.LENGTH_SHORT).show()
            })
            val dataSection = it.data.map { data ->
                DataItem(data) { data ->
                    Toast.makeText(this, "Item Clicked : ${data[1]}", Toast.LENGTH_SHORT).show()
                }
            }
            section.addAll(dataSection)
            section
        }
        adapter.addAll(section)
    }

    private fun getListData(): List<SectionPassengerCheckout> = listOf(
        SectionPassengerCheckout("Data Passenger (name 1)", listOf("Nama orang 1")),
        SectionPassengerCheckout("Data Passenger (name 2)", listOf("Nama Orang 2")),
    )


    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            sendOrderData(it)
        }
    }
    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            CheckoutSeatActivity.sendDataOrder(
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
            val intent = Intent(context, CheckoutDataPassengerActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }
}