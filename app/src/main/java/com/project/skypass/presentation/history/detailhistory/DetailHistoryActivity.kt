package com.project.skypass.presentation.history.detailhistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.data.model.History
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ActivityDetailHistoryBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity.Companion.EXTRA_FLIGHT

class DetailHistoryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailHistoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<History>(EXTRA_USER_HISTORY)?.let {

        }
    }
    private fun setProfileData(item: History) {
        binding.apply {
            tvStatus.text = item.status
            tvCode.text = item.bookingId

        }
        }

    companion object {
        const val EXTRA_USER_HISTORY = "EXTRA_USER_HISTORY"
        fun startActivity(
            context: Context,
            item: History
        ) {
            val intent = Intent(context, DetailHistoryActivity::class.java)
            intent.putExtra(EXTRA_USER_HISTORY, item)
            context.startActivity(intent)
        }
    }

}