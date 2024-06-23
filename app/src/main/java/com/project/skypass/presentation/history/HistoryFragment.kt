package com.project.skypass.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.R
import com.project.skypass.databinding.FragmentHistoryBinding
import com.project.skypass.presentation.history.adapter.HistoryMonthItem
import com.project.skypass.presentation.history.adapter.HistoryTicketItem
import com.project.skypass.presentation.history.filter.date.CalendarHistoryFragment
import com.project.skypass.presentation.history.filter.search.SearchHistoryFragment
import com.project.skypass.utils.convertDateMouth
import com.project.skypass.utils.proceedWhen
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModel()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        setData()
        observeResult()
    }

    private fun setData() {
        binding.rvTicketHistoryOrder.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HistoryFragment.adapter
        }
    }

    private fun observeResult() {
        viewModel.getAllHistory(viewModel.getToken()).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { result ->
                    binding.layoutContentState.root.isVisible = false
                    result.payload?.let { data ->
                        val items = mutableListOf<BindableItem<*>>()

                        val groupedData = data.groupBy { convertDateMouth(it.bookingDate) }

                        groupedData.forEach { (mount, data) ->
                            items.add(HistoryMonthItem(mount))
                            data.forEach { list ->
                                items.add(HistoryTicketItem(list))
                            }
                        }
                        adapter.update(items)
                    }
                },
                doOnEmpty = {
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.text_empty_seat_class)
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                },
                doOnLoading = {
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.isVisible = false
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                }, doOnError = {
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.unknown_error)
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                }

            )
        }
    }

    private fun clickListener() {
        binding.ivSearchHistory.setOnClickListener {
            val searchFragment = SearchHistoryFragment()
            searchFragment.show(childFragmentManager, searchFragment.tag)

        }
        binding.llFilterContainer.setOnClickListener {
            val calendarFragment = CalendarHistoryFragment()
            calendarFragment.show(childFragmentManager, calendarFragment.tag)
        }
    }


}