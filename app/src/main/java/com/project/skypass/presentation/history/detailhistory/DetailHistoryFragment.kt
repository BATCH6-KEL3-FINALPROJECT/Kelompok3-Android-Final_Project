package com.project.skypass.presentation.history.detailhistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.skypass.R
import com.project.skypass.databinding.FragmentDetailHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHistoryFragment : Fragment() {

    private lateinit var  binding: FragmentDetailHistoryBinding
    private val viewModel: DetailHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}