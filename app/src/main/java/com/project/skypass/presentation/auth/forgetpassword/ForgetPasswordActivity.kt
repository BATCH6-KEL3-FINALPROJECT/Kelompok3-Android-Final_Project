package com.project.skypass.presentation.auth.forgetpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {

    private val binding by lazy { ActivityForgetPasswordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}