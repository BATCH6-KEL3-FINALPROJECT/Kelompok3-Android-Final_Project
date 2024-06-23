package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.model.SectionPassengerCheckout
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.DataItem
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.HeaderItem
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class CheckoutDataPassengerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataPassengerBinding.inflate(layoutInflater) }
    private var dataPassengersOrder = mutableListOf<PassengersData>()
    private var getPassengersAdult = Int ?: 0
    private var getPassengersChild = Int ?: 0
    private var getPassengersBaby = Int ?: 0

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

    private fun observeResult() {
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
            val dataSection = it.data.map {
                DataItem(it!!) { passenger ->
                    dataPassengersOrder.add(passenger)
                    Toast.makeText(this, "Item Clicked : $dataPassengersOrder", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            section.addAll(dataSection)
            section
        }
        adapter.addAll(section)
    }

    private fun getListData(): List<SectionPassengerCheckout> {
        val passenger = DataHolder.passengersDataData

        val result = mutableListOf<SectionPassengerCheckout>()
        for (i in 1..getPassengersAdult as Int) {
            result.add(
                SectionPassengerCheckout("Data Passenger Adult $i", listOf(passenger)),
            )
        }
        for (i in 1..getPassengersChild as Int) {
            result.add(
                SectionPassengerCheckout("Data Passenger Child $i", listOf(passenger)),
            )
        }
        for (i in 1..getPassengersBaby as Int) {
            result.add(
                SectionPassengerCheckout("Data Passenger Baby $i", listOf(passenger)),
            )
        }
        return result
    }


    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {
            intent.extras?.getParcelable<OrderPassengers>(EXTRA_USER_ORDER)?.let { orderPassenger ->
                sendOrderData(it, orderPassenger)
                getDataPassenger(it, orderPassenger)
            }
        }
    }

    private fun sendOrderData(item: OrderUser, passengerData: OrderPassengers) {
        binding.btnSubmit.setOnClickListener {
            CheckoutSeatActivity.sendDataOrder(
                this,
                item,
                OrderPassengers(
                    name = passengerData.name,
                    email = passengerData.email,
                    familyName = passengerData.familyName,
                    noTelephone = passengerData.noTelephone,
                    passengers = dataPassengersOrder,
                    seatOrderDeparture = passengerData.seatOrderDeparture,
                    seatOrderArrival = passengerData.seatOrderArrival,
                    seatIdArrival = passengerData.seatIdArrival,
                    seatIdDeparture = passengerData.seatIdDeparture
                )
            )
        }
    }

    private fun getDataPassenger(item: OrderUser, passengerData: OrderPassengers) {
        getPassengersAdult = item.passengersAdult!!
        getPassengersChild = item.passengersChild!!
        getPassengersBaby = item.passengersBaby!!
        DataHolder.emailOrder = passengerData.email.toString()
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
            orderPassenger: OrderPassengers
        ) {
            val intent = Intent(context, CheckoutDataPassengerActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            intent.putExtra(EXTRA_USER_ORDER, orderPassenger)
            context.startActivity(intent)
        }
    }

    object DataHolder {
        var emailOrder: String? = null
        var passengersDataData = PassengersData(
            title = "",
            firstName = "",
            lastName = "",
            nationality = "",
            passportNo = "",
            issuingCountry = "",
            dateOfBirth = "",
            email = "",
            phoneNumber = "",
            validUntil = "",
            passengerType = ""
        )
    }
}