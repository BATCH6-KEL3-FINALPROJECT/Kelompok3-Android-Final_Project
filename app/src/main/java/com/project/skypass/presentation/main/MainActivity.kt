package com.project.skypass.presentation.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

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
                R.id.menu_tab_profile,
                R.id.menu_tab_home,
                R.id.menu_tab_history,
                R.id.menu_tab_notification -> {
                    if (!viewModel.isUserLoggedIn()) {
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

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser
        ){
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }

    fun navigateToProfile() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navBottomView.selectedItemId = R.id.menu_tab_profile
        navController.navigate(R.id.menu_tab_profile)
    }

    private fun showLoginBottomSheet() {
        val loginBottomSheet = LoginBottomSheetFragment()
        loginBottomSheet.show(supportFragmentManager, loginBottomSheet.tag)
    }
}