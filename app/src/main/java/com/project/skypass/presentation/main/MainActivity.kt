package com.project.skypass.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.skypass.R
import com.project.skypass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNav()
    }

    private fun setBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navBottomView.setupWithNavController(navController)
    }
}