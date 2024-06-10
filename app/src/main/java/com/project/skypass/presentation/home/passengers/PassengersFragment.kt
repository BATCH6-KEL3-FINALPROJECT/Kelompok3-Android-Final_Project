package com.project.skypass.presentation.home.passengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.databinding.FragmentPassengersBinding
import com.project.skypass.presentation.customview.DataSelection
import com.project.skypass.presentation.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PassengersFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPassengersBinding
    private val viewModel: PassengersViewModel by viewModel()
    var passengersSelection: DataSelection? = null

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
        sendData()
    }

    private fun setOnClickListener() {
        totalAdult()
        totalChildren()
        totalInfants()
    }

    private fun sendData() {
        binding.btnSavePassengers.setOnClickListener {
            val totalPassengers = viewModel.totalPassengersCount()
            Toast.makeText(requireContext(), totalPassengers.toString(), Toast.LENGTH_SHORT).show()
            if (tag == "passengers"){
                passengersSelection?.onPassengerSelected(tag ?: "", totalPassengers.toString())
            }
            dismiss()
        }
    }

    private fun totalInfants() {
        binding.tvIncreasePassengersBaby.setOnClickListener {
            viewModel.addInfantPassenger()
        }
        binding.tvDecreasePassengersBaby.setOnClickListener {
            viewModel.minusInfantPassenger()
        }
        viewModel.passengersInfantCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalBaby.setText(it.toString())
        }
    }

    private fun totalChildren() {
        binding.tvIncreasePassengersChildren.setOnClickListener {
            viewModel.addChildrenPassenger()
        }
        binding.tvDecreasePassengersChildren.setOnClickListener {
            viewModel.minusChildrenPassenger()
        }
        viewModel.passengersChildrenCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalChildren.setText(it.toString())
        }
    }

    private fun totalAdult() {
        binding.tvIncreasePassengersAdult.setOnClickListener {
            viewModel.addAdultPassenger()
        }
        binding.tvDecreasePassengersAdult.setOnClickListener {
            viewModel.minusAdultPassenger()
        }
        viewModel.passengersAdultCountLiveData.observe(viewLifecycleOwner){
            binding.etTotalAdult.setText(it.toString())
        }
    }

}