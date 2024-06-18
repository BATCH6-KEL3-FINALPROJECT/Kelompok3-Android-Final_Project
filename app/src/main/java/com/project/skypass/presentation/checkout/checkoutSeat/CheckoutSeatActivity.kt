package com.project.skypass.presentation.checkout.checkoutSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import dev.jahidhasanco.seatbookview.SeatBookView
import dev.jahidhasanco.seatbookview.SeatClickListener
import dev.jahidhasanco.seatbookview.SeatLongClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutSeatActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCheckoutSeatBinding.inflate(layoutInflater) }
    private val title = mutableListOf<String>()
    private val seatss = StringBuilder()

    private val viewModel: CheckoutSeatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        observeResult()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeResult() {
//        observe view model
        /*viewModel.getSeat("abc", "abc").observe(this){

        }*/
        viewModel.getSeats("economy", "dc7e52f4-3781-4e92-a099-f2cf88c6f335", 200).observe(this){

        }
    }

    private fun setupTitle(limit: Int) {
        var currentCharCode = 'A'.code
        var count = 0
        for (i in 1..100) {
            title.add(
                "/"
            )
            seatss.append("/")
            for (j in 1..7) {
                var data = j
                if (j == 4) {
                    title.add("")
                    seatss.append("_")
                } else {
                    if (j > 3) {
                        data = j - 1
                    }
                    seatss.append("A")
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
        seatBookView.setSeatsLayoutString(seatss.toString())
            .isCustomTitle(true)
            .setCustomTitle(title)
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
                    Toast.makeText(this@CheckoutSeatActivity, "seat ${item.airlineName}", Toast.LENGTH_SHORT).show()
                }

                override fun onBookedSeatLongClick(view: View) {
                }

                override fun onReservedSeatLongClick(view: View) {
                }
            },
        )
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(CheckoutSeatActivity.EXTRA_FLIGHT)?.let {
            sendOrderData(it)
            setupTitle(it.seatsAvailable!!)
            binding.tvHeader.text = "Kursi Tersedia " +it.seatsAvailable.toString()
            setupSeatBookView(it)
        }
    }

    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            CheckoutDetailActivity.sendDataOrder(
                this,
                item,
            )
        }
    }


    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser
        ) {
            val intent = Intent(context, CheckoutSeatActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }

}