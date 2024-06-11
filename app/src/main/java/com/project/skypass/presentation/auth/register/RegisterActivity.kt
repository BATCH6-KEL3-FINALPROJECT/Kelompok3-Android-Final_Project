package com.project.skypass.presentation.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.project.skypass.R
import com.project.skypass.databinding.ActivityRegisterBinding
import com.project.skypass.presentation.auth.login.LoginActivity
import com.project.skypass.presentation.auth.verification.VerificationActivity
import com.project.skypass.utils.highLightWord
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.ivBackRegister.setOnClickListener {
            onBackPressed()
        }
        binding.btnRegister.setOnClickListener {
            doRegister()
        }
        binding.tvNavToLogin.highLightWord(getString(R.string.text_login_here)) {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun navigateToVerification(email: String) {
        startActivity(
            Intent(this, VerificationActivity::class.java).apply {
                putExtra("email", email)
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun doRegister() {
        if (isFormValid()) {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val fullName = binding.etName.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            proceedRegister(fullName, email, phoneNumber, password)
        }
    }

    private fun proceedRegister(
        fullName: String,
        email: String,
        phoneNumber: String,
        password: String,
    ) {
        registerViewModel.doRegister(fullName, email, phoneNumber, password).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    navigateToVerification(email)
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_otp_send_success),
                        R.style.ToastSuccess
                    ).show()
                },
                doOnError = { error ->
                    when (error.exception?.message) {
                        getString(R.string.email_registered_exception) -> {
                            StyleableToast.makeText(
                                this,
                                getString(R.string.email_registered), R.style.ToastError
                            ).show()
                            binding.etEmail.setBackgroundResource(R.drawable.bg_input_error)
                            binding.etName.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPhoneNumber.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_selector_input)
                        }

                        getString(R.string.password_less_than_8_char_exception) -> {
                            StyleableToast.makeText(
                                this,
                                getString(R.string.text_error_password_less_than_8_char),
                                R.style.ToastError
                            ).show()
                            binding.etName.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etEmail.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPhoneNumber.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_input_error)
                        }

                        else -> {
                            StyleableToast.makeText(
                                this,
                                error.exception?.message, R.style.ToastError
                            ).show()
                            binding.etEmail.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_selector_input)
                        }
                    }
                    binding.pbLoading.isVisible = false
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                },
            )
        }
    }

    private fun isFormValid(): Boolean {
        val password = binding.etPassword.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()
        val fullName = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        return checkNameValidation(fullName) && checkEmailValidation(email) &&
                checkPhoneNumberValidation(phoneNumber) &&
                checkPasswordValidation(password, binding.tilPassword)
    }

    private fun checkPhoneNumberValidation(phoneNumber: String): Boolean {
        binding.tilPhoneNumber.isEndIconVisible = false
        return if (phoneNumber.isEmpty()) {
            binding.tilPhoneNumber.isErrorEnabled = true
            binding.tilPhoneNumber.isEndIconVisible = false
            binding.tilPhoneNumber.error = getString(R.string.text_error_telepon_cannot_empty)
            false
        } else {
            binding.tilPhoneNumber.isErrorEnabled = false
            binding.tilPhoneNumber.isEndIconVisible = true
            true
        }
    }

    private fun checkNameValidation(fullName: String): Boolean {
        binding.tilName.isEndIconVisible = false
        return if (fullName.isEmpty()) {
            binding.tilName.isErrorEnabled = true
            binding.tilName.isEndIconVisible = false
            binding.tilName.error = getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.tilName.isErrorEnabled = false
            binding.tilName.isEndIconVisible = true
            true
        }
    }

    private fun checkEmailValidation(email: String): Boolean {
        binding.tilEmail.isEndIconVisible = false
        return if (email.isEmpty()) {
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.isEndIconVisible = false
            binding.tilEmail.error = getString(R.string.text_error_email_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.isEndIconVisible = false
            binding.tilEmail.error = getString(R.string.text_error_email_invalid)
            false
        } else {
            binding.tilEmail.isErrorEnabled = false
            binding.tilEmail.isEndIconVisible = true
            true
        }
    }

    private fun checkPasswordValidation(
        password: String,
        textInputLayout: TextInputLayout,
    ): Boolean {
        return if (password.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error =
                getString(R.string.text_error_password_empty)
            false
        } else if (password.length < 8) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error =
                getString(R.string.text_error_password_less_than_8_char)
            false
        } else {
            textInputLayout.isErrorEnabled = false
            true
        }
    }
}