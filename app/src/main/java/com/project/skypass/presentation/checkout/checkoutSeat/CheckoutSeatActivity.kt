package com.project.skypass.presentation.checkout.checkoutSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import com.project.skypass.utils.proceedWhen
import dev.jahidhasanco.seatbookview.SeatBookView
import dev.jahidhasanco.seatbookview.SeatClickListener
import dev.jahidhasanco.seatbookview.SeatLongClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutSeatActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCheckoutSeatBinding.inflate(layoutInflater) }
    private val title = mutableListOf<String>()
    var listSeat:String = ""
    var listTitle: List<String>? = null
    var listSeatId: List<String>? = null
    private val seats = StringBuilder()
    var getSeatId = listOf<String>()
    var getSeatTitle = listOf<String>()

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
                    getSeatTitle = emptyList()
                    getSeatId = emptyList()
                    getSeatId = getSelectedSeatId(selectedIdList, listSeatId!!)
                    getSeatTitle = getSelectedTitle(selectedIdList, listTitle!!)
                    Toast.makeText(this@CheckoutSeatActivity, "$getSeatTitle", Toast.LENGTH_SHORT).show()
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

                }

                override fun onBookedSeatLongClick(view: View) {
                }

                override fun onReservedSeatLongClick(view: View) {
                }
            },
        )
    }

    private fun getSelectedSeatId(selectedIdList: List<Int>, seatId: List<String>): List<String> {
        val selectedSeatId = mutableListOf<String>()
        for (index in selectedIdList) {

            if (index in seatId.indices) {

                selectedSeatId.add(seatId[index - 1])
            }
        }
        return selectedSeatId
    }
    private fun getSelectedTitle(selectedIdList: List<Int>, titleSeat: List<String>): List<String> {
        val selectedSeatTitle = mutableListOf<String>()
        for (index in selectedIdList) {

            if (index in titleSeat.indices) {
                var cleanTitle = index / 3
                if (index % 3 == 0) {
                    cleanTitle -= 1
                }
                selectedSeatTitle.add(titleSeat[index + cleanTitle])
            }
        }
        return selectedSeatTitle
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(CheckoutSeatActivity.EXTRA_FLIGHT)?.let {

            if(it.supportRoundTrip == true){
                viewModel.getSeats(it.seatClass!!, it.flightId!!, 200).observe(this) {data ->
                    data.proceedWhen(
                        doOnSuccess = { result ->
                            binding.layoutContentState.root.isVisible = false
                            result.payload?.let { (seat, seatId, title) ->
                                listSeat = seat
                                listTitle = title
                                listSeatId = seatId
                                observeResult()
                                setClickListeners()
                                setupSeatBookView(it)
                            }
                        },
                        doOnEmpty = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.text =
                                getString(R.string.text_empty_seat_class)
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                        },
                        doOnLoading = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.isVisible = false
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                        }, doOnError = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.text =
                                getString(R.string.text_error_seat_checkout)
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                        }
                    )
                }
            } else {
                viewModel.getSeats(it.seatClass!!, it.flightIdRoundTrip!!, 200).observe(this) {data ->
                    data.proceedWhen(
                        doOnSuccess = { result ->
                            binding.layoutContentState.root.isVisible = false
                            result.payload?.let { (seat, seatId, title) ->
                                listSeat = seat
                                listTitle = title
                                listSeatId = seatId
                                observeResult()
                                setClickListeners()
                                setupSeatBookView(it)
                            }
                        },
                        doOnEmpty = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.text =
                                getString(R.string.text_empty_seat_class)
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                        },
                        doOnLoading = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.isVisible = false
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                        }, doOnError = {
                            binding.layoutContentState.root.isVisible = true
                            binding.layoutContentState.textError.text =
                                getString(R.string.text_error_seat_checkout)
                            binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                        }
                    )
                }
            }

            /*viewModel.getSeats(it.seatClass!!, it.flightId!!, 200).observe(this) {data ->
                data.proceedWhen(
                    doOnSuccess = { result ->
                        binding.layoutContentState.root.isVisible = false
                        result.payload?.let { (seat, seatId, title) ->
                            listSeat = seat
                            listTitle = title
                            listSeatId = seatId
                            observeResult()
                            setClickListeners()
                            setupSeatBookView(it)
                        }
                    },
                    doOnEmpty = {
                        binding.layoutContentState.root.isVisible = true
                        binding.layoutContentState.textError.text =
                            getString(R.string.text_empty_seat_class)
                        binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                    },
                    doOnLoading = {
                        binding.layoutContentState.root.isVisible = true
                        binding.layoutContentState.textError.isVisible = false
                        binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                    }, doOnError = {
                        binding.layoutContentState.root.isVisible = true
                        binding.layoutContentState.textError.text =
                            getString(R.string.text_error_seat_checkout)
                        binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                    }
                )
            }*/

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
                        seatOrderDeparture = getSeatTitle,
                        seatOrderArrival = passengerData.seatOrderArrival,
                        seatIdDeparture = getSeatId,
                        seatIdArrival = passengerData.seatIdArrival
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
                        seatOrderDeparture = getSeatTitle,
                        seatOrderArrival = passengerData.seatOrderArrival,
                        seatIdDeparture = getSeatId,
                        seatIdArrival = passengerData.seatIdArrival
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
                        seatOrderArrival = getSeatTitle,
                        seatIdDeparture = passengerData.seatIdDeparture,
                        seatIdArrival = getSeatId
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