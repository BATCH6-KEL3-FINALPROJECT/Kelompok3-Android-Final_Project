package com.project.skypass.presentation.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.project.skypass.R
import com.project.skypass.databinding.ActivityRegisterBinding
import com.project.skypass.presentation.auth.login.LoginActivity
import com.project.skypass.presentation.auth.verification.VerificationActivity
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.highLightWord
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        addTextWatchers()
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

    private fun addTextWatchers() {
        binding.etName.addTextChangedListener(createTextWatcher { checkNameValidation(it) })
        binding.etEmail.addTextChangedListener(createTextWatcher { checkEmailValidation(it) })
        binding.etPhoneNumber.addTextChangedListener(createTextWatcher { checkPhoneNumberValidation(it) })
        binding.etPassword.addTextChangedListener(createTextWatcher { checkPasswordValidation(it, binding.tilPassword) })
    }

    private fun createTextWatcher(validation: (String) -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {}

            override fun afterTextChanged(s: Editable?) {
                validation(s.toString())
            }
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
                    StyleableToast.makeText(
                        this,
                        getString(R.string.text_otp_send_success),
                        R.style.ToastSuccess,
                    ).show()
                    lifecycleScope.launch {
                        delay(2000)
                        navigateToVerification(email)
                    }
                },
                doOnError = { error ->
                    if (error.exception is ApiErrorException) {
                        val errorMessage = error.exception.errorResponse
                        StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                    } else if (error.exception is NoInternetException) {
                        StyleableToast.makeText(this, getString(R.string.no_internet_connection), R.style.ToastError).show()
                    } else if (error.exception is UnauthorizedException) {
                        val errorMessage = error.exception.errorUnauthorizedResponse
                        StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                    }
                    binding.pbLoading.isVisible = false
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.btnRegister.isEnabled = false
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
        return if (phoneNumber.isEmpty()) {
            binding.tilPhoneNumber.isErrorEnabled = true
            binding.tilPhoneNumber.error = getString(R.string.text_error_telepon_cannot_empty)
            false
        } else {
            binding.tilPhoneNumber.isErrorEnabled = false
            binding.tilPhoneNumber.setEndIconDrawable(R.drawable.ic_success)
            binding.tilPhoneNumber.setEndIconTintList(getColorStateList(R.color.colorSuccess))
            true
        }
    }

    private fun checkNameValidation(fullName: String): Boolean {
        return if (fullName.isEmpty()) {
            binding.tilName.isErrorEnabled = true
            binding.tilName.error = getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.tilName.isErrorEnabled = false
            binding.tilName.setEndIconDrawable(R.drawable.ic_success)
            binding.tilName.setEndIconTintList(getColorStateList(R.color.colorSuccess))
            true
        }
    }

    private fun checkEmailValidation(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.error = getString(R.string.text_error_email_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.error = getString(R.string.text_error_email_invalid)
            false
        } else {
            binding.tilEmail.isErrorEnabled = false
            binding.tilEmail.setEndIconDrawable(R.drawable.ic_success)
            binding.tilEmail.setEndIconTintList(getColorStateList(R.color.colorSuccess))
            true
        }
    }

    private fun checkPasswordValidation(
        password: String,
        textInputLayout: TextInputLayout,
    ): Boolean {
        return if (password.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.text_error_password_empty)
            false
        } else if (password.length < 8) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.text_error_password_less_than_8_char)
            false
        } else {
            binding.tilPassword.isErrorEnabled = false
            binding.tilPassword.setEndIconDrawable(R.drawable.ic_success)
            binding.tilPassword.setEndIconTintList(getColorStateList(R.color.colorSuccess))
            true
        }
    }
}
