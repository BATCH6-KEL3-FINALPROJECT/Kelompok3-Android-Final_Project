package com.project.skypass.presentation.auth.forgetpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.databinding.ActivityForgetPasswordBinding
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgetPasswordActivity : AppCompatActivity() {
    private val binding by lazy { ActivityForgetPasswordBinding.inflate(layoutInflater) }

    private val viewModel: ForgotPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnSendLinkResetPassword.setOnClickListener {
            inputEmailRequest()
        }
    }

    private fun inputEmailRequest() {
        val email = binding.etEmail.text.toString()
        sendRequestLink(email)
    }

    private fun sendRequestLink(email: String) {
        viewModel.sendRequestEmail(email).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    binding.pbLogin.isVisible = false
                    binding.btnSendLinkResetPassword.isEnabled = false
                    StyleableToast.makeText(
                        this,
                        "Request telah berhasil dikirimkan ke email yang anda masukkan",
                        R.style.ToastSuccess,
                    ).show()
                },
                doOnLoading = {
                    binding.pbLogin.isVisible = true
                    binding.btnSendLinkResetPassword.isEnabled = true
                },
                doOnError = {
                    binding.pbLogin.isVisible = false
                    binding.btnSendLinkResetPassword.isEnabled = false
                    StyleableToast.makeText(
                        this,
                        "Request gagal dikirimkan ke email yang anda masukkan",
                        R.style.ToastError,
                    ).show()
                },
            )
        }
    }
}
