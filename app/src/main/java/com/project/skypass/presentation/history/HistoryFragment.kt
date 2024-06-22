package com.project.skypass.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.R
import com.project.skypass.databinding.FragmentHistoryBinding
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.DataItem
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.HeaderItem
import com.project.skypass.presentation.history.filter.date.CalendarHistoryFragment
import com.project.skypass.presentation.history.filter.search.SearchHistoryFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.xwray.groupie.viewbinding.BindableItem
import dev.jahidhasanco.seatbookview.SeatBookView
import org.koin.core.parameter.parametersOf
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
    }

    private fun setData() {
        binding.rvTicketHistoryOrder.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HistoryFragment.adapter
        }
    }

/* private fun observeResult() {
    viewModel.getHistory().observe(viewLifecycleOwner) { result ->
        result.pro
        result.payload?.let { data ->
            val items = mutableListOf<BindableItem<*>>()

            val groupedData = data.groupBy { it.createdAt.toMonthYearFormat() }

            groupedData.forEach { (monthYear, dataList) ->
                items.add(MonthHeaderItem(monthYear))
                dataList.forEach { data ->
                    items.add(HistoryDataItem(data))
                }
            }
            groupAdapter.update(items)
        }
    }
}
 */

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