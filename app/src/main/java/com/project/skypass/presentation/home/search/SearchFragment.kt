package com.project.skypass.presentation.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.databinding.FragmentSearchBinding
import com.project.skypass.presentation.customview.DataSelection
import com.project.skypass.presentation.home.search.adapter.SearchAdapter
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter by lazy {
        SearchAdapter {selectedTrip ->
            if (tag == "fromTrip") {
                tripSelection?.onTripSelected(tag ?: "", selectedTrip)
            } else if (tag == "toTrip") {
                tripSelection?.onTripSelected(tag ?: "", selectedTrip)
            }
            dismiss()
        }
    }
    private val viewModel: SearchViewModel by viewModel()
    var tripSelection: DataSelection? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        searchDestination()
        setupSearchHistory()
    }

    private fun setupRecyclerView() {
        binding.rvSearchResult.apply {
            adapter = this@SearchFragment.searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupSearchHistory() {
        val token = viewModel.getToken()
        viewModel.getHistorySearch(token).observe(viewLifecycleOwner) { result ->
            result.proceedWhen(
                doOnSuccess = { data ->
                    binding.rvSearchNow.isVisible = true
                    binding.tvEmptySearchResult.isVisible = false
                    data.payload?.let {
                        bindHistoryToChipGroup(it)
                    }
                },
                doOnLoading = {
                    // Handle loading state if necessary
                },
                doOnError = {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun bindHistoryToChipGroup(historyList: List<SearchHistoryHome>) {
        binding.rvSearchNow.removeAllViews()
        for (history in historyList) {
            val chip = Chip(requireContext()).apply {
                text = history.history
                isClickable = true
                isCheckable = true
                setOnCheckedChangeListener { _, isChecked ->
                    // Handle chip checked state if necessary
                }
            }
            binding.rvSearchNow.addView(chip)
        }
        binding.rvSearchNow.childCount
    }


    private fun searchDestination() {
        binding.svCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText).observe(viewLifecycleOwner) { results ->
                    results.proceedWhen(
                        doOnSuccess = {
                            binding.rvSearchResult.isVisible = true
                            binding.tvEmptySearchResult.isVisible = false
                            it.payload?.let {
                                bindDataToAdapter(it)
                            }
                        },
                        doOnLoading = {
                            binding.rvSearchResult.isVisible = false
                            binding.tvEmptySearchResult.isVisible = false
                        },
                        doOnError = {
                            binding.rvSearchResult.isVisible = false
                            binding.tvEmptySearchResult.isVisible = true
                        }
                    )
                }
                return true
            }
        })
    }

    private fun bindDataToAdapter(data: List<Search>) {
        searchAdapter.submitData(data)
    }

}