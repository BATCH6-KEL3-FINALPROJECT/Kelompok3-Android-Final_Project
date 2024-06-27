package com.project.skypass.presentation.history.filter.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.project.skypass.data.model.History
import com.project.skypass.databinding.FragmentSearchHistoryBinding
import com.project.skypass.presentation.history.OnSearchItemSelectedListener
import com.project.skypass.presentation.history.filter.search.adapter.SearchHistoryAdapter
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchHistoryFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchHistoryBinding
    private lateinit var callback: OnSearchItemSelectedListener
    private val searchAdapter by lazy {
        SearchHistoryAdapter { selectedBooking ->
            handleSearchItemSelected(selectedBooking.bookingCode)
            targetFragment?.onActivityResult(
                targetRequestCode, Activity.RESULT_OK, Intent().apply {
                    putExtra("searchQuery", selectedBooking.bookingCode)
                }
            )
            dismiss()
        }
    }
    private val viewModel: SearchHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        showDataSuggestionSearch()
        searchDestination()
    }

    private fun showDataSuggestionSearch() {
        viewModel.getBookingHistory(token = viewModel.getToken(), search = "")
            ?.observe(viewLifecycleOwner) { results ->
                results.proceedWhen(
                    doOnSuccess = {
                        binding.rvSearchResult.isVisible = true
                        binding.tvEmptySearchResult.isVisible = false
                        it.payload?.let { historyList ->
                            bindDataToAdapter(historyList)
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
            adapter = this@SearchHistoryFragment.searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun searchDestination() {
        binding.svBooking.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    performSearch(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    showDataSuggestionSearch()
                    binding.rvSearchResult.isVisible = true
                    binding.tvEmptySearchResult.isVisible = false
                } else {
                    viewModel.getBookingHistory(token = viewModel.getToken(), search = newText)
                        ?.observe(viewLifecycleOwner) { results ->
                            results.proceedWhen(
                                doOnSuccess = { it ->
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
                return true
            }
        })
    }

    private fun handleSearchItemSelected(selectedBookingCode: String) {
        viewModel.getBookingHistory(token = viewModel.getToken(), search = selectedBookingCode)
            ?.observe(viewLifecycleOwner) { results ->
                results.proceedWhen(
                    doOnSuccess = {
                        binding.rvSearchResult.isVisible = true
                        binding.tvEmptySearchResult.isVisible = false
                        it.payload?.let { historyList ->
                            bindDataToAdapter(historyList)
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

    private fun performSearch(query: String) {
        viewModel.getBookingHistory(token = viewModel.getToken(), search = query)
            ?.observe(viewLifecycleOwner) { results ->
                results.proceedWhen(
                    doOnSuccess = { it ->
                        binding.rvSearchResult.isVisible = true
                        binding.tvEmptySearchResult.isVisible = false
                        it.payload?.let { historyList ->
                            if (historyList.isNotEmpty()) {
                                binding.rvSearchResult.isVisible = false
                            }
                            bindDataToAdapter(historyList)
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

    private fun bindDataToAdapter(data: List<History>) {
        searchAdapter.submitData(data)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchItemSelectedListener) {
            callback = context
        } else {
            throw RuntimeException("$context must implement OnSearchItemSelectedListener")
        }
    }
}
