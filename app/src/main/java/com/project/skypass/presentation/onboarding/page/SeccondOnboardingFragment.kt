package com.project.skypass.presentation.onboarding.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.skypass.databinding.FragmentSecondOnboardingBinding

class SecondOnboardingFragment : Fragment() {
    private lateinit var binding: FragmentSecondOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSecondOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }
}
