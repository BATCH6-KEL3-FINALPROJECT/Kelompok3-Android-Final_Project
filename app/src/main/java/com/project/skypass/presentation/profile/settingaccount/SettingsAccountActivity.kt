package com.project.skypass.presentation.profile.settingaccount

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.project.skypass.databinding.ActivitySettingsAccountBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsAccountActivity : AppCompatActivity() {

    private val binding: ActivitySettingsAccountBinding by lazy {
        ActivitySettingsAccountBinding.inflate(layoutInflater)
    }

    private val settingsAccountViewModel: SettingsAccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        settingsAccountViewModel.isUsingDarkMode.observe(this) { isUsingDarkMode ->
            applyUiMode(isUsingDarkMode)
        }

        setSwitchListener()
    }

    private fun applyUiMode(isUsingDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isUsingDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        binding.switchTheme.isChecked = isUsingDarkMode
    }

    private fun setSwitchListener() {
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            settingsAccountViewModel.setUsingDarkMode(isChecked)
        }
    }
}
