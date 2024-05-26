package com.project.skypass.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.databinding.ActivityLoginBinding
import com.project.skypass.presentation.main.MainActivity
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

        }
        binding.tvNotHaveAccountRegister.setOnClickListener{
            //not have account
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
                    //Toast.makeText(this, "Login Success :  ${it.payload?.message}", Toast.LENGTH_SHORT).show()
                    StyleableToast.makeText(this, "Login sukses!", R.style.ToastSuccess).show()
                    viewModel.setToken(it.payload?.token.toString())
                    viewModel.setLogin(true)
                    binding.pbLogin.isVisible = false
                    binding.btnLogin.isEnabled = true
                    lifecycleScope.launch {
                        delay(2000)
                        navigateToMain()
                    }

                },
                doOnLoading = {
                    binding.pbLogin.isVisible = true
                    binding.btnLogin.isEnabled = false
                },
                doOnError = {error ->
                    when (error.exception?.message) {
                        getString(R.string.email_not_found_exception) -> {
                            StyleableToast.makeText(this, "Alamat email tidak ditemukan!", R.style.ToastError).show()
                        }
                        getString(R.string.password_is_wrong_exception) -> {
                            StyleableToast.makeText(this, "Password yang dimasukkan salah!", R.style.ToastError).show()
                        }
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

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }

}