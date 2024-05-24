package com.project.skypass.presentation.auth.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.databinding.ActivityLoginBinding
import com.project.skypass.utils.proceedWhen
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
        viewModel.doLogin(email, password).observe(this){
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                },
                doOnError = {error ->
                    Toast.makeText(this, "Gatau ini cara dapetin response apinya gimana, besok ku coba WKWKWK", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}