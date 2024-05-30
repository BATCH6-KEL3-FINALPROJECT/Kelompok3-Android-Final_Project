package com.project.skypass.presentation.checkout.checkoutDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.skypass.databinding.FragmentCheckoutDataPassengerBinding
import com.project.skypass.databinding.FragmentDetailCheckoutBinding


class FragmentCheckoutDetail : Fragment() {
    private lateinit var binding: FragmentDetailCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}