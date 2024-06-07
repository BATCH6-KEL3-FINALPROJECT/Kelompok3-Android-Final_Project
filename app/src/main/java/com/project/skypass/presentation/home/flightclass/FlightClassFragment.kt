package com.project.skypass.presentation.home.flightclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.data.model.SeatClass
import com.project.skypass.databinding.FragmentFlightClassBinding
import com.project.skypass.presentation.home.flightclass.adapter.FlightClassAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightClassFragment : BottomSheetDialogFragment() {

    private lateinit var  binding: FragmentFlightClassBinding
    private val viewModel: FlightClassViewModel by viewModel()

    private val flightClassAdapter: FlightClassAdapter by lazy {
        FlightClassAdapter{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFlightClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindSeatClass(viewModel.getFlightClass())
    }

    private fun bindSeatClass(flightClass: List<SeatClass>) {
        binding.rvFlightClass.apply {
            adapter = flightClassAdapter
        }
        flightClassAdapter.submitData(flightClass)
    }

}