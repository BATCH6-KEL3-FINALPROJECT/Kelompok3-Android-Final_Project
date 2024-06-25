package com.project.skypass.presentation.flight.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.databinding.FragmentFilterBinding

class FilterFragment : BottomSheetDialogFragment() {
    private lateinit var  binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}