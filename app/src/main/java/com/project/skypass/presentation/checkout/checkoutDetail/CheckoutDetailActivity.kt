package com.project.skypass.presentation.checkout.checkoutDetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding

class CheckoutDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDetailBinding.inflate(layoutInflater) }
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