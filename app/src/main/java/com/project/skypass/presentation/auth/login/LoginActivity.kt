package com.project.skypass.presentation.auth.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.databinding.ActivityLoginBinding
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
        TODO("Not yet implemented")
    }

}