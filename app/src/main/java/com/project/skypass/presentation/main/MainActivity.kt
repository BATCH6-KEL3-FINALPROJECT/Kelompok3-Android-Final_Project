package com.project.skypass.presentation.main

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.ActivityMainBinding
import com.project.skypass.presentation.LoginBottomSheetFragment

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNav()
    }

    private fun setBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navBottomView.setupWithNavController(navController)
        binding.navBottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tab_profile -> {
                    if (!isUserLoggedIn()) {
                        showLoginBottomSheet()
                        false
                    } else {
                        NavigationUI.onNavDestinationSelected(item, navController)
                    }
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                }
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.menu_tab_home -> {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    window.statusBarColor = getColor(R.color.colorNavbar)
                }
                else -> {
                    window.decorView.systemUiVisibility = if (isNightMode()) {
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    } else {
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    window.statusBarColor = Color.TRANSPARENT
                }
            }
        }
    }

    private fun isNightMode(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun isUserLoggedIn(): Boolean {
        return false
    }

    private fun showLoginBottomSheet() {
        val loginBottomSheet = LoginBottomSheetFragment()
        loginBottomSheet.show(supportFragmentManager, loginBottomSheet.tag)
    }
}

