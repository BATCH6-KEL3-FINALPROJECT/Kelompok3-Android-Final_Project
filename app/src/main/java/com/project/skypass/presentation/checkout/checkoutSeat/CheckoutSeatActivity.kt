package com.project.skypass.presentation.checkout.checkoutSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.Seat
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import com.project.skypass.utils.proceedWhen
import dev.jahidhasanco.seatbookview.SeatBookView
import dev.jahidhasanco.seatbookview.SeatClickListener
import dev.jahidhasanco.seatbookview.SeatLongClickListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutSeatActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCheckoutSeatBinding.inflate(layoutInflater) }
    private val title = mutableListOf<String>()
    var listSeat:String = ""
    var listTitle: List<String>? = null
    var listSeatId: List<String>? = null
    private val seats = StringBuilder()
    var toSeat = listOf<String>()

    private val viewModel: CheckoutSeatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
    }

    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeResult() {

    }

    private fun setupTitle(limit: Int) {
        var currentCharCode = 'A'.code
        var count = 0
        for (i in 1..100) {
            title.add(
                "/"
            )
            seats.append("/")
            for (j in 1..7) {
                var data = j
                if (j == 4) {
                    title.add("")
                    seats.append("_")
                } else {
                    if (j > 3) {
                        data = j - 1
                    }
                    seats.append("A")
                    title.add("${currentCharCode.toChar()}$data")
                    count += 1
                }
                if (count == limit) {
                    break
                }
            }
            if (count == limit) {
                break
            }
            currentCharCode++
        }
    }


    private fun setupSeatBookView(item: OrderUser) {
        val seatBookView: SeatBookView = binding.layoutSeat
        val totalPassenger = item.passengersAdult!! + item.passengersChild!! + item.passengersBaby!!
        seatBookView.setSeatsLayoutString(listSeat)
            .isCustomTitle(true)
            .setCustomTitle(listTitle!!)
            .setSeatLayoutPadding(0)
            .setSelectSeatLimit(totalPassenger)
            .setSeatSizeBySeatsColumnAndLayoutWidth(7, -1)
        seatBookView.show()

        seatBookView.setSeatClickListener(
            object : SeatClickListener {
                override fun onAvailableSeatClick(
                    selectedIdList: List<Int>,
                    view: View,
                ) {
                    toSeat = getSelectedTitles(selectedIdList, listSeatId!!)
                    Toast.makeText(this@CheckoutSeatActivity, "$toSeat", Toast.LENGTH_SHORT).show()
                }

                override fun onBookedSeatClick(view: View) {
                }

                override fun onReservedSeatClick(view: View) {
                }
            },
        )

        seatBookView.setSeatLongClickListener(
            object : SeatLongClickListener {
                override fun onAvailableSeatLongClick(view: View) {
                    Toast.makeText(
                        this@CheckoutSeatActivity,
                        "seat ${item.airlineName}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onBookedSeatLongClick(view: View) {
                }

                override fun onReservedSeatLongClick(view: View) {
                }
            },
        )
    }

    private fun getSelectedTitles(selectedIdList: List<Int>, title: List<String>): List<String> {
        val selectedTitles = mutableListOf<String>()
        for (index in selectedIdList) {

            if (index in title.indices) {

                selectedTitles.add(title[index - 1])
            }
        }
        return selectedTitles
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(CheckoutSeatActivity.EXTRA_FLIGHT)?.let {

            viewModel.getSeats(it.seatClass!!, it.flightId!!, 200).observe(this){result ->
                result.payload?.let { (seat, seatId, title ) ->
                    listSeat = seat
                    listTitle = title
                    listSeatId = seatId
                    observeResult()
                    setClickListeners()
                    setupSeatBookView(it)
                }
            }

            intent.extras?.getParcelable<OrderPassengers>(CheckoutDataPassengerActivity.EXTRA_USER_ORDER)
                ?.let { orderPassenger ->
                    sendOrderData(it, orderPassenger)
                    setProfileData(it,orderPassenger)
                }
        }
    }

    private fun setProfileData(item: OrderUser, passengerData: OrderPassengers) {
        binding.apply {
            if (item.seatsAvailableRoundTrip != null && item.supportRoundTrip == false) {
                binding.tvHeader.text = "Kursi Tersedia " + item.seatsAvailableRoundTrip + " Arrival"
            }else{
                binding.tvHeader.text = "Kursi Tersedia " + item.seatsAvailable + " Departure"
            }
        }

    }


    private fun sendOrderData(item: OrderUser, passengerData: OrderPassengers) {
        binding.btnSubmit.setOnClickListener {
            if (item.supportRoundTrip == true ) {
                CheckoutDetailActivity.sendDataOrder(
                    this,
                    item,
                    OrderPassengers(
                        name = passengerData.name,
                        email = passengerData.email,
                        familyName = passengerData.familyName,
                        noTelephone = passengerData.noTelephone,
                        passengers = passengerData.passengers,
                        seatOrderDeparture = toSeat,
                        seatOrderArrival = passengerData.seatOrderArrival
                    )
                )
            }else if(item.isRoundTrip == false && item.supportRoundTrip == false && item.seatsAvailableRoundTrip == null){
                CheckoutDetailActivity.sendDataOrder(
                    this,
                    item,
                    OrderPassengers(
                        name = passengerData.name,
                        email = passengerData.email,
                        familyName = passengerData.familyName,
                        noTelephone = passengerData.noTelephone,
                        passengers = passengerData.passengers,
                        seatOrderDeparture = toSeat,
                        seatOrderArrival = passengerData.seatOrderArrival
                    )
                )
            }
            else {
                CheckoutDetailActivity.sendDataOrder(
                    this,
                    item,
                    OrderPassengers(
                        name = passengerData.name,
                        email = passengerData.email,
                        familyName = passengerData.familyName,
                        noTelephone = passengerData.noTelephone,
                        passengers = passengerData.passengers, // need edit
                        seatOrderDeparture = passengerData.seatOrderDeparture,
                        seatOrderArrival = toSeat
                    )
                )
            }
        }
    }


    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
            orderPassenger: OrderPassengers
        ) {
            val intent = Intent(context, CheckoutSeatActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            intent.putExtra(EXTRA_USER_ORDER, orderPassenger)
            context.startActivity(intent)
        }
    }

}