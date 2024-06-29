package com.project.skypass.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.databinding.LayoutLoginBeforeCheckoutBinding
import com.project.skypass.presentation.auth.login.LoginActivity

class LoginBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: LayoutLoginBeforeCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LayoutLoginBeforeCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.btnSubmit.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            dismiss()
        }
        binding.idClear.setOnClickListener {
            dismiss()
        }
    }
}
