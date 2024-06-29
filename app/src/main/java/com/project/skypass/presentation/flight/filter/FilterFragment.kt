package com.project.skypass.presentation.flight.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.data.model.FilterFlight
import com.project.skypass.databinding.FragmentFilterBinding
import com.project.skypass.presentation.customview.FilterFlightSelected
import com.project.skypass.presentation.flight.filter.adapter.FilterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBinding
    private val viewModel: FilterViewModel by viewModel()
    var filterFlight: FilterFlightSelected? = null
    private var selectedFilter: FilterFlight? = null
    private val filterAdapter by lazy {
        FilterAdapter {
            selectedFilter = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setOnClickListeners()
        loadData()
    }

    private fun loadData() {
        filterItemClicked(viewModel.getFilter())
    }

    private fun setUpRecyclerView() {
        binding.rvFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filterAdapter
        }
    }

    private fun setOnClickListeners() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnFlightClass.setOnClickListener {
            if (tag == "filter") {
                selectedFilter?.let {
                    filterFlight?.onFilterSelected(tag ?: "", it)
                }
            }
            dismiss()
        }
    }

    private fun filterItemClicked(filterFlight: List<FilterFlight>) {
        filterAdapter.submitData(filterFlight)
    }
}
