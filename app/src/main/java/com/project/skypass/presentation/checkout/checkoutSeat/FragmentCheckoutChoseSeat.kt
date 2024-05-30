package com.project.skypass.presentation.checkout.checkoutSeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.skypass.R
import com.project.skypass.databinding.FragmentCheckoutChoseSeatBinding
import com.project.skypass.databinding.FragmentHomeBinding

class FragmentCheckoutChoseSeat : Fragment() {
    private lateinit var binding: FragmentCheckoutChoseSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutChoseSeatBinding.inflate(inflater, container, false)
        return binding.root
    }
}