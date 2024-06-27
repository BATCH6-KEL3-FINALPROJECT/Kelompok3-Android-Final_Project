package com.project.skypass.presentation.auth.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.databinding.ActivityLoginBinding
import com.project.skypass.databinding.LayoutStateErrorBinding
import com.project.skypass.databinding.LayoutStateLoadingBinding
import com.project.skypass.databinding.LayoutStateSuccessBinding
import com.project.skypass.presentation.auth.forgetpassword.ForgetPasswordActivity
import com.project.skypass.presentation.auth.login.LoginViewModel.Companion.RC_SIGN_IN
import com.project.skypass.presentation.auth.register.RegisterActivity
import com.project.skypass.presentation.auth.resetpassword.ResetPasswordActivity
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.decodeJWT
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel : LoginViewModel by viewModel()
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener{
            inputLogin()
        }
        binding.tvForgotPassword.setOnClickListener{
            navigateToResetPassword()
        }
        binding.tvNotHaveAccountRegister.setOnClickListener{
            navigateToRegister()
        }
        binding.btnLoginGoogle.setOnClickListener{
            loginWithGoogle()
        }
    }

    private fun loginWithGoogle() {
        val signInIntent = viewModel.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            viewModel.handleSignInResult(data).observe(this) { result ->

            }
        }
    }

    private fun inputLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        doLogin(email, password)
    }

    private fun doLogin(email: String, password: String) {
        viewModel.doLogin(email, password).observe(this){result ->
            result.proceedWhen(
                doOnSuccess = {
                    //dialog?.dismiss()
                    //doSuccess()
                    binding.pbLogin.isVisible = false
                    binding.btnLogin.isEnabled = true
                    StyleableToast.makeText(this,
                        getString(R.string.login_success), R.style.ToastSuccess).show()
                    setLoginPref(it.payload?.data?.token.toString())
                    viewModel.setEmail(email)
                    lifecycleScope.launch {
                        delay(2000)
                        navigateToMain()
                    }
                },
                doOnLoading = {
                    //dialog?.dismiss()
                    //doLoading()
                    binding.pbLogin.isVisible = true
                    binding.btnLogin.isEnabled = false
                },
                doOnError = {error ->
                    /*dialog?.dismiss()
                    when (error.exception?.message) {
                        getString(R.string.email_not_found_exception) -> {
                            StyleableToast.makeText(this,
                                getString(R.string.email_not_found), R.style.ToastError).show()
                            binding.etEmail.setBackgroundResource(R.drawable.bg_input_error)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_selector_input)
                        }
                        getString(R.string.password_is_wrong_exception) -> {
                            StyleableToast.makeText(this,
                                getString(R.string.password_not_found), R.style.ToastError).show()
                            binding.etEmail.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_input_error)
                        }
                        getString(R.string.no_internet_connection_exception) -> {
                            StyleableToast.makeText(this,
                                getString(R.string.no_internet_connection), R.style.ToastError).show()
                        }
                        else -> {
                            doError()
                            *//*StyleableToast.makeText(this,
                                getString(R.string.unknown_error), R.style.ToastError).show()*//*
                            binding.etEmail.setBackgroundResource(R.drawable.bg_selector_input)
                            binding.etPassword.setBackgroundResource(R.drawable.bg_selector_input)
                        }
                    }*/
                    dialog?.dismiss()
                    if (error.exception is ApiErrorException) {
                        val errorMessage = error.exception.errorResponse
                        StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                    } else if (error.exception is NoInternetException) {
                        StyleableToast.makeText(this, getString(R.string.no_internet_connection), R.style.ToastError).show()
                    } else if (error.exception is UnauthorizedException) {
                        val errorMessage = error.exception.errorUnauthorizedResponse
                        StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                    }
                    binding.pbLogin.isVisible = false
                    binding.btnLogin.isEnabled = true
                },
                doOnEmpty = {
                    Toast.makeText(this, "Empty : ${it.payload?.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun setLoginPref(token: String) {
        viewModel.setToken(token)
        viewModel.setLogin(true)
        viewModel.setUserID(token)
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }

    private fun navigateToRegister() {
        startActivity(
            Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }

    private fun navigateToResetPassword() {
        startActivity(
            Intent(this, ForgetPasswordActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        )
    }

    private fun doLoading(){
        val dialogBinding = LayoutStateLoadingBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doSuccess(){
        val dialogBinding = LayoutStateSuccessBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doError(){
        val dialogBinding = LayoutStateErrorBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

}