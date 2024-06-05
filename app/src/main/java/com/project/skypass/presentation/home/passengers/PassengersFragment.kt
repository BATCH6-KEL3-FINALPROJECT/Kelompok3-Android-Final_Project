package com.project.skypass.presentation.home.passengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.R
import com.project.skypass.databinding.FragmentPassengersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PassengersFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPassengersBinding
    private val viewModel: PassengersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPassengersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        totalAdult()
        totalChildren()
        totalInfants()
    }

    private fun totalInfants() {
        binding.tvIncreasePassengersBaby.setOnClickListener {
            viewModel.addPassenger()
        }
        binding.tvDecreasePassengersBaby.setOnClickListener {
            viewModel.minusPassenger()
        }
        viewModel.passengersCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalBaby.setText(it.toString())
        }
    }

    private fun totalChildren() {
        binding.tvIncreasePassengersChildren.setOnClickListener {
            viewModel.addPassenger()
        }
        binding.tvDecreasePassengersChildren.setOnClickListener {
            viewModel.minusPassenger()
        }
        viewModel.passengersCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalChildren.setText(it.toString())
        }
    }

    private fun totalAdult() {
        binding.tvIncreasePassengersAdult.setOnClickListener {
            viewModel.addPassenger()
        }
        binding.tvDecreasePassengersAdult.setOnClickListener {
            viewModel.minusPassenger()
        }
        viewModel.passengersCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalAdult.setText(it.toString())
        }
    }

}