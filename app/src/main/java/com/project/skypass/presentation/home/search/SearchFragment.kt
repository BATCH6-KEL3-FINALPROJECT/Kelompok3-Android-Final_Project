package com.project.skypass.presentation.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.project.skypass.R
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
            insertHistory(selectedTrip.city)
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
        showDataSuggestionSearch()
        setupSearchHistory()
        searchDestination()
    }

    private fun showDataSuggestionSearch(){
        viewModel.search().observe(viewLifecycleOwner) { results ->
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

                },
                doOnError = {

                }
            )
        }
    }

    private fun bindHistoryToChipGroup(historyList: List<SearchHistoryHome>) {
        binding.rvSearchNow.removeAllViews()
        for (i in historyList.size - 1 downTo 0) {
            val history = historyList[i]
            val chip = Chip(requireContext()).apply {
                text = history.history
                isClickable = true
                isCheckable = true
                chipStrokeWidth = 0.0f
                isCloseIconVisible = true
                chipCornerRadius = 36f
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        binding.svCity.setQuery(history.history, false)
                    }
                }
                setOnCloseIconClickListener {
                    viewModel.deleteHistorySearch(
                        viewModel.getToken(),
                        history.idHistory
                    ).observe(viewLifecycleOwner){result ->
                        result.proceedWhen(
                            doOnSuccess = {
                                binding.rvSearchNow.removeView(this)
                            },
                            doOnLoading = {

                            },
                            doOnError = {

                            }
                        )
                    }
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
                if (newText.isNullOrEmpty()) {
                    showDataSuggestionSearch()
                    binding.rvSearchResult.isVisible = true
                    binding.rvSearchNow.isVisible = true
                    binding.tvEmptySearchResult.isVisible = false
                } else {
                    viewModel.search(newText).observe(viewLifecycleOwner) { results ->
                        results.proceedWhen(
                            doOnSuccess = {
                                binding.rvSearchResult.isVisible = true
                                binding.rvSearchNow.isVisible = false
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
                }
                return true
            }
        })
    }

    private fun bindDataToAdapter(data: List<Search>) {
        searchAdapter.submitData(data)
    }

    private fun insertHistory(city: String) {
        val token = viewModel.getToken()
        viewModel.insertHistorySearch(token, city).observe(viewLifecycleOwner) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    setupSearchHistory()
                    Toast.makeText(requireContext(), "Added to history", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    // Optionally show a loading indicator
                },
                doOnError = {
                    Toast.makeText(requireContext(), "Failed to add to history", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}