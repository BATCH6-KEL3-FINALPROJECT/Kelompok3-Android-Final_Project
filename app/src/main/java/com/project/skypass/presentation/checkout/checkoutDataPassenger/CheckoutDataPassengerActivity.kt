package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding

class CheckoutDataPassengerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataPassengerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
    }
    private fun setClickListeners() {
//        onclick binding
    }
    private fun observeResult(){
//        observe view model
    }
}