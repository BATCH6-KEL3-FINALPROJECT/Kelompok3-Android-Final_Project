package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.data.model.OrderPassangers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.SectionPassengerCheckout
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.DataItem
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.HeaderItem
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class CheckoutDataPassengerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataPassengerBinding.inflate(layoutInflater) }
    private var getPassengersAdult = Int?:0
    private var getPassengersChild = Int?:0
    private var getPassengersBaby = Int?:0

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
                DataItem(data) { name ->
                    Toast.makeText(this, "Item Clicked : 1", Toast.LENGTH_SHORT).show()
                }
            }
            section.addAll(dataSection)
            section
        }
        adapter.addAll(section)
    }

    private fun getListData(): List<SectionPassengerCheckout> {
        val result = mutableListOf<SectionPassengerCheckout>()
        for (i in 1..getPassengersAdult as Int) {
            result.add(
                    SectionPassengerCheckout("Data Passenger Adult $i", listOf("Adult $i")),
            )
        }
        for (i in 1..getPassengersChild as Int) {
            result.add(
                    SectionPassengerCheckout("Data Passenger Child $i", listOf("Child $i")),
            )
        }
        for (i in 1..getPassengersBaby as Int) {
            result.add(
                    SectionPassengerCheckout("Data Passenger Baby $i", listOf("Baby $i")),
            )
        }
        return result
    }



    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            sendOrderData(it)
            getDataPassenger(it)
        }
    }
    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            CheckoutSeatActivity.sendDataOrder(
                this,
                item,
            )
        }
    }

    private fun getDataPassenger(item: OrderUser){
        getPassengersAdult = item.passengersAdult!!
        getPassengersChild = item.passengersChild!!
        getPassengersBaby = item.passengersBaby!!
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
            orderPassenger: OrderPassangers
        ){
            val intent = Intent(context, CheckoutDataPassengerActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            intent.putExtra(EXTRA_USER_ORDER, orderPassenger)
            context.startActivity(intent)
        }
    }
}