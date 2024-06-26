package com.project.skypass.presentation.checkout.checkoutPayment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ActivityCheckoutPaymentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.CheckoutPaymentAdapter
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.OnItemClickedPaymentListener
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.PaymentAdapter
import com.project.skypass.presentation.checkout.checkoutmidtrans.CheckoutMidtransActivity
import com.project.skypass.utils.proceedWhen

class CheckoutPaymentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutPaymentBinding.inflate(layoutInflater) }
    private lateinit var paymentAdapter: CheckoutPaymentAdapter
    private lateinit var paymentListAdapter: PaymentAdapter
    private val viewModel: CheckoutPaymentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapterListPayment()
        setAdapterPassengers()
        getBookingData()
        setOnClick()
        setDataPassengers()
    }

    private fun setAdapterListPayment() {
        paymentListAdapter = PaymentAdapter()
        binding.rvDataPayment.adapter = paymentListAdapter
    }

    private fun onItemClick(item: PassengersData) {

    }

    private fun setAdapterPassengers(){
        val itemClickListener = object : OnItemClickedPaymentListener<PassengersData> {
            override fun onItemClicked(item: PassengersData) {
                onItemClick(item)
            }
        }
        paymentAdapter = CheckoutPaymentAdapter(itemClickListener)
        binding.rvPassenger.adapter = paymentAdapter
    }

    private fun setDataPassengers() {
        val orderPassengers = intent.getParcelableArrayListExtra<PassengersData>(EXTRA_USER_ORDER)
        if (orderPassengers != null) {
            viewModel.setPassengersData(orderPassengers)
        }
    }

    private fun getBookingData() {
        viewModel.getBooking(
            viewModel.getToken(),
            intent.getStringExtra(EXTRA_FLIGHT)!!
        ).observe(this) {
            it.proceedWhen (
                doOnSuccess = { success ->
                    success.payload?.let { booking ->
                        paymentListAdapter.submitDataPayment(booking)
                    }
                },
                doOnLoading = {

                },
                doOnError = {

                }
            )
        }

        viewModel.passengersData.observe(this) {
            paymentAdapter.submitData(it)
        }
    }

    private fun setOnClick(){
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.createPayment(
                viewModel.getToken(),
                intent.getStringExtra(EXTRA_FLIGHT)!!
            ).observe(this){
                it.proceedWhen(
                    doOnSuccess = { success ->
                        navigateToMidtrans(success.payload?.urlMidtrans!!)
                    },
                    doOnLoading = {

                    },
                    doOnError = {

                    }
                )
            }
        }
    }

    private fun navigateToMidtrans(paymentId: String) {
        startActivity(
            Intent(this, CheckoutMidtransActivity::class.java)
                .putExtra(CheckoutMidtransActivity.EXTRA_MIDTRANS, paymentId)
        )
    }


    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"

        fun sendDataOrder(
            context: Context,
            paymentId: String,
            orderPassenger: List<PassengersData>
        ) {
            val intent = Intent(context, CheckoutPaymentActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, paymentId)
            intent.putParcelableArrayListExtra(EXTRA_USER_ORDER, ArrayList(orderPassenger))
            context.startActivity(intent)
        }

    }

}