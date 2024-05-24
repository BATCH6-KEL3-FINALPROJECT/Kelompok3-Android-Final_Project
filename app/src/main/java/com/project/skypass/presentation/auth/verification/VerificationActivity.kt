package com.project.skypass.presentation.auth.verification

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.databinding.ActivityRegisterBinding
import com.project.skypass.databinding.ActivityVerificationBinding
import com.project.skypass.utils.highLightWord

class VerificationActivity : AppCompatActivity() {
    private val binding: ActivityVerificationBinding by lazy {
        ActivityVerificationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.ivBackOtp.setOnClickListener {
            onBackPressed()
        }
        binding.btnVerify.setOnClickListener {
        }
    }
}