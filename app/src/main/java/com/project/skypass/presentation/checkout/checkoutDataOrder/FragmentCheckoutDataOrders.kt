package com.project.skypass.presentation.checkout.checkoutDataOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import com.project.skypass.R
import com.project.skypass.databinding.FragmentCheckoutDataOrdersBinding
import com.project.skypass.databinding.FragmentHomeBinding


class FragmentCheckoutDataOrders : Fragment() {
    private lateinit var binding: FragmentCheckoutDataOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutDataOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }
}