package com.project.skypass.presentation.onboarding

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.skypass.databinding.ActivityOnboardingBinding
import com.project.skypass.presentation.onboarding.adapter.ViewPagerAdapter
import com.project.skypass.presentation.onboarding.page.FirstOnboardingFragment
import com.project.skypass.presentation.onboarding.page.SecondOnboardingFragment
import com.project.skypass.presentation.onboarding.page.ThirdOnboardingFragment

class OnboardingActivity : AppCompatActivity() {

    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                binding.root.viewTreeObserver.removeOnPreDrawListener(this)
                enableEdgeToEdge()
                return true
            }
        })

        onboarding()

    }

    private fun onboarding() {
        val fragmentList = arrayListOf<Fragment>(
            FirstOnboardingFragment(), SecondOnboardingFragment(), ThirdOnboardingFragment()
        )
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.vpOnboarding.adapter = adapter
        binding.onboardingIndicator.attachTo(binding.vpOnboarding)
    }

    private fun enableEdgeToEdge() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars())
                controller.show(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

            binding.root.setOnApplyWindowInsetsListener { view, insets ->
                val navBarInsets = insets.getInsets(WindowInsets.Type.navigationBars())
                view.setPadding(0, 0, 0, navBarInsets.bottom)
                insets
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )

            binding.root.setOnApplyWindowInsetsListener { view, insets ->
                val navBarInsets = insets.systemWindowInsetBottom
                view.setPadding(0, 0, 0, navBarInsets)
                insets
            }
        }
    }

}