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
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
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
            val intent = Intent(context, CheckoutDataPassengerActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }
}