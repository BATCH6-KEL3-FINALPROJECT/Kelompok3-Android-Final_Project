package com.project.skypass.presentation.onboarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.skypass.R
import com.project.skypass.databinding.ActivityOnboardingBinding
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.presentation.onboarding.adapter.ViewPagerAdapter
import com.project.skypass.presentation.onboarding.page.FirstOnboardingFragment
import com.project.skypass.presentation.onboarding.page.SecondOnboardingFragment
import com.project.skypass.presentation.onboarding.page.ThirdOnboardingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : AppCompatActivity() {
    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    private val viewModel: OnboardingViewModel by viewModel()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            proceedToMainActivity()
        } else {
            // Permission denied, you can show a message to the user
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.root.viewTreeObserver.removeOnPreDrawListener(this)
                    enableEdgeToEdge()
                    return true
                }
            },
        )

        onboarding()
        clickOnboarding()
    }

    private fun changePageListener(fragmentList: List<Fragment>) {
        binding.vpOnboarding.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == fragmentList.size - 1) {
                        binding.tvButtonOnboarding.text = getString(R.string.get_started)
                    } else {
                        binding.tvButtonOnboarding.text = getString(R.string.next)
                    }
                }
            },
        )
        binding.tvButtonOnboarding.text =
            if (binding.vpOnboarding.currentItem == fragmentList.size - 1) {
                getString(R.string.get_started)
            } else {
                getString(R.string.next)
            }
    }

    private fun onboarding() {
        val fragmentList =
            arrayListOf<Fragment>(
                FirstOnboardingFragment(),
                SecondOnboardingFragment(),
                ThirdOnboardingFragment(),
            )
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.vpOnboarding.adapter = adapter
        binding.onboardingIndicator.attachTo(binding.vpOnboarding)

        changePageListener(fragmentList)
    }

    private fun clickOnboarding() {
        binding.tvButtonOnboarding.setOnClickListener {
            if (binding.vpOnboarding.currentItem < 2) {
                binding.vpOnboarding.currentItem += 1
            } else {
                checkPermissionAndProceed()
            }
        }
    }

    private fun checkPermissionAndProceed() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permission is already granted, proceed to MainActivity
                proceedToMainActivity()
            }
            else -> {
                // Request the permission
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun proceedToMainActivity() {
        viewModel.setFirstRun(true)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
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