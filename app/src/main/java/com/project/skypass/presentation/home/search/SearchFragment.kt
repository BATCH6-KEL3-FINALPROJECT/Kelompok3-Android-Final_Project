package com.project.skypass.presentation.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.skypass.data.model.Search
import com.project.skypass.databinding.FragmentSearchBinding
import com.project.skypass.presentation.customview.DataSelection
import com.project.skypass.presentation.home.search.adapter.SearchAdapter
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter by lazy {
        SearchAdapter {selectedTrip ->
            // Handle item click
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
    }

    private fun setupRecyclerView() {
        binding.rvSearchResult.apply {
            adapter = this@SearchFragment.searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun searchDestination() {
        binding.svCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
                viewModel.search(query).observe(viewLifecycleOwner) { results ->
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

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun bindDataToAdapter(data: List<Search>) {
        searchAdapter.submitData(data)
    }

}