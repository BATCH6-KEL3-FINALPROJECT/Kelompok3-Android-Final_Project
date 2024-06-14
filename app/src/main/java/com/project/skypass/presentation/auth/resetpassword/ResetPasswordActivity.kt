package com.project.skypass.presentation.auth.resetpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

    private val binding : ActivityResetPasswordBinding by lazy {
        ActivityResetPasswordBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onClickListener()
    }

    private fun onClickListener() {
        binding.ivAppbarBack.setOnClickListener{
            onBackPressed()
        }
    }
}