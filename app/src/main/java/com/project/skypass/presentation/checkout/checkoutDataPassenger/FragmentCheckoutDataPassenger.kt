package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.skypass.R
import com.project.skypass.databinding.FragmentCheckoutChoseSeatBinding
import com.project.skypass.databinding.FragmentCheckoutDataPassengerBinding


class FragmentCheckoutDataPassenger : Fragment() {
    private lateinit var binding: FragmentCheckoutDataPassengerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutDataPassengerBinding.inflate(inflater, container, false)
        return binding.root
    }
}