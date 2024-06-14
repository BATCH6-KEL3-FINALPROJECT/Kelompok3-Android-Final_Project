package com.project.skypass.presentation.auth.verification

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.databinding.ActivityVerificationBinding
import com.project.skypass.presentation.auth.login.LoginActivity
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
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
            finish()
        }
        binding.tvResendOtp.setOnClickListener {
            doResendOtp()
        }
        binding.btnVerify.setOnClickListener {
            doVerify()
        }
    }

    private fun showEmail() {
        val email = intent.getStringExtra("email")
        binding.tvInputOtp.text = getString(R.string.text_input_6_code_otp, email)
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun doVerify() {
        val email = intent.getStringExtra("email")
        val otp = binding.pvInputOtp.text.toString().trim()
        if (email != null) {
            proceedVerify(email, otp)
        }
    }

    private fun proceedVerify(
        email: String,
        otp: String
    ) {
        verifyViewModel.doVerify(email, otp).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    navigateToLogin()
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_register_success),
                        R.style.ToastSuccess
                    ).show()
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_otp_wrong),
                        R.style.ToastError
                    ).show()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                },
            )
        }
    }

    private fun doResendOtp() {
        val email = intent.getStringExtra("email")
        if (email != null) {
            proceedResendOtp(email)
        }
    }

    private fun proceedResendOtp(email: String) {
        verifyViewModel.doResendCode(email).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    resetCountdown()
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_resend_otp_success),
                        R.style.ToastSuccess
                    ).show()
                },
                doOnError = {
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_email_not_found),
                        R.style.ToastError
                    ).show()
                }
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