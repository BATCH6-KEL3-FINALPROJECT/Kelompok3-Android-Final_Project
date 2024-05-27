package com.project.skypass.presentation.auth.verification

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.databinding.ActivityVerificationBinding
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerificationActivity : AppCompatActivity() {
    private val binding: ActivityVerificationBinding by lazy {
        ActivityVerificationBinding.inflate(layoutInflater)
    }
    private val verifyViewModel: VerificationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showEmail()
        resetCountdown()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.ivBackOtp.setOnClickListener {
            onBackPressed()
        }
        binding.tvResendOtp.setOnClickListener {
            resetCountdown()
        }
        binding.btnVerify.setOnClickListener {
            doVerify()
        }
    }

    private fun showEmail() {
        val email = "user@example.com"
        binding.tvInputOtp.text = getString(R.string.text_input_6_code_otp, email)
    }

    private fun doVerify() {
        val email = "user@example.com"
        val otp = binding.pvInputOtp.text.toString().trim()
        proceedVerify(email, otp)
    }

    private fun proceedVerify(
        email: String,
        otp: String
    ) {
        verifyViewModel.doVerify(email, otp).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_register_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnError = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_otp_wrong),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                },
            )
        }
    }

    private fun resetCountdown() {
        val countdownTime = 60000L
        val interval = 1000L

        object : CountDownTimer(countdownTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvResendOtp.isVisible = false
                binding.tvResendOtpIn60Seconds.isVisible = true
                binding.tvResendOtpIn60Seconds.text =
                    getString(R.string.text_resend_otp_in_60_seconds, secondsRemaining)
            }

            override fun onFinish() {
                binding.tvResendOtpIn60Seconds.isVisible = false
                binding.tvResendOtp.isVisible = true
            }
        }.start()
    }
}